package view;

import java.awt.image.BufferedImage;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dimension;

import controller.Features;

/**
 * Represents the View for an ImageProcessor.
 */
public class SwingGUIView extends JFrame implements ImageProcessorGUI {

  private final JPanel mainPanel;
  private JLabel image;
  private final String currImgName = "curr-img";
  private JLabel displayLabel;
  private JButton[] radioButtons;
  private JTextField brightenByField;
  private Histogram histogramPanel;
  private JPanel histogramHousingPanel;
  private JScrollPane imageScroll;
  private JPanel radioPanel;
  private JButton brightenButton;

  /**
   * Instantiates the GUI to view.
   */
  public SwingGUIView() {
    // Layout stuff
    super("Image Processor GUI");
    this.mainPanel = new JPanel();
    GridBagConstraints constraints = new GridBagConstraints();

    // Defines and sets up all feature buttons
    initFeatureButtons();

    // Sets up display for the histogram
    initHistogramDisplay();

    // Sets up the display for the loaded image
    initImageDisplay();

    // Adds all JFrame components to our window and organizes them
    organizeWindowLayout(constraints);

    JScrollPane mainScroll = new JScrollPane(mainPanel);
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
    String[] commands = {"load", "save", "red-component", "green-component", "blue-component",
        "value-component", "intensity-component", "luma-component",
        "horizontal-flip", "vertical-flip", "sharpen", "greyscale", "sepia"};
    radioButtons = new JButton[commands.length];

    // Makes add feature buttons
    for (int i = 0; i < commands.length; i += 1) {
      radioButtons[i] = new JButton(commands[i]);
      radioButtons[i].setActionCommand(commands[i]);

      radioPanel.add(radioButtons[i]);
      radioButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    JPanel brightenPanel = new JPanel();

    brightenByField = new JTextField("");
    brightenByField.setPreferredSize(new Dimension(80, 30));
    brightenByField.setMinimumSize(brightenByField.getPreferredSize());
    brightenByField.setMaximumSize(brightenByField.getPreferredSize());

    brightenButton = new JButton("brighten");
    brightenPanel.add(brightenButton);
    brightenPanel.add(brightenByField);
    brightenPanel.setLayout(new BoxLayout(brightenPanel, BoxLayout.X_AXIS));

    radioPanel.add(brightenPanel);
    brightenByField.setAlignmentX(Component.LEFT_ALIGNMENT);
    brightenPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    // Text input to enter brightness amount


    // Text label for error logging
    displayLabel = new JLabel("");
    radioPanel.add(displayLabel);
  }

  private void initHistogramDisplay() {
    int histogramPanelSize = 256;

    this.histogramHousingPanel = new JPanel();
    histogramHousingPanel.setSize(new Dimension(histogramPanelSize + 2,
            histogramPanelSize + 2));
    this.histogramPanel = new Histogram();
    histogramPanel.setMinimumSize(new Dimension(histogramPanelSize, histogramPanelSize));
    histogramPanel.setPreferredSize(new Dimension(histogramPanelSize, histogramPanelSize));

    histogramHousingPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramHousingPanel.add(histogramPanel);
  }

  private void initImageDisplay() {
    int imagePanelSize = 756;
    JPanel imagePanel = new JPanel();
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
    mainPanel.add(histogramHousingPanel, constraints);

    // adds image to grid
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    mainPanel.add(imageScroll, constraints);
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
      button.addActionListener(e -> {
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
          default:
            if (actionPerformed != null) {
              features.readButtonClick(actionPerformed, currImgName);
            }
        }

      });
    }
    brightenButton.addActionListener(e -> features.takesInTextField("brighten",
            brightenByField.getText(), currImgName));
  }
}
