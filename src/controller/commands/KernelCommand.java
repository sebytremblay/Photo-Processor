package controller.commands;

import model.imageprocessor.ImageProcessor;

/**
 * A command to apply a kernel an image.
 */
public class KernelCommand extends AbstractCommand {
  private final String newImgName;
  private final String imgName;
  private final String imgMaskName;
  private final double[][] kernel;


  /**
   * Creates a command to apply a given kernel.
   *
   * @param param       params for the command imgName maskImgName newImgName
   * @param kernel     the kernel to apply
   * @param append     the output stream
   */
  public KernelCommand(String param,
                       double[][] kernel, Appendable append) {
    super(append);

    this.kernel = kernel;
    String[] commands = param.split(" ");
    for (String com: commands){
      System.out.println(com);
    }
    if (commands.length == 4){
      this.imgName = commands[1];
      this.imgMaskName = commands[2];
      this.newImgName = commands[3];

    }else if (commands.length == 3){
      this.imgName = commands[1];
      this.newImgName = commands[2];
      this.imgMaskName = null;

    }else{
      throw new IllegalArgumentException("Incorrect Arguments");
    }
  }

  @Override
  public void run(ImageProcessor model) {
    model.applyKernel(imgName, imgMaskName,newImgName,  kernel);
    super.successMessage("Kernel applied");
  }

}


