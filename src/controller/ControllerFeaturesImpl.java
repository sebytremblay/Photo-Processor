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
  public void readButtonClick(String btnAction, String imgName) {
    String inputString = btnAction + " " + imgName + " " + imgName;
    update(inputString, imgName);
  }

  @Override
  public void readButtonClick(String btnAction, String filePath, String imgName) {
    String inputString = btnAction + " " + filePath + " \n " + imgName;
    update(inputString, imgName);
  }

  private void update(String command, String imgName) {
    processCommand(command);
    view.setImage(model.getImageAsBufferedImage(imgName));
    view.setHistogram(model.generateHistogram(imgName));
    view.renderMessage(output.toString());
    output = new StringBuilder();
    view.refresh();

  }

  @Override
  public void takesInTextField(String btnAction, String value, String imgName) {
    String input = btnAction + " " + value + " " + imgName + " " + imgName;
    update(input, imgName);
  }


}
