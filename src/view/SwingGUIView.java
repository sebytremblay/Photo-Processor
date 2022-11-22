package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.Features;

public class SwingGUIView extends JFrame implements ImageProcessorGUI {

  private final JPanel mainPanel;
  private final JLabel image;
  private String currImgName = "curr-img";
  private String displayText;
  private JLabel displayLabel;
  private final JButton[] radioButtons;
  private final JTextField brightenByField;
  private final Histogram histogramPanel;
  private final JPanel imagePanel;

  public static void main(String[] args) {
    ImageProcessorGUI view = new SwingGUIView();
  }

  public SwingGUIView() {
    super("Image Processor GUI");
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();

    // buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setSize(200, 512);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Image Transformations"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    String[] commands = {"load", "save",
            "red-component", "green-component", "blue-component",
            "value-component", "intensity-component", "luma-component",
            "horizontal-flip", "vertical-flip", "sharpen", "greyscale", "sepia", "brighten"};
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

    displayLabel = new JLabel("");
    radioPanel.add(displayLabel);

    int histogramPanelSize = 256;
    this.histogramPanel = new Histogram();
    histogramPanel.setMinimumSize(new Dimension(histogramPanelSize, histogramPanelSize));
    histogramPanel.setPreferredSize(new Dimension(histogramPanelSize, histogramPanelSize));
    histogramPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    // display image with
    int imagePanelSize = 712;
    this.imagePanel = new JPanel();
    this.image = new JLabel();
    imagePanel.add(this.image);
    imagePanel.setBorder(BorderFactory.createTitledBorder("The Working Image"));
    JScrollPane imageScroll = new JScrollPane(imagePanel);
    imageScroll.setMinimumSize(new Dimension(imagePanelSize, imagePanelSize));
    imageScroll.setSize(new Dimension(imagePanelSize, imagePanelSize));
    imageScroll.setPreferredSize(new Dimension(imagePanelSize, imagePanelSize));

    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    constraints.gridy = 0;
    mainPanel.add(radioPanel, constraints);

    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.ipadx = 5;
    mainPanel.add(histogramPanel, constraints);

    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    mainPanel.add(imageScroll, constraints);

    JScrollPane mainScroll = new JScrollPane(mainPanel);
    this.add(mainScroll);
    this.pack();
    this.setVisible(true);
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
