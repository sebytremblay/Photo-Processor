package controller.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import model.color.Color;
import model.image.ImageModel;
import model.image.SimpleImage;
import util.Util;

/**
 * Function object command for adding a mosaicking pattern to the image
 */
public class Mosaicking implements ImageProcessingCommand {
  private final int numSeeds;
  private final Function<ImageModel, List<PixelCoordinate>> func;

  public Mosaicking(int numSeeds, Function<ImageModel, List<PixelCoordinate>> func) {
    this.numSeeds = numSeeds;
    this.func = func;
  }

  @Override
  public ImageModel process(ImageModel m) throws IllegalArgumentException {
    Util.requireNonNullArg(m);

    List<PixelCoordinate> seeds = chooseSeeds(m);
    // PixelCoordinate and all of its nearest neighbors
    Map<PixelCoordinate, List<PixelCoordinate>> seedMapping = categorizeSeeds(seeds,m);
    ImageModel newImage = doMosaick(seedMapping, m);
    return newImage;
  }

  private ImageModel doMosaick(Map<PixelCoordinate, List<PixelCoordinate>> seedMapping, ImageModel m) {
    Color[][] newColors = new Color[m.getHeight()][m.getWidth()];

    for (PixelCoordinate seed : seedMapping.keySet()) {
      List<PixelCoordinate> grouping = seedMapping.get(seed);

      Color avgColor = findAvgColor(grouping, m);
      for (PixelCoordinate coord : grouping) {
        newColors[coord.x][coord.y] = avgColor;
      }
    }

    return new SimpleImage(newColors);
  }

  // Finds the average color of a list of colors
  private Color findAvgColor(List<PixelCoordinate> grouping, ImageModel m) {
    int avgRed = 0;
    int avgBlue = 0;
    int avgGreen = 0;

    for (PixelCoordinate pix : grouping) {
      Color color = m.colorAt(pix.x, pix.y);
      avgRed += color.red();
      avgGreen += color.green();
      avgBlue += color.blue();
    }

    return new Color(avgRed / grouping.size(),
            avgGreen / grouping.size(),
            avgBlue / grouping.size());
  }

  // Assigns pixels to the closest seed
  private Map<PixelCoordinate, List<PixelCoordinate>> categorizeSeeds(List<PixelCoordinate> seeds,
                                                                      ImageModel m) {
    Map<PixelCoordinate, List<PixelCoordinate>> seedMapping =
            new HashMap<PixelCoordinate, List<PixelCoordinate>>();

    for (int row = 0; row < m.getHeight(); row += 1) {
      for (int col = 0; col < m.getWidth(); col += 1) {
        // For pixel p at location, find the closest seed
        PixelCoordinate p = new PixelCoordinate(row, col);
        PixelCoordinate closestSeed = findClosestPix(p, seeds);

        // Add current pixel to the closest seed's group
        List<PixelCoordinate> seedNeighbors = seedMapping.getOrDefault(closestSeed,
                new ArrayList<PixelCoordinate>());
        seedNeighbors.add(p);
        seedMapping.put(closestSeed, seedNeighbors);
      }
    }
    return seedMapping;
  }

  // Chooses seeds based off the provided strategy
  private List<PixelCoordinate> chooseSeeds(ImageModel m) {
    return this.func.apply(m);
  }

  // Finds the closest pixel in the list to myPix
  private PixelCoordinate findClosestPix(PixelCoordinate myPix,
                                         List<PixelCoordinate> nearbyPix) {
    double min = Double.MAX_VALUE;
    PixelCoordinate cloestPix = nearbyPix.get(0);

    for (PixelCoordinate p : nearbyPix) {
      if (distance(p, myPix) < min) {
        min = distance(p, myPix);
        cloestPix = p;
      }
    }

    return cloestPix;
  }

  // Finds Euclidean distance between two points
  private double distance(PixelCoordinate p1, PixelCoordinate p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) +
            Math.pow(p1.y - p2.y, 2));
  }
}