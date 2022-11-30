package controller.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import model.image.ImageModel;

public class ChooseSeedsRandomly implements Function<ImageModel,
        List<PixelCoordinate>> {

  private int numSeeds;

  public ChooseSeedsRandomly(int numSeeds) {
    if (numSeeds < 0) {
      throw new IllegalArgumentException("Seed amount cannot be negative");
    }

    this.numSeeds = numSeeds;
  }

  @Override
  public List<PixelCoordinate> apply(ImageModel imageModel) {
    Random r = new Random();
    List<PixelCoordinate> seeds = new ArrayList<PixelCoordinate>();

    // Imposes range on number of seeds
    numSeeds = Math.min(numSeeds, imageModel.getHeight()) * imageModel.getWidth();

    for (int seedNum = 0; seedNum < numSeeds; seedNum += 1) {
      int seedRow = r.nextInt(imageModel.getHeight());
      int seedCol = r.nextInt(imageModel.getWidth());

      seeds.add(new PixelCoordinate(seedRow, seedCol));
    }

    return seeds;
  }
}
