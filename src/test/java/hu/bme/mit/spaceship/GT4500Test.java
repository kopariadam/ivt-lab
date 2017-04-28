package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary,secondary;
  
  @Before
  public void init(){
	 primary = mock(TorpedoStore.class);
	 secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary,secondary);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
	  when(primary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
	  when(primary.fire(1)).thenReturn(true);
	  when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
    assertEquals(true, result);
  }

  //fire first fail, second success
  //fire both fail
  //all first fail
  //all second fail
  //all both fail
  @Test
  public void fireTorpedos_first_fail(){
    // Arrange
	  when(primary.fire(1)).thenReturn(false);
	  when(secondary.fire(1)).thenReturn(true);
	  
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(0)).fire(1);
    assertEquals(false, result);
  }
  
  @Test
  public void fireTorpedos_first_empty(){
    // Arrange
	  when(primary.isEmpty()).thenReturn(true);
	  when(secondary.fire(1)).thenReturn(true);
	  
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(0)).fire(1);
    verify(secondary,times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedos_All_first_fail(){
    // Arrange
	  when(primary.fire(1)).thenReturn(false);
	  when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedos_All_second_fail(){
    // Arrange
	  when(primary.fire(1)).thenReturn(true);
	  when(secondary.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedos_All_both_fail(){
    // Arrange
	  when(primary.fire(1)).thenReturn(false);
	  when(secondary.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
    assertEquals(false, result);
  }
  
  @Test
  public void fireTorpedos_one_twice(){
    // Arrange
	  when(primary.fire(1)).thenReturn(true);
	  when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }
  
  @Test
  public void fireTorpedos_one_twice_asdfasda(){
    // Arrange
	  when(primary.fire(1)).thenReturn(true);
	  when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(2)).fire(1);
    verify(secondary,times(0)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }
  
  @Test
  public void fireTorpedos_one_twice_eubjhjh(){
    // Arrange
	  when(primary.isEmpty()).thenReturn(true);
	  when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(0)).fire(1);
    verify(secondary,times(0)).fire(1);
    assertEquals(false, result);
    assertEquals(false, result2);
  }
  
  @Test
  public void fireTorpedos_one_twice_euberdgdf(){
    // Arrange
	  when(primary.isEmpty()).thenReturn(false);
	  when(primary.fire(1)).thenReturn(true);
	  when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    when(primary.isEmpty()).thenReturn(true);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(0)).fire(1);
    assertEquals(true, result);
    assertEquals(false, result2);
  }
}
