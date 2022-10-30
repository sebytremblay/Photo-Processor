import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Represents an ASCII PPM Image Processor.
 */
public class ASCIIPPMImageProcessor implements ImageProcessor{


  private final Map<String, Image> loadedImages;

  /**
   * Constructor the Image Processor with no laaded Images.
   */
  public ASCIIPPMImageProcessor(){
    loadedImages = new HashMap<String, Image>();
  }
  /**
   * Loads an image from an ASCII PPM file. If imgName is already taken, the new image will overwrite
   * the old image
   *
   * @param imgName  the name of the generated image
   * @param filename the file path of the file to load
   */
  @Override
  public void loadASCIIPPM(String imgName, String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] pixelGrid = new Pixel[width][height];

    for (int row=0;row<height;row++) {
      for (int col=0;col<width;col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pixel = new RGBPixel(r,g,b,maxValue);
        pixelGrid[row][col] = pixel;
      }
    }
    Image loadImage = new ASCIIPPMImage(pixelGrid,maxValue);
    loadedImages.put(imgName,loadImage);
  }

  /**
   * visualizes a component based on the given function
   *
   * @param imgName name of the loaded image we are trying to manipulate
   * @param f       function that is applied the image
   * @param newImageName the new modified name in the processor
   */
  @Override
  public void visualize(String imgName, String newImageName, Function f) {
    isLoadedImgName(imgName);
    Image newImage = loadedImages.get(imgName).visualize(f);
    loadedImages.put(newImageName,newImage);
  }

  // determines is an image is loaded, and throws an error if not
  private void isLoadedImgName(String imgName) {
    if (!loadedImages.containsKey(imgName)){
     throw new IllegalArgumentException("The image" + imgName + " is not loaded in the processor");
    }
  }

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  @Override
  public void flipImage(String imgName, String newImageName, Direction dir) {
    isLoadedImgName(imgName);
    Image newImage = loadedImages.get(imgName).flipImage(dir);
    loadedImages.put(newImageName, newImage);
  }

  /**
   * Brightens image by the provided constant and saves into new image.
   *
   * @param imgName    the name of the image to brighten
   * @param newImgName the name of the newly generated image
   * @param brightenBy the factor to brighten the image by
   */
  @Override
  public void brighten(String imgName, String newImgName, int brightenBy) {
    isLoadedImgName(imgName);
    Image newImage = loadedImages.get(imgName).brightenImage(brightenBy);
    loadedImages.put(newImgName, newImage);

  }

  /**
   * Saves an image to a given location and a given name. If filePath is occupied it will overwrite
   *
   * @param imgName  name of the saved image
   * @param filePath location to save of an image
   */
  @Override
  public void saveImage(String imgName, String filePath) {
    isLoadedImgName(imgName);
    StringBuilder result = new StringBuilder();
    Image saveImage = loadedImages.get(imgName);
    writeMessage("P3",result);
    writeMessage(saveImage.getWidth() + " " + saveImage.getHeight(),result);
    writeMessage(String.valueOf(saveImage.getMaxValue()), result);

    for (int row = 0; row < saveImage.getWidth(); row+=1){
      for (int col = 0; col < saveImage.getHeight(); col +=1){
        Pixel pixel = saveImage.getPixelAt(row,col);
        writeMessage(pixel.toString(),result);
      }
    }
    result.deleteCharAt(result.length()-1);
    try{
      Files.writeString(Path.of(filePath),result);
    }catch (IOException e){
      throw new IllegalArgumentException("the filePath you are saving to is not valid");
    }
  }


  private void writeMessage(String message, StringBuilder builder){
    builder.append(message + "\n");
  }
}
