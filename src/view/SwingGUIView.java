package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

  private JButton resizeButton;

  private JButton popupButton;

  private JPanel popup;
  private JLabel imagePreview;
  private JScrollPane pane;
  private String lastCommand;

  private String lastField;

  private BufferedImage currImg;
  private Features features;

  private boolean changedInLastSecond;
  private long timeOfLastChange;
  private javax.swing.Timer t;


  /**
   * Instantiates the GUI to view.
   */
  public SwingGUIView() {
    // Layout stuff
    super("Image Processor GUI");
    this.mainPanel = new JPanel();
    GridBagConstraints constraints = new GridBagConstraints();
    mainPanel.setLayout(new GridBagLayout());
    //this.setSize(1200, 1200);

    // Defines and sets up all feature buttons
    initFeatureButtons();

    // Sets up display for the histogram
    initHistogramDisplay();

    // Sets up the display for the loaded image
    initImageDisplay();

    // Adds all JFrame components to our window and organizes them
    organizeWindowLayout(constraints);

    this.add(mainPanel);
    this.pack();
    this.setVisible(true);
    organizeWindowLayout(constraints);
  }

  private void initFeatureButtons() {
    // Initializes panel defaults
    this.radioPanel = new JPanel();
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
    //BRIGHTEN BUTTON
    JPanel brightenPanel = new JPanel();
    brightenByField = new JTextField("");
    brightenByField.setPreferredSize(new Dimension(60, 30));
    brightenByField.setMinimumSize(brightenByField.getPreferredSize());
    brightenButton = new JButton("brighten");

    brightenPanel.add(brightenButton);
    brightenPanel.add(brightenByField);
    brightenPanel.setLayout(new BoxLayout(brightenPanel, BoxLayout.X_AXIS));

    radioPanel.add(brightenPanel);
    brightenByField.setAlignmentX(Component.LEFT_ALIGNMENT);
    brightenPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

    //RESIZE BUTTON
    resizeButton = new JButton("resize");
    radioPanel.add(resizeButton);


    // Text label for error logging
    displayLabel = new JLabel("");
    radioPanel.add(displayLabel);

    //Button for popup window
    popupButton = new JButton("Open Preview");
    radioPanel.add(popupButton);
    popup = new JPanel();
    popup.setBorder(BorderFactory.createTitledBorder("The Working Image"));
    popup.setPreferredSize(new Dimension(200, 200));
    imagePreview = new JLabel();

    pane = new JScrollPane(imagePreview);
    pane.setPreferredSize(new Dimension(200, 200));
    pane.setSize(200, 200);

    changedInLastSecond = false;

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
    int imagePanelSize = 800;
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
    //constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridheight = 2;
    mainPanel.add(radioPanel);

    // adds histogram to grid
    constraints.fill = GridBagConstraints.NONE;
    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.gridheight = 1;
    mainPanel.add(histogramHousingPanel, constraints);

    // adds image to grid
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    mainPanel.add(imageScroll, constraints);

    constraints.fill = GridBagConstraints.NONE;
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;

    Panel contain = new Panel();
    //pane.setVisible(false);
    contain.setPreferredSize(new Dimension(200, 200));
    contain.setSize(200, 200);
    contain.setMaximumSize(new Dimension(200, 200));
    contain.setBackground(Color.BLUE);
    mainPanel.add(contain.add(pane), constraints);
    //pane.setVisible(false);
  }


  @Override
  public void setImage(BufferedImage source) {
    System.out.println("in set image");
    if (pane.isVisible() && (!lastCommand.equals("save") && !lastCommand.equals("load"))) {
      BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
      Graphics g = b.createGraphics();
      g.drawImage(source, 0, 0, null);
      g.dispose();
      this.imagePreview.setIcon(new ImageIcon(b));
      return;
    }
    System.out.println("here");
    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
    Graphics g = b.createGraphics();
    g.drawImage(source, 0, 0, null);
    g.dispose();
    this.imagePreview.setIcon(new ImageIcon(b));
    this.image.setIcon(new ImageIcon(b));
    currImg = source;
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
    this.features = features;
    for (JButton button : radioButtons) {
      button.addActionListener(e -> {
        String actionPerformed = e.getActionCommand();
        JFileChooser fchooser = new JFileChooser(".");
        lastCommand = actionPerformed;
        switch (actionPerformed) {

          case "load":
            pane.setVisible(false);
          case "save":
            int loadRetValueSave = actionPerformed.equals("load") ?
                    fchooser.showOpenDialog(SwingGUIView.this) :
                    fchooser.showSaveDialog(SwingGUIView.this);
            if (loadRetValueSave == JFileChooser.APPROVE_OPTION) {
              String filePath = fchooser.getSelectedFile().getAbsolutePath();
              features.readButtonClickFile(actionPerformed,
                      filePath,
                      currImgName);
            }
            break;
          default:
            if (currImg != null) {
              String mask = "";
              if (pane.isVisible()) {
                mask = "mask";
                features.createMask(currImgName, mask,
                        pane.getVerticalScrollBar().getValue(),
                        pane.getHorizontalScrollBar().getValue());
                this.imagePreview.setIcon(new ImageIcon(currImg));
                features.readButtonClickSaveName(actionPerformed, mask,
                        currImgName, "prev");
                return;
              }
              features.readButtonClick(actionPerformed, mask, currImgName);
            }
        }

      });
    }
    brightenButton.addActionListener(e -> fieldActions());
    resizeButton.addActionListener(e -> features.takesInTextField("resize",
            JOptionPane.showInputDialog("Please enter the width and height in this format" +
                    " '100 100' <width height>)"), "",
            currImgName, currImgName));

    popupButton.addActionListener(e -> popupButtonAction());
    pane.getViewport().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        timeOfLastChange = System.currentTimeMillis();
        if (!changedInLastSecond) {
          changedInLastSecond = true;
          refreshPreviewPanel();
        }
      }
    });
  }

  private void fieldActions() {
    lastCommand = "brighten";
    lastField = brightenByField.getText();
    if (currImg != null) {
      features.createMask(currImgName, "mask",
              pane.getVerticalScrollBar().getValue(),
              pane.getHorizontalScrollBar().getValue());
      this.imagePreview.setIcon(new ImageIcon(currImg));
      features.takesInTextField("brighten",
              brightenByField.getText(), pane.isVisible() ? "mask" : "", currImgName, pane.isVisible() ? "prev" : currImgName);
    }

  }

  private void refreshPreviewPanel() {
    t = new javax.swing.Timer(250, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(timeOfLastChange);
        String[] inv = {"load", "save", "flip-horizontal", "flip-vertical", "resize"};
        if (System.currentTimeMillis() - timeOfLastChange > 250
                && SwingGUIView.this.pane.isVisible()
                && !Arrays.asList(inv).contains(lastCommand)) {
          features.createMask(currImgName, "mask",
                  pane.getVerticalScrollBar().getValue(),
                  pane.getHorizontalScrollBar().getValue());
          SwingGUIView.this.imagePreview.setIcon(new ImageIcon(currImg));
          if (lastCommand.equals("brighten")) {
            features.takesInTextField(lastCommand, lastField, "mask", currImgName, "prev");

          } else {
            features.readButtonClickSaveName(lastCommand, "mask", currImgName, "prev");
          }
          changedInLastSecond = false;
          t.stop();
        }
      }
    });
    t.start();

  }

  private void popupButtonAction() {
    if (currImg != null && currImg.getHeight() >= 200 && currImg.getWidth() >= 200) {
      pane.setVisible(!pane.isVisible());
      popupButton.setText(pane.isVisible() ? "Close Preview" : "Open Preview");
      System.out.println(pane.isVisible());
      this.imagePreview.setIcon(new ImageIcon(currImg));
    }
    this.refresh();
  }
}
