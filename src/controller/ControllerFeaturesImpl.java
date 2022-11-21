package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ColorTransformationCommand;
import controller.commands.DisplayComponent;
import controller.commands.Flip;
import controller.commands.KernelCommand;
import controller.commands.Load;
import controller.commands.ProcessCommand;
import controller.commands.Save;
import model.imageprocessor.ImageProcessor;
import model.imageprocessor.ImageProcessorModel;
import model.operations.FlipImage;
import model.operations.VisualizeBlue;
import model.operations.VisualizeBrighten;
import model.operations.VisualizeGreen;
import model.operations.VisualizeIntensity;
import model.operations.VisualizeLuma;
import model.operations.VisualizeRed;
import model.operations.VisualizeValue;
import view.ImageProcessorGUI;
import view.SwingGUIView;

public class ControllerFeaturesImpl implements Features {
  private final ImageProcessorGUI view;
  private final ImageProcessor model;
  private final Map<String, Function<Scanner, ProcessCommand>> commands;
  private final Appendable output;

  public ControllerFeaturesImpl(ImageProcessorGUI view, ImageProcessor model) {
    this.view = view;
    this.model = model;
    this.commands = new HashMap<String, Function<Scanner, ProcessCommand>>();
    this.output = new StringBuilder();

    double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}};
    double[][] sharpenKernel = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
    double[][] greyscaleTrans = {{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    double[][] sepiaTrans = {{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};


    commands.put("load", s -> new Load(s.next(), s.next(), output));
    commands.put("save", s -> new Save(s.next(), s.next(), output));
    commands.put("red-component", s -> new DisplayComponent(new VisualizeRed(),
            s.next(), s.next(), output));
    commands.put("blue-component", s -> new DisplayComponent(new VisualizeBlue(), s.next(),
            s.next(), output));
    commands.put("green-component", s -> new DisplayComponent(new VisualizeGreen(), s.next(),
            s.next(), output));
    commands.put("value-component", s -> new DisplayComponent(new VisualizeValue(), s.next(),
            s.next(), output));
    commands.put("intensity-component", s -> new DisplayComponent(
            new VisualizeIntensity(), s.next(),
            s.next(), output));
    commands.put("luma-component", s -> new DisplayComponent(new VisualizeLuma(), s.next(),
            s.next(), output));
    commands.put("horizontal-flip", s -> new Flip(
            new FlipImage(ImageProcessor.Direction.Horizontal), s.next(), s.next(), output));
    commands.put("vertical-flip", s -> new Flip(
            new FlipImage(ImageProcessor.Direction.Vertical), s.next(), s.next(), output));
    commands.put("brighten", s -> new DisplayComponent(
            new VisualizeBrighten(s.nextInt()), s.next(), s.next(), output));
    commands.put("blur", s -> new KernelCommand(s.next(), s.next(),
            blurKernel, output));
    commands.put("sharpen", s -> new KernelCommand(s.next(), s.next(),
            sharpenKernel, output));
    commands.put("greyscale", s -> new ColorTransformationCommand(s.next(), s.next(),
            greyscaleTrans, output));
    commands.put("sepia", s -> new ColorTransformationCommand(s.next(), s.next(),
            sepiaTrans, output));

    view.acceptsFeaturesObject(this);


  }

  @Override
  public void readButtonClick(String btnAction, String imgName) {
    readButtonClick(btnAction, imgName, imgName);
  }

  //TODO ADD AN IMAGE NAME PARAMETER
  @Override
  public void readButtonClick(String btnAction, String filePath, String imgName) {
    String inputString = btnAction + " " + filePath + " " + imgName;
    update(inputString, imgName);

  }

  }


}
