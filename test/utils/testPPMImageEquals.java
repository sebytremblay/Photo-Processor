package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * class that tests to see if the Image equal method works as intended
 */
public class testPPMImageEquals {
  @Test
  public void testPPMImageEquals(){
    assertEquals(true,ppmImageEquals.twoPPMImagesTheSame("koala.ppm","koala.ppm"));
    assertEquals(false,ppmImageEquals.twoPPMImagesTheSame("images/koala-blue-greyscale.ppm","koala.ppm"));

  }
}
