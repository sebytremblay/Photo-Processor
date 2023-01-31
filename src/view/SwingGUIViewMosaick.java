//package view;
//
//import java.awt.*;
//import java.util.Scanner;
//
//import javax.swing.*;
//
//import controller.ControllerFeatureSet;
//import model.image.ImageModel;
//
//
//public class SwingGUIViewMosaick extends SwingGuiView {
//  private final JTextField mosaickIncrement;
//
//  public SwingGUIViewMosaick() {
//    super();
//
//    // Add the button for mosaick and its input
//    JButton mosaickBtn = new JButton("Mosaick");
//    JLabel mosaickLabel = new JLabel("Mosaick increment:");
//    this.mosaickIncrement = new JTextField();
//    mosaickBtn.addActionListener(actionEvent -> requestMosaickAction());
//    JPanel mosaickSection = new JPanel();
//    mosaickSection.setLayout(new FlowLayout());
//    mosaickSection.add(mosaickBtn);
//    mosaickSection.add(mosaickLabel);
//    mosaickSection.add(mosaickIncrement);
//    JPanel mosaickPanel = new JPanel();
//    mosaickPanel.add(mosaickSection);
//
//    mosaickPanel.setLayout(new BorderLayout());
//    this.processingActions.add(mosaickPanel);
//    this.repaint();
//  }
//
//  private void requestMosaickAction() {
//    try {
//      this.actionObject.runProcessingCommand("mosaick", IMG_NAME, IMG_NAME,
//              new Scanner(this.mosaickIncrement.getText()));
//    } catch (IllegalArgumentException | IllegalStateException e) {
//      JOptionPane.showMessageDialog(null, e.getMessage());
//    }
//  }
//}
