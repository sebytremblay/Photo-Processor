package controller;

public class FeaturesImpl implements Features {
  private ImageProcessorController controller;

  public FeaturesImpl(ImageProcessorController controller) {
    this.controller = controller;
  }
  @Override
  public void readButtonClick(String btnAction) {
    controller.processCommand(btnAction);
  }
}
