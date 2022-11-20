package controller;

import model.imageprocessor.ImageProcessor;
import model.imageprocessor.ImageProcessorModel;
import view.ImageProcessorGUI;
import view.SwingGUIView;

public class ControllerFeaturesImpl implements Features {
  private final ImageProcessorGUI view;
  private final ImageProcessor model;


  public ControllerFeaturesImpl(ImageProcessorGUI view, ImageProcessor model) {
    this.view = view;
    this.model = model;
    view.acceptsFeaturesObject(this);


  }
  @Override
  public void readButtonClick(String btnAction,String imgName) {


  }


  public static void main(String[] args){
    ImageProcessor model = new ImageProcessorModel();
    ImageProcessorGUI view = new SwingGUIView();
    ControllerFeaturesImpl controller = new ControllerFeaturesImpl(view,model);
  }
}
