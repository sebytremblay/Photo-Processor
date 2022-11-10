package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

public class VisualizeBrighten implements Function<Pixel, Pixel> {
  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  private final int brightenBy;
  public VisualizeBrighten(int brightenBy){
   this.brightenBy = brightenBy;
  }
  @Override
  public Pixel apply(Pixel pixel) {
    int[] comp = pixel.getComponents();
    return new RGBPixel(comp[0]+brightenBy,comp[1]+brightenBy,comp[2]+brightenBy);
  }
}
