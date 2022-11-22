package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.Features;

public class SwingGUIView extends JFrame implements ImageProcessorGUI {

  private JPanel mainPanel;
  private JLabel image;
  private String currImgName = "curr-img";
  private JLabel displayLabel;
  private JButton[] radioButtons;
  private JTextField brightenByField;
  private Histogram histogramPanel;
  private JScrollPane imageScroll;
  private JScrollPane mainScroll;
  private JPanel imagePanel;
  private JPanel radioPanel;


  public SwingGUIView() {
    // Layout stuff
    super("Image Processor GUI");
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Defines and sets up all feature buttons
    initFeatureButtons();

    // Sets up display for the histogram
    initHistogramDisplay();

    // Sets up the display for the loaded image
    initImageDisplay();

    // Adds all JFrame components to our window and organizes them
    organizeWindowLayout(constraints);

    this.mainScroll = new JScrollPane(mainPanel);
    this.add(mainScroll);
    this.pack();
    this.setVisible(true);
  }

  private void initFeatureButtons() {
    // Initializes panel defaults
    this.radioPanel = new JPanel();
    radioPanel.setSize(275, 512);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Image Transformations"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    // Defines all supported commands
    String[] commands = {"load", "save",
            "red-component", "green-component", "blue-component",
            "value-component", "intensity-component", "luma-component",
            "horizontal-flip", "vertical-flip", "sharpen", "greyscale", "sepia", "brighten"};
    radioButtons = new JButton[commands.length];

    // Makes add feature buttons
    for (int i = 0; i < commands.length; i += 1) {
      radioButtons[i] = new JButton(commands[i]);
      radioButtons[i].setActionCommand(commands[i]);

      radioPanel.add(radioButtons[i]);
    }

    // Text input to enter brightness amount
    brightenByField = new JTextField("");
    brightenByField.setPreferredSize(new Dimension(80, 30));
    brightenByField.setMinimumSize(brightenByField.getPreferredSize());
    brightenByField.setMaximumSize(brightenByField.getPreferredSize());
    radioPanel.add(brightenByField);

    // Text label for error logging
    displayLabel = new JLabel("");
    radioPanel.add(displayLabel);
  }

  private void initHistogramDisplay() {
    int histogramPanelSize = 256;
    this.histogramPanel = new Histogram();
    histogramPanel.setMinimumSize(new Dimension(histogramPanelSize, histogramPanelSize));
    histogramPanel.setPreferredSize(new Dimension(histogramPanelSize, histogramPanelSize));
    histogramPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }

  private void initImageDisplay() {
    int imagePanelSize = 756;
    this.imagePanel = new JPanel();
    this.image = new JLabel();
    imagePanel.add(this.image);
    imagePanel.setBorder(BorderFactory.createTitledBorder("The Working Image"));
    this.imageScroll = new JScrollPane(imagePanel);
    this.imageScroll.setMinimumSize(new Dimension(imagePanelSize, imagePanelSize));
    this.imageScroll.setPreferredSize(new Dimension(imagePanelSize, imagePanelSize));
  }

  private void organizeWindowLayout(GridBagConstraints constraints) {
    // adds feature buttons to grid
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    constraints.gridy = 0;
    mainPanel.add(radioPanel, constraints);

    // adds histogram to grid
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.ipadx = 2;
    mainPanel.add(histogramPanel, constraints);

    // adds image to grid
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.ipadx = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    mainPanel.add(imageScroll, constraints);
  }

  @Override
  public void setCurrImgName(String currImgName) {
    this.currImgName = currImgName;
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
              if (actionPerformed != null) {
                features.readButtonClick(actionPerformed, currImgName);
              }
          }
        }
      });
    }
  }
}
