package controller;

import controller.commands.Load;
import controller.commands.Save;
import model.imageprocessor.ImageProcessor;
import view.ImageProcessorGUI;

/**
 * Represents the Controller that has an implementation of features.
 */
public class ControllerFeaturesImpl extends AbstractController implements Features {
  private final ImageProcessorGUI view;

  /**
   * Constructor for the feature's controller.
   *
   * @param view  the view that the information will be displayed at
   * @param model the model that the information will retrieve from.
   */
  public ControllerFeaturesImpl(ImageProcessorGUI view, ImageProcessor model) {
    super(model, new StringBuilder());
    this.view = view;
    this.model = model;
    this.commands.put("load", s -> new Load(s.nextLine(), s.next(), output));
    this.commands.put("save", s -> new Save(s.nextLine(), s.next(), output));
    view.acceptsFeaturesObject(this);
  }

  @Override
  public void readButtonClick(String btnAction, String mask, String imgName) {
    String inputString = btnAction + " " + imgName + " " + mask + " " + imgName;
    view.setHistogram(model.generateHistogram(imgName));
    update(inputString, imgName);
    view.refresh();
  }

  @Override
  public void readButtonClickSaveName(String btnAction, String maskName, String imgName,
                                      String newImageName) {

    String inputString = btnAction + " " + imgName + " " + maskName + " " + newImageName;
    update(inputString, newImageName);
    view.refresh();
  }

  public void readButtonClickFile(String btnAction, String filePath, String imgName) {
    String inputString = btnAction + " " + filePath + " \n " + imgName;
    update(inputString, imgName);
    view.setHistogram(model.generateHistogram(imgName));
    view.refresh();
  }

  private void update(String command, String imgName) {
    processCommand(command);
    view.setImage(model.getImageAsBufferedImage(imgName));
    view.renderMessage(output.toString());
    output = new StringBuilder();
  }

  @Override
  public void takesInTextField(String btnAction, String value, String mask,String imgName, String newImgName) {
    String input = btnAction + " " + value + " " + imgName + " " +  mask + " " + newImgName;
    update(input, newImgName);
    view.refresh();
  }

  @Override
  public void createMask(String currImgName,String maskName,int topLeftRow, int topLeftCol) {
      model.createMask(currImgName,maskName,topLeftRow,topLeftCol);
  }


}
