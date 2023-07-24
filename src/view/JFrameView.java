package view;

import java.awt.Graphics2D;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageModelImpl;
import model.ImagesImpl;

/**
 * Class for the GUI that allows the users to use the program.
 */
public class JFrameView extends JFrame implements
        ActionListener, ItemListener, ListSelectionListener {

  File f;
  String filename;
  Features features;
  ImageModelImpl model;
  JPanel imagePanel;
  JPanel mainPanel;
  JLabel imageLabel;
  JScrollPane imageScrollPane;
  JScrollPane mainScrollPane;
  JLabel fileOpenDisplay;
  JLabel blurDisplay;
  JLabel sharpenDisplay;
  JLabel fileSaveDisplay;
  JLabel inputDisplay;
  JLabel inputDisplayX;
  JLabel inputDisplayY;
  JLabel optionDisplay;
  JLabel comboboxDisplay;
  Histogram hist;

  /**
   * Constructor for the class that allows the model to be used.
   * @param features the different features available for the user
   * @param model the group of pictures the user is using
   */
  public JFrameView(Features features, ImageModelImpl model) {
    super();
    this.model = model;
    this.features = features;
    setTitle("Swing features");
    setSize(1500, 850);
    File f = new File("welcome.jpg");
    mainPanel = new JPanel();


    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    //show an image with a scrollbar


    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanel, LEFT_ALIGNMENT);


    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("");
    fileopenPanel.add(fileOpenDisplay, LEFT_ALIGNMENT);

    //file blur
    JPanel blurPanel = new JPanel();
    blurPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blurPanel);
    JButton blurButton = new JButton("blur");
    blurButton.setActionCommand("blur");
    blurButton.addActionListener(this);
    blurPanel.add(blurButton);
    blurDisplay = new JLabel("");
    blurPanel.add(blurDisplay, LEFT_ALIGNMENT);

    //file sharpen
    JPanel sharpenPanel = new JPanel();
    sharpenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sharpenPanel);
    JButton sharpenButton = new JButton("sharpen");
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.addActionListener(this);
    sharpenPanel.add(sharpenButton);
    sharpenDisplay = new JLabel("");
    sharpenPanel.add(sharpenDisplay, LEFT_ALIGNMENT);

    //brightPanel input dialog
    JPanel brightPanel = new JPanel();
    brightPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(brightPanel, LEFT_ALIGNMENT);

    JButton brightButton = new JButton("brighten");
    brightButton.setActionCommand("bright");
    brightButton.addActionListener(this);
    brightPanel.add(brightButton);

    inputDisplay = new JLabel("");
    brightPanel.add(inputDisplay, LEFT_ALIGNMENT);

    //JOptionsPane options dialog
    JPanel optionsDialogPanel = new JPanel();
    //optionsDialogPanel.setLayout(new BoxLayout(optionsDialogPanel, BoxLayout.X_AXIS));
    dialogBoxesPanel.add(optionsDialogPanel, LEFT_ALIGNMENT);

    JButton optionButton = new JButton("Colors");
    optionButton.setActionCommand("color");
    optionButton.addActionListener(this);
    optionsDialogPanel.add(optionButton, LEFT_ALIGNMENT);

    optionDisplay = new JLabel("");
    optionsDialogPanel.add(optionDisplay, LEFT_ALIGNMENT);

    //downscale input dialog
    JPanel downscaleP = new JPanel();
    downscaleP.setLayout(new FlowLayout());
    dialogBoxesPanel.add(downscaleP, LEFT_ALIGNMENT);

    JButton downButton = new JButton("downscale");
    downButton.setActionCommand("downscale");
    downButton.addActionListener(this);
    downscaleP.add(downButton);

    inputDisplayX = new JLabel("");
    inputDisplayY = new JLabel("");
    downscaleP.add(inputDisplayX, LEFT_ALIGNMENT);
    downscaleP.add(inputDisplayY, LEFT_ALIGNMENT);


    //combo boxes

    JPanel comboboxPanel = new JPanel();
    //comboboxPanel.setBorder(BorderFactory.createTitledBorder("Flip"));
    // comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.X_AXIS));
    dialogBoxesPanel.add(comboboxPanel, LEFT_ALIGNMENT);
    comboboxDisplay = new JLabel("");
    comboboxPanel.add(comboboxDisplay);
    String[] options = {"null", "horizontal", "vertical"};
    JComboBox<String> combobox = new JComboBox<String>();
    //the event listener when an option is selected
    combobox.setActionCommand("flip");
    combobox.addActionListener(this);
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }

    comboboxPanel.add(combobox, LEFT_ALIGNMENT);


    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay, LEFT_ALIGNMENT);

    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Welcome to Image Processing Program\n" +
            "Start by loading an image in"));
    //imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);
    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane();
    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    setImage(f);
    hist = new Histogram(mainPanel, model, filename);

  }


  @Override
  public void actionPerformed(ActionEvent e) throws IllegalStateException {
    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "bmp", "jpeg", "ppm", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(JFrameView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
          setImage(f);
          String filename2 = f.getName();
          filename = filename2.substring(0, filename2.indexOf("."));
          features.loadImage("res/" + filename2, filename);
          hist.filename = filename;
        }
      }
      break;
      case "sharpen": {
        filename = filename + "-sharpen";
        features.sharpen(filename.substring(0, filename.length() - 8), filename);
        setImageVisible(filename);
        System.out.println(filename);
      }
      break;
      case "bright":
        filename = filename + "-brighten";
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter your brightness"));
        try {
          int brightness = Integer.parseInt(inputDisplay.getText());
          features.brightenImage(filename.substring(0, filename.length() - 9),
                  filename, brightness);
          setImageVisible(filename);
          System.out.println(filename);
        } catch (NumberFormatException exception) {
          exception.getMessage();
        }
        break;
      case "downscale":
        inputDisplayX.setText(JOptionPane.showInputDialog("Enter new width"));
        inputDisplayY.setText(JOptionPane.showInputDialog("Enter new height"));
        try {
          int x = Integer.parseInt(inputDisplayX.getText());
          int y = Integer.parseInt(inputDisplayY.getText());
          setImageVisible(filename, x, y);
          filename = filename + "-downscaled";
          System.out.println(filename);
        } catch (NumberFormatException exception) {
          //throw new IllegalStateException("Invalid brightness");
        }
        break;
      case "blur": {
        filename = filename + "-blur";
        features.blur(filename.substring(0, filename.length() - 5), filename);
        setImageVisible(filename);
        System.out.println(filename);
      }
      break;
      case "flip":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          if (box.getSelectedItem().equals("horizontal")) {
            filename = filename + "-horizontal-flip";
            features.flipHorizontal(filename.substring(0, filename.length() - 16), filename);
            setImageVisible(filename);
          } else if (box.getSelectedItem().equals("vertical")) {
            filename = filename + "-vertical-flip";
            features.flipVertical(filename.substring(0, filename.length() - 14), filename);
            setImageVisible(filename);
          }
        }
        break;
      case "color": {
        String[] options = {"red-component", "blue-component", "green-component",
          "sepia", "luma-component", "intensity-component", "value-component", "greyscale"};
        int retvalue = JOptionPane.showOptionDialog(JFrameView.this,
                "Color",
                "Options", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[7]);
        optionDisplay.setText(options[retvalue]);
        filename = filename + "-" + options[retvalue];
        System.out.println(options[retvalue]);
        System.out.println(filename);
        System.out.println(filename.substring(0,
                filename.length() - options[retvalue].length() - 1));
        features.greyscaleImage(options[retvalue], filename.substring(0,
                filename.length() - options[retvalue].length() - 1), filename);
        setImageVisible(filename);
        System.out.println(filename);
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(JFrameView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
          String filename2 = f.getName();
          features.saveImage(filename2, filename);
          System.out.println(filename2);
          System.out.println(filename);

        }
      }
      break;
      default: System.out.print("Invalid Command!");
    }
  }

  private void setImage(File f) {
    String images = f.getAbsolutePath();
    imageLabel.setIcon(new ImageIcon(images));
    imageScrollPane.setPreferredSize(new Dimension(400, 400));
    imagePanel.add(imageScrollPane);
    filename = images.substring(0, images.indexOf(".") + 1);
    try {
      model.addToMap(filename, new ImagesImpl().readImage(images));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private void setImageVisible(String filename) {
    try {
      //model.getPictures().put(filename, model.blur(filename));

      BufferedImage image = ImagesImpl.getBufferedImage(model.getPictures().get(filename));
      //image = downscale(image,400, 400 );
      imageLabel.setIcon(new ImageIcon(image));
      imageScrollPane.setPreferredSize(new Dimension(400, 400));
      imagePanel.add(imageScrollPane);
      hist.filename = filename;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void setImageVisible(String filename, int x, int y) {
    try {
      //model.getPictures().put(filename, model.blur(filename));

      BufferedImage image = ImagesImpl.getBufferedImage(model.getPictures().get(filename));
      image = downscale(image, x, y);
      imageLabel.setIcon(new ImageIcon(image));
      imageScrollPane.setPreferredSize(new Dimension(400, 400));
      imagePanel.add(imageScrollPane);
      hist.filename = filename;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // unused method
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // unused method
  }

  /**
   * allows the user to downscale their image.
   * @param originalImage the image they are using
   * @param width the new width
   * @param height the new height
   * @return a new image that has been downscaled
   * @throws IOException if given invalid image
   */
  private BufferedImage downscale(BufferedImage originalImage, int width, int height)
          throws IOException {

    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, width, height, null);
    graphics2D.dispose();
    model.addToMap(filename + "-downscaled", new ImagesImpl().readBufferedImage(resizedImage));
    return resizedImage;


  }


}
