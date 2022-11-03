import org.junit.Before;
import org.junit.Test;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * Tests on the Pixel class.
 */
public class PixelTests {

  @Before
  public void init(){
    Pixel pixel1 = new RGBPixel(120,45,05,255);
    Pixel pixel2 = new RGBPixel(200,34,255,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelValueAboveMax(){
    Pixel pixel1 = new RGBPixel(120,503,23, 450);
  }
}
