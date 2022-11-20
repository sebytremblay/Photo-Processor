package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Features;
import model.pixel.Pixel;

public class SwingGUIView extends JFrame implements ImageProcessorGUI {

  private final JPanel mainPanel;
  private final JScrollPane imagePane;
  private BufferedImage currImg;
  private Map<Integer, Integer> histogram;
  private String currImgName;
  private String displayText;
  private JButton[] radioButtons;

  public static void main(String[] args) {
    ImageProcessorGUI view = new SwingGUIView();
  }

  public SwingGUIView() {
    super("Image Processor GUI");
    this.setSize(500, 500);

    this.mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    this.imagePane = new JScrollPane(mainPanel);
    add(imagePane);

    // buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Image Transformations"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    ButtonGroup rGroup1 = new ButtonGroup();
    ButtonGroup rGroup2 = new ButtonGroup();

    String[] commands = {"load", "save",
            "red-component", "green-component", "blue-component",
            "value-component", "intensity-component", "luma-component",
            "horizontal-flip", "vertical-flip", "sharpen", "greyscale", "sepia"};
    radioButtons = new JButton[commands.length];

    for (int i = 0; i < commands.length; i += 1) {
      radioButtons[i] = new JButton(commands[i]);
      radioButtons[i].setActionCommand(commands[i]);
    }
    mainPanel.add(radioPanel);

    // display image with
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("The Working Image"));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    String imagePath = "res/20by5.png";

    JLabel image = new JLabel();
    image.setIcon(new ImageIcon(imagePath));
    imagePanel.add(image);

    this.setVisible(true);
  }

  @Override
  public void refresh(BufferedImage newImage, Map<Integer, Integer> histogram) {
    this.currImg = newImage;
    this.histogram = histogram;
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.displayText = message;
    this.repaint();
  }

  @Override
  public void acceptsFeaturesObject(Features features) {
    for (JButton button : radioButtons) {
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          features.readButtonClick(e.getActionCommand(), currImgName);
        }
      });
    }


  }


}
