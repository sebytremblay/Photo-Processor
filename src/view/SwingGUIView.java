package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Features;

public class SwingGUIView extends JFrame implements ImageProcessorGUI {

  private final JPanel mainPanel;
  private final JScrollPane imagePane;
  private final JLabel image;
  private String currImgName = "curr-img";
  private String displayText;
  private JLabel displayLabel;
  private final JButton[] radioButtons;
  private final JTextField brightenByField;
  private final Histogram histogramPanel;

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

      radioPanel.add(radioButtons[i]);
    }

    brightenByField = new JTextField();
    brightenByField.setPreferredSize(new Dimension(80, 30));
    brightenByField.setMinimumSize(brightenByField.getPreferredSize());
    brightenByField.setMaximumSize(brightenByField.getPreferredSize());
    radioPanel.add(brightenByField);

    displayLabel = new JLabel("MESSAGE");
    radioPanel.add(displayLabel);

    mainPanel.add(radioPanel);

    this.histogramPanel = new Histogram();
    JLabel displayLabel2 = new JLabel("histogram");
    histogramPanel.add(displayLabel2);
    histogramPanel.setPreferredSize(new Dimension(500, 500));

    mainPanel.add(histogramPanel);

    // display image with
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("The Working Image"));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);


    this.image = new JLabel();
    //image.setIcon(new ImageIcon(currImg));
    imagePanel.add(this.image);

    this.setVisible(true);
  }
  @Override
  public void setCurrImgName(String currImgName) {
    //this.currImgName = currImgName;

  }

  @Override
  public void setImage(BufferedImage img) {
    this.image.setIcon(new ImageIcon(img));
  }

  @Override
  public void setHistogram(int[][] histograms) {
    this.histogramPanel.setHistograms(histograms);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.displayLabel.setText(message);
    this.refresh();
  }


  @Override
  public void acceptsFeaturesObject(Features features) {
    for (JButton button : radioButtons) {
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String actionPerformed = e.getActionCommand();
          JFileChooser fchooser = new JFileChooser(".");
          switch (actionPerformed) {
            case "load":
            case "save":
              int loadRetValue = actionPerformed.equals("load") ?
                      fchooser.showOpenDialog(SwingGUIView.this) :
                      fchooser.showSaveDialog(SwingGUIView.this);
              if (loadRetValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fchooser.getSelectedFile().getAbsolutePath();
                features.readButtonClick(actionPerformed,
                        filePath,
                        currImgName);
              }
              break;
            case "brighten":
              features.takesInTextField("brighten", brightenByField.getText(), currImgName);
              break;
            default:
              features.readButtonClick(actionPerformed, currImgName);
          }
        }
      });
    }


  }


}
