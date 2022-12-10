package controller.commands;

import model.imageprocessor.ImageProcessor;

public class Resize extends AbstractCommand implements ProcessCommand{
  private final String imgName;
  private final String newImgName;
  private final int width;
  private final int height;
  private final Appendable out;

  public Resize(int width, int height,String imgName, String newImgName, Appendable out){
    super(out);
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.width = width;
    this.height = height;
    this.out = out;
  }
  @Override
  public void run(ImageProcessor model) {
    model.applyResize(imgName, newImgName, height, width);
    super.successMessage("Resize applied");
  }
}
