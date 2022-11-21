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
  private BufferedImage currImg;
  private int[] histogram;
  private String currImgName;
  private String displayText;
  private final JButton[] radioButtons;
  private String storedImagePath;

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

      if (i < 2)
        rGroup1.add(radioButtons[i]);
      else
        rGroup2.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }
    mainPanel.add(radioPanel);

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
  public void setCurrImgName(String currImgName){
    this.currImgName = currImgName;

  }
  @Override
  public void refresh(BufferedImage newImage, int[] histogram) {
    this.currImg = newImage;
    this.image.setIcon(new ImageIcon(currImg));
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
          String actionPerformed = e.getActionCommand();
          JFileChooser fchooser = new JFileChooser(".");
          switch (actionPerformed) {
            case "load":
              int loadRetValue = fchooser.showOpenDialog(SwingGUIView.this);
              if (loadRetValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fchooser.getSelectedFile().getAbsolutePath();
                storedImagePath = filePath.substring(0,
                        filePath.lastIndexOf("/") + 1);
                features.readButtonActionWithFilePath(actionPerformed,
                        filePath,
                        filePath.substring(filePath.lastIndexOf("/") + 1,
                                filePath.lastIndexOf(".")));
              }
              break;
            case "save":
              int saveRetValue = fchooser.showSaveDialog(SwingGUIView.this);
              if (saveRetValue == JFileChooser.APPROVE_OPTION) {
                String newAddedPath = fchooser.getSelectedFile().getAbsolutePath();
                newAddedPath = newAddedPath.substring(storedImagePath.length());
                features.readButtonActionWithFilePath(actionPerformed,
                        newAddedPath,
                        currImgName);
              }
              break;
            case "brighten":
              System.out.println("test");
              break;
            default:
              features.readButtonClick(actionPerformed, currImgName);
          }
          //features.readButtonClick(e.getActionCommand(), currImgName);
        }
      });
    }


  }


}
