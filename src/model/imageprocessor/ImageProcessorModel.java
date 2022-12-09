package model.imageprocessor;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.operations.VisualizeIntensity;
import model.pixel.Pixel;
import model.pixel.RGBPixel;


/**
 * Represents a model for an Image Processor.
 */
public class ImageProcessorModel implements ImageProcessor {
  private final Map<String, Pixel[][]> loadedImages;

  /**
   * Constructor the Image Processor with no loaded Images.
   */
  public ImageProcessorModel() {
    loadedImages = new HashMap<String, Pixel[][]>();
  }

  @Override
  public void load(Pixel[][] pixelGrid, String imgName) {
    loadedImages.put(imgName, pixelGrid);
  }

  @Override
  public void visualize(String imgName, String newImgName, Function<Pixel, Pixel> f) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[0].length; col += 1) {
        Pixel pixel = pixelGrid[row][col];
        Pixel newPixel = f.apply(pixel);
        newPixelGrid[row][col] = newPixel;
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }


  // determines is an image is loaded, and throws an error if not
  private void isLoadedImgName(String imgName) {
    if (!loadedImages.containsKey(imgName)) {
      throw new IllegalArgumentException("The image " + imgName
              + " is not loaded in the processor");
    }
  }

  @Override
  public void flipImage(String imgName, String newImgName, Function<Pixel[][], Pixel[][]> f) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    loadedImages.put(newImgName, f.apply(pixelGrid));
  }


  @Override
  public String getImageAsString(String imgName) {
    isLoadedImgName(imgName);
    StringBuilder result = new StringBuilder();
    Pixel[][] saveImage = loadedImages.get(imgName);
    writeMessage("P3", result);
    writeMessage(saveImage[0].length + " " + saveImage.length, result);
    writeMessage("255", result);

    for (int row = 0; row < saveImage.length; row += 1) {
      for (int col = 0; col < saveImage[0].length; col += 1) {
        Pixel pixel = saveImage[row][col];
        writeMessage(pixel.toString(), result);
      }
    }

    return result.deleteCharAt(result.length() - 1).toString();
  }

  @Override
  public BufferedImage getImageAsBufferedImage(String imgName) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    BufferedImage bufferedImage = new BufferedImage(pixelGrid[0].length,
            pixelGrid.length, 1);
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        bufferedImage.setRGB(col, row, pixelGrid[row][col].pixelToHex());
      }
    }
    return bufferedImage;
  }


  @Override
  public void applyKernel(String imgName, String newImgName, double[][] kernel) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        Pixel[][] kernelBackground = getKernelBackground(row, col, kernel.length, pixelGrid);
        newPixelGrid[row][col] = pixelGrid[row][col].kernelEval(kernel, kernelBackground);
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }

  @Override
  public void applyColorTransformation(String imgName, String newImgName,
                                       double[][] transformation) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        newPixelGrid[row][col] = pixelGrid[row][col].colorTransformation(transformation);
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }

  @Override
  public void applyResize(String imgName, String newImgName, int height, int width) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[height][width];
    for (int row = 0; row < height; row+=1){
      for (int col = 0; col < width; col+=1){
         double cordOnOrgRow= (double)row / height * pixelGrid.length;
         double cordOnOrgCol = (double)col / width * pixelGrid[0].length;
         if (cordOnOrgRow % 1  == 0 || cordOnOrgCol % 1 == 0){
           Pixel pix = pixelGrid[(int)cordOnOrgRow][(int)cordOnOrgCol];
           newPixelGrid[row][col] = new RGBPixel((int)pix.getRed(),(int)pix.getGreen(),(int)pix.getBlue());
           continue;
         }

         Pixel a = pixelGrid[(int)Math.floor(cordOnOrgRow)][(int)Math.floor(cordOnOrgCol)];
         Pixel b = pixelGrid[(int)Math.ceil(cordOnOrgRow)][(int)Math.floor(cordOnOrgCol)];
         Pixel c = pixelGrid[(int)Math.floor(cordOnOrgRow)][(int)Math.ceil(cordOnOrgCol)];
         Pixel d = pixelGrid[(int)Math.ceil(cordOnOrgRow)][(int)Math.ceil(cordOnOrgCol)];

         double mRed = (b.getRed() * (cordOnOrgCol - Math.floor(cordOnOrgCol))) +
                 (a.getRed() * (Math.ceil(cordOnOrgCol) - cordOnOrgCol));
         double nRed = (d.getRed() * (cordOnOrgCol - Math.floor(cordOnOrgCol))) +
                 (c.getRed() * (Math.ceil(cordOnOrgCol) - cordOnOrgCol));
         double cRed = (mRed * (cordOnOrgRow - Math.floor(cordOnOrgRow))) + (nRed
                 * (Math.ceil(cordOnOrgRow)-cordOnOrgRow));

        double mGreen= (b.getGreen() * (cordOnOrgCol - Math.floor(cordOnOrgCol))) +
                (a.getGreen() * (Math.ceil(cordOnOrgCol) - cordOnOrgCol));
        double nGreen = (d.getGreen() * (cordOnOrgCol - Math.floor(cordOnOrgCol))) +
                (c.getGreen() * (Math.ceil(cordOnOrgCol) - cordOnOrgCol));
        double cGreen = (mGreen * (cordOnOrgRow - Math.floor(cordOnOrgRow))) + (nGreen
                * (Math.ceil(cordOnOrgRow)-cordOnOrgRow));

        double mBlue = (b.getBlue() * (cordOnOrgCol - Math.floor(cordOnOrgCol))) +
                (a.getBlue() * (Math.ceil(cordOnOrgCol) - cordOnOrgCol));
        double nBlue = (d.getBlue() * (cordOnOrgCol - Math.floor(cordOnOrgCol))) +
                (c.getBlue() * (Math.ceil(cordOnOrgCol) - cordOnOrgCol));
        double cBlue = (nBlue * (cordOnOrgRow - Math.floor(cordOnOrgRow))) + (mBlue
                * (Math.ceil(cordOnOrgRow)-cordOnOrgRow));
        newPixelGrid[row][col] = new RGBPixel((int)cRed,(int)cGreen,(int)cBlue);
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }

  @Override
  public int[][] generateHistogram(String imgName) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    int[] histogramRedMap = new int[256];
    int[] histogramGreenMap = new int[256];
    int[] histogramBlueMap = new int[256];
    int[] histogramVisualMap = new int[256];
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        Pixel pix = pixelGrid[row][col];
        histogramRedMap[pix.getRed()] += 1;
        histogramGreenMap[pix.getGreen()] += 1;
        histogramBlueMap[pix.getBlue()] += 1;

        Pixel intensityPixel = new VisualizeIntensity().apply(pix);
        histogramVisualMap[intensityPixel.getRed()] += 1;
      }

    }
    return new int[][]{histogramRedMap, histogramGreenMap, histogramBlueMap, histogramVisualMap};
  }

  // Gets the values that will exist behind a particular kernel at a particular position. If a
  // particular surrounding pixel do not exist, the returned pixel will be zero
  private Pixel[][] getKernelBackground(int row, int col, int length, Pixel[][] pixelGrid) {
    Pixel[][] background = new Pixel[length][length];
    int rowCounter = 0;
    for (int r = row - length / 2; r <= row + length / 2; r += 1) {
      int colCounter = 0;
      for (int c = col - length / 2; c <= col + length / 2; c += 1) {
        if (r < 0 || c < 0 || r >= pixelGrid.length || c >= pixelGrid[0].length) {
          background[rowCounter][colCounter] = new RGBPixel(0, 0, 0);
        } else {
          background[rowCounter][colCounter] = pixelGrid[r][c];
        }
        colCounter += 1;
      }
      rowCounter += 1;
    }
    return background;
  }



  // Writes a given message in a given builder and adds a new line
  private void writeMessage(String message, StringBuilder builder) {
    builder.append(message + "\n");
  }

}
