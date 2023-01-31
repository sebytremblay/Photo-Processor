package controller.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import model.image.ImageModel;

/**
 * A strategy for choosing pixels from an image. This strategy chooses
 * pixels at random, with no checks for clustering.
 */
public class ChooseSeedsRandomly implements Function<ImageModel,
        List<PixelCoordinate>> {

  private int numSeeds;
  private Random r;

  /**
   * Chooses a specific amount of pixels from an image.
   *
   * @param numSeeds the number of pixels to choose
   */
  public ChooseSeedsRandomly(int numSeeds) {
    if (numSeeds < 0) {
      throw new IllegalArgumentException("Seed amount cannot be negative");
    }

    this.numSeeds = numSeeds;
    this.r = new Random();
  }

  /**
   * Choose a specific amount of pixels from an image with a provided
   * way to "randomly" choose the pixels.
   *
   * @param numSeeds the number of pixels to choose
   * @param rand     the random generator to use
   */
  public ChooseSeedsRandomly(int numSeeds, Random rand) {
    if (numSeeds < 0) {
      throw new IllegalArgumentException("Seed amount cannot be negative");
    }

    this.numSeeds = numSeeds;
    this.r = rand;
  }


  @Override
  public List<PixelCoordinate> apply(ImageModel imageModel) {
    List<PixelCoordinate> seeds = new ArrayList<PixelCoordinate>();

    // Imposes range on number of seeds
    numSeeds = Math.min(numSeeds, imageModel.getHeight() * imageModel.getWidth());

    for (int seedNum = 0; seedNum < numSeeds; seedNum += 1) {
      int seedRow = r.nextInt(imageModel.getHeight());
      int seedCol = r.nextInt(imageModel.getWidth());

      seeds.add(new PixelCoordinate(seedRow, seedCol));
    }

    return seeds;
  }
}
