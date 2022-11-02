public class RunImageProcessor {

  public static void main(String[] args) {
    ImageProcessor model = new ASCIIPPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(model);

    controller.run();
  }
}
