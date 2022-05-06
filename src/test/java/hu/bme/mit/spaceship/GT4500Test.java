package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mock1;
  private TorpedoStore mock2;

  @BeforeEach
  public void init(){
    this.mock1 = mock(TorpedoStore.class);
    this.mock2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mock1, mock2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true); 
    when(mock2.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mock1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mock1.fire(mock1.getTorpedoCount())).thenReturn(true); 
    when(mock2.fire(mock2.getTorpedoCount())).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mock1, times(2)).fire(mock1.getTorpedoCount());
    verify(mock2, times(2)).fire(mock2.getTorpedoCount());
  }



  @Test
  public void testSingleModeFireSecondaryFirst(){
    // Arrange
    when(mock2.fire(1)).thenReturn(true); 
    when(mock1.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void testSingleModeFireSecondaryEmpty(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true); 
    when(mock2.isEmpty()).thenReturn(true); 
    when(mock1.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void testSingleModeFirePrimaryFirst(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true); 
    when(mock2.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void testSingleModeFirePrimaryEmpty(){
    // Arrange
    when(mock2.fire(1)).thenReturn(true); 
    when(mock1.isEmpty()).thenReturn(true); 
    when(mock2.fire(1)).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void testAllModeFireAll(){
    // Arrange
    when(mock1.fire(mock1.getTorpedoCount())).thenReturn(true); 
    when(mock2.fire(mock2.getTorpedoCount())).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
