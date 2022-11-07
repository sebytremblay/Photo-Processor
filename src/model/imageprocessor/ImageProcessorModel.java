package model.imageprocessor;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;


/**
 * Represents a model for an Image Processor.
 */
public class ImageProcessorModel implements ImageProcessor {
  private final Map<String, Pixel[][]> loadedImages;
  private final Map<String, Integer> loadedImagesMaxValue;

  /**
   * Constructor the Image Processor with no loaded Images.
   */
  public ImageProcessorModel() {
    // INVARIANT: loadedImages size is always equal to the loadedImagesMaxValue size
    loadedImages = new HashMap<String, Pixel[][]>();
    loadedImagesMaxValue = new HashMap<String, Integer>();
  }

  @Override
  public void load(String imgAsString, String imgName) {
    Scanner scan = new Scanner(imgAsString);
    scan.next();
    int width = scan.nextInt();
    int height = scan.nextInt();
    int maxValue = scan.nextInt();
    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int row = 0; row < height; row += 1) {
      for (int col = 0; col < width; col += 1) {
        int[] components = new int[3];

        // reads each of the pixel's components
        for (int comp = 0; comp < 3; comp += 1) {
          components[comp] = scan.nextInt();
        }

        pixelGrid[row][col] = new RGBPixel(components, maxValue);
      }
    }
    addPixelGridToProcessor(imgName, pixelGrid, maxValue);
  }

  @Override
  public void visualize(String imgName, String newImgName, Function f) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Integer maxValue = loadedImagesMaxValue.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[0].length; col += 1) {
        Pixel pixel = pixelGrid[row][col];
        Pixel newPixel = pixel.visual(f, maxValue);
        newPixelGrid[row][col] = newPixel;
      }
    }
    addPixelGridToProcessor(newImgName, newPixelGrid, maxValue);
  }

  // determines is an image is loaded, and throws an error if not
  private void isLoadedImgName(String imgName) {
    if (!loadedImages.containsKey(imgName)) {
      throw new IllegalArgumentException("The image " + imgName
              + " is not loaded in the processor");
    }
  }

  @Override
  public void flipImage(String imgName, String newImgName, Direction dir) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    int maxValue = loadedImagesMaxValue.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        if (dir == Direction.Vertical) {
          newPixelGrid[newPixelGrid.length - row - 1][col] = pixelGrid[row][col];
        }
        if (dir == Direction.Horizontal) {
          newPixelGrid[row][newPixelGrid[0].length - col - 1] = pixelGrid[row][col];
        }
      }
    }
    addPixelGridToProcessor(newImgName, newPixelGrid, maxValue);
  }

  @Override
  public void brighten(String imgName, String newImgName, int brightenBy) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    int maxValue = loadedImagesMaxValue.get(imgName);
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        Pixel pixel = pixelGrid[row][col];
        pixel = pixel.brightenPixel(brightenBy, maxValue);
        newPixelGrid[row][col] = pixel;
      }
    }
    addPixelGridToProcessor(newImgName, newPixelGrid, maxValue);
  }

  @Override
  public String getImageAsString(String imgName) {
    isLoadedImgName(imgName);
    StringBuilder result = new StringBuilder();
    Pixel[][] saveImage = loadedImages.get(imgName);
    Integer maxValue = loadedImagesMaxValue.get(imgName);
    writeMessage("P3", result);
    writeMessage(saveImage[0].length + " " + saveImage.length, result);
    writeMessage(String.valueOf(maxValue), result);

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
    BufferedImage bufferedImage = new BufferedImage(pixelGrid[0].length, pixelGrid.length, 1);
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        bufferedImage.setRGB(col, row, pixelGrid[row][col].PixelToHex());
      }
    }
    return bufferedImage;
  }

  /**
   * Applies the kernel to the image
   *
   * @param imgName    the image that the kernel is being applied to
   * @param newImgName the new image that will be the applied image
   * @param kernel     is the operation on the image
   */
  @Override
  public void applyKernel(String imgName, String newImgName, int[][] kernel) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    int maxValue = loadedImagesMaxValue.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        Pixel[][] kernelBackground = getKernelBackground(row,col,kernel.length,pixelGrid,maxValue);
        newPixelGrid[row][col] = pixelGrid[row][col].kernelEval(kernel,kernelBackground,maxValue);
      }
    }
  }

  // Gets the values that will exist behind a particular kernel at a particular position. If a
  // particular surrounding pixel do not exist, the returned pixel will be zero
  private Pixel[][] getKernelBackground(int row, int col, int length,Pixel[][] pixelGrid,int maxValue) {
    Pixel[][] background = new Pixel[length][length];
    int rowCounter = 0;
    for (int r = row-length/2; r < row+length;r+=1){
      int colCounter = 0;
      for (int c = col-length/2; c < col+length;c+=1){
        try{
         background[rowCounter][colCounter] = pixelGrid[r][c];
        }catch (ArrayIndexOutOfBoundsException e){
          background[rowCounter][colCounter] = new RGBPixel(new int[]{0,0,0},maxValue);
        }
        colCounter+=1;
      }
      rowCounter+=1;
    }
    return background;
  }


  // Writes a given message in a given builder and adds a new line
  private void writeMessage(String message, StringBuilder builder) {
    builder.append(message + "\n");
  }

  // Adds the given Image to the map based off a given ImgName, and adds a maxValue to a maxValue
  // map based off of a given ImgName
  private void addPixelGridToProcessor(String newImgName, Pixel[][] newPixelGrid, int maxValue) {
    loadedImages.put(newImgName, newPixelGrid);
    loadedImagesMaxValue.put(newImgName, maxValue);

  }
}
