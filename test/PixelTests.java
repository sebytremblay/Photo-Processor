import org.junit.Before;
import org.junit.Test;

import model.operations.visualizeBlue;
import model.operations.visualizeGreen;
import model.operations.visualizeIntensity;
import model.operations.visualizeLuma;
import model.operations.visualizeRed;
import model.operations.visualizeValue;
import model.pixel.Pixel;
import model.pixel.RGBPixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
  @Test(expected = IllegalArgumentException.class)
  public void negativePixel(){
    Pixel pixel2 = new RGBPixel(-1120,120,120,255);
  }
  @Test()
  public void validPixel(){
    Pixel pixel2 = new RGBPixel(120,120,120,255);
  }

  @Test() public void testVisualizeBlue(){
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.visual(new visualizeBlue(),255);
    assertEquals(new RGBPixel(30,30,30,255).toString(),newPixel1.toString());

    Pixel pixel2 = new RGBPixel(53,201,122,255);
    Pixel newPixel = pixel2.visual(new visualizeBlue(),255);
    assertEquals(new RGBPixel(122,122,122,255).toString(),newPixel.toString());
  }
  @Test() public void testVisualizeGreen(){
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.visual(new visualizeGreen(),255);
    assertEquals(new RGBPixel(20,20,20,255).toString(),newPixel1.toString());

    Pixel pixel2 = new RGBPixel(53,201,122,255);
    Pixel newPixel = pixel2.visual(new visualizeGreen(),255);
    assertEquals(new RGBPixel(201,201,201,255).toString(),newPixel.toString());
  }
  @Test() public void testVisualizeIntensity(){
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.visual(new visualizeIntensity(),255);
    assertEquals(new RGBPixel(20,20,20,255).toString(),newPixel1.toString());

    Pixel pixel2 = new RGBPixel(53,201,122,255);
    int avg = (53+201+122) /3;
    Pixel newPixel = pixel2.visual(new visualizeIntensity(),255);
    assertEquals(new RGBPixel(avg,avg,avg,255).toString(),newPixel.toString());
  }
  @Test() public void testVisualizeLuma(){
    int val1 = (int)(0.2126 * 10
            + 0.7152 * 20
            + 0.0722 * 30);
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.visual(new visualizeLuma(),255);
    assertEquals(new RGBPixel(val1,val1,val1,255).toString(),newPixel1.toString());

    int val2 = (int)(0.2126 * 53
            + 0.7152 * 201
            + 0.0722 * 122);
    Pixel pixel2 = new RGBPixel(53,201,122,255);
    Pixel newPixel = pixel2.visual(new visualizeLuma(),255);
    assertEquals(new RGBPixel(val2,val2,val2,255).toString(),newPixel.toString());
  }

  @Test() public void testVisualizeRed(){
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.visual(new visualizeRed(),255);
    assertEquals(new RGBPixel(10,10,10,255).toString(),newPixel1.toString());

    Pixel pixel2 = new RGBPixel(53,201,122,255);
    Pixel newPixel = pixel2.visual(new visualizeRed(),255);
    assertEquals(new RGBPixel(53,53,53,255).toString(),newPixel.toString());

  }
  @Test() public void testVisualizeValue(){
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.visual(new visualizeValue(),255);
    assertEquals(new RGBPixel(30,30,30,255).toString(),newPixel1.toString());

    Pixel pixel2 = new RGBPixel(53,201,122,255);
    Pixel newPixel = pixel2.visual(new visualizeValue(),255);
    assertEquals(new RGBPixel(201,201,201,255).toString(),newPixel.toString());
  }
  @Test() public void testBrighten(){
    Pixel pixel1 = new RGBPixel(10,20,30,255);
    Pixel newPixel1 = pixel1.brightenPixel(10,255);
    assertEquals(new RGBPixel(20,30,40,255).toString(),newPixel1.toString());

    Pixel pixel3 = new RGBPixel(10,20,30,255);
    Pixel newPixel3 = pixel3.brightenPixel(-20,255);
    assertEquals(new RGBPixel(0,0,10,255).toString(),newPixel3.toString());

    Pixel pixel2 = new RGBPixel(53,201,122,255);
    Pixel newPixel = pixel2.brightenPixel(-54,255);
    assertEquals(new RGBPixel(0,147,68,255).toString(),newPixel.toString());
  }

  @Test()
  public void testGetComp(){
    int[] arr = {10,10,20};
    Pixel pixel1 = new RGBPixel(10,10,20,255);
    assertArrayEquals(arr,pixel1.getComponents());

    int[] arr2 = {51,20,206};
    Pixel pixel2 = new RGBPixel(51,20,206,255);
    assertArrayEquals(arr2,pixel2.getComponents());
  }

  @Test()
  public void testToString(){
    Pixel pixel1 = new RGBPixel(10,10,20,255);
    assertEquals("10\n10\n20\n",pixel1.toString());
    Pixel pixel2 = new RGBPixel(90,101,46,255);
    assertEquals("90\n101\n46\n",pixel2.toString());
  }

}
