package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageModel;
import model.ImagesImpl;
import view.ViewModel;

/**
 * Controller class for the images to change images.
 */
public class ImageController implements Features {
  protected ImageModel model;
  private ViewModel view;
  private Readable readable;
  private Scanner sc;

  /**
   * takes in the model, view, and readable to allow changes to be made.
   *
   * @param model    the image
   * @param view     the view of the image
   * @param readable input
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public ImageController(ImageModel model, ViewModel view, Readable readable)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Model and/or view and/or readable is null");
    }

    this.model = model;
    this.view = view;
    this.sc = new Scanner(readable);

  }

  /**
   * run method which controls all the methods that a picture can do.
   *
   * @throws IllegalStateException if readable doesn't have next
   */
  public void run() throws IllegalStateException {
    this.renderMessage("Welcome to Image Processing Program" + System.lineSeparator());
    this.renderMessage("Start by loading an image in" + System.lineSeparator());
    String userInstruction = "";
    while (sc.hasNext()) {
      userInstruction = sc.next();
      switch (userInstruction) {
        case "quit": {
          this.renderMessage("System Quit" + System.lineSeparator());
          break;
        }
        case "load": {
          loadImage(sc.next(), sc.next());
          break;
        }
        case "horizontal-flip": {
          this.flipHorizontal(sc.next(), sc.next());
          break;
        }
        case "vertical-flip": {
          this.flipVertical(sc.next(), sc.next());
          break;
        }
        case "save": {
          String dest = sc.next();
          String input = sc.next();
          this.saveImage(dest, input);
          break;
        }
        case "red-component": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("red-component", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "green-component": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("green-component", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "text": {
          try {
            Reader in = new BufferedReader(new FileReader(sc.next()));
            this.sc = new Scanner(in);
          } catch (FileNotFoundException e) {
            this.renderMessage("Invalid Command! Try again" + System.lineSeparator());
          }
          break;
        }
        case "blue-component": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("blue-component", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "luma-component": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("luma-component", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "greyscale": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("greyscale", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "intensity-component": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("intensity-component", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "sepia": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("sepia", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "value-component": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.greyscaleImage("value-component", input, output);
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
          }
          break;
        }
        case "blur": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.blur(input, output);
            break;
          } catch (NumberFormatException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
            break;
          }
        }
        case "convert": {
          try {
            String input = sc.next();
            String filetype = sc.next();
            String fileoutput = sc.next();
            this.converter(input, filetype, fileoutput);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;
        }
        case "sharpen": {
          try {
            String input = sc.next();
            String output = sc.next();
            this.sharpen(input, output);
            break;
          } catch (NumberFormatException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
            break;
          }
        }
        case "brighten": {
          try {
            int bright = Integer.parseInt(sc.next());
            String input = sc.next();
            String output = sc.next();
            this.brightenImage(input, output, bright);
            break;
          } catch (NumberFormatException e) {
            this.renderMessage("Invalid input!" + System.lineSeparator());
            break;
          }
        }
        default:
          this.renderMessage("Invalid Command! Try again" + System.lineSeparator());
          break;
      }
    }
  }

  @Override
  public void sharpen(String input, String output) {
    if (model.getPictures().containsKey(input)) {
      this.model.addToMap(output, model.sharpen(input));
    } else {
      this.renderMessage("File not found!");
    }
    if (model.getPictures().containsKey(output)) {
      this.renderMessage("- Image sharpened" + System.lineSeparator());
    } else {
      this.renderMessage("Image couldn't be sharpened" + System.lineSeparator());
    }
  }

  @Override
  public void blur(String filename, String fileoutput) {
    if (model.getPictures().containsKey(filename)) {
      this.model.addToMap(fileoutput, model.blur(filename));
    } else {
      this.renderMessage("File not found!");
    }
    if (model.getPictures().containsKey(fileoutput)) {
      this.renderMessage("- Image blurred" + System.lineSeparator());
    } else {
      this.renderMessage("Image couldn't be blurred" + System.lineSeparator());
    }
  }

  //if jpeg or png use the model.ImagesImpl class
  @Override
  public void loadImage(String filename, String fileOutput) {
    if (filename.substring(filename.indexOf(".") + 1).equals("png")
            || filename.substring(filename.indexOf(".") + 1).equals("jpg")
            || filename.substring(filename.indexOf(".") + 1).equals("jpeg")
            || filename.substring(filename.indexOf(".") + 1).equals("bmp")) {
      try {
        model.addToMap(fileOutput, new ImagesImpl().readImage(filename));
      } catch (IOException e) {
        this.renderMessage(e.getMessage());
      }
      return;
    } else {
      Scanner sc3 = new Scanner(System.in);
      try {
        sc3 = new Scanner(new FileInputStream(filename));
      } catch (FileNotFoundException e) {
        this.renderMessage("File " + filename + " not found!" + System.lineSeparator());
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc3.hasNextLine()) {
        String s = sc3.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }

      //now set up the scanner to read from the string we just built
      Scanner sc2 = new Scanner(builder.toString());
      String token;
      token = sc2.next();
      int width = sc2.nextInt();
      int height = sc2.nextInt();
      int maxValue = sc2.nextInt();
      model.getMax(maxValue);
      int[][][] array = new int[height][width][3];
      for (int i = 0; i < height; i++) {
        int[][] row = new int[width][3];
        array[i] = row;
        for (int j = 0; j < width; j++) {
          int[] col = new int[3];
          array[i][j] = col;
          int r = sc2.nextInt();
          int g = sc2.nextInt();
          int b = sc2.nextInt();
          array[i][j][0] = r;
          array[i][j][1] = g;
          array[i][j][2] = b;
        }
      }

      this.model.addToMap(fileOutput, array);
    }
    if (model.getPictures().containsKey(fileOutput)) {
      this.renderMessage("- Image loaded" + System.lineSeparator());
    } else {
      this.renderMessage("Image couldn't be loaded" + System.lineSeparator());
    }
  }

  @Override
  public void saveImage(String filedest, String filename) {
    if (filedest.substring(filedest.indexOf(".") + 1).equals("png")
            || filedest.substring(filedest.indexOf(".") + 1).equals("jpeg")
            || filedest.substring(filedest.indexOf(".") + 1).equals("jpg")
            || filedest.substring(filedest.indexOf(".") + 1).equals("bmp")) {
      try {
        new ImagesImpl().saveImage(model.getPictures().get(filename), filedest);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return;
    }
    try {
      StringBuilder file = createString(filename);
      PrintWriter saved = new PrintWriter(filedest);
      saved.write(file.toString());
      saved.close();
    } catch (FileNotFoundException e) {
      this.renderMessage(e.getMessage());
    } catch (NullPointerException e) {
      this.renderMessage("File doesn't exist in system!" + System.lineSeparator());
    }
  }


  /**
   * creates string to help save image.
   *
   * @param filename of the image you want saved
   * @return stringbuilder with the new name
   */
  private StringBuilder createString(String filename) {
    StringBuilder file = new StringBuilder();
    file.append("P3" + System.lineSeparator());
    int height = model.getImageHeight(filename);
    int width = model.getImageWidth(filename);
    file.append(width);
    file.append(" ");
    file.append(height);
    file.append(System.lineSeparator());
    file.append(model.getMax());
    int[][][] array = model.getPictures().get(filename);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        file.append(System.lineSeparator());
        file.append(array[i][j][0]);
        file.append(System.lineSeparator());
        file.append(array[i][j][1]);
        file.append(System.lineSeparator());
        file.append(array[i][j][2]);
      }
    }
    file.append(System.lineSeparator());
    return file;
  }

  @Override
  public void brightenImage(String filename, String fileoutput, int brightness) {
    try {
      if (model.getPictures().containsKey(filename)) {
        this.model.addToMap(fileoutput, model.bright(brightness, filename));
      } else {
        this.renderMessage("File not found!");
      }
      if (model.getPictures().containsKey(fileoutput)) {
        this.renderMessage("- Image brightened" + System.lineSeparator());
      } else {
        this.renderMessage("Image couldn't be brightened" + System.lineSeparator());
      }
    } catch (Exception e) {
      this.renderMessage(e.getMessage());
    }

  }

  @Override
  public void flipHorizontal(String filename, String fileoutput) {
    if (model.getPictures().containsKey(filename)) {
      this.model.addToMap(fileoutput, model.flipHorizontal(filename));
    } else {
      this.renderMessage("File not found!");
    }
    if (model.getPictures().containsKey(fileoutput)) {
      this.renderMessage("- Image flipped horizontally" + System.lineSeparator());
    } else {
      this.renderMessage("Image couldn't be flipped" + System.lineSeparator());
    }
  }

  @Override
  public void flipVertical(String filename, String fileoutput) {
    if (model.getPictures().containsKey(filename)) {
      this.model.addToMap(fileoutput, model.flipVertical(filename));
    } else {
      this.renderMessage("File not found!");
    }
    if (model.getPictures().containsKey(fileoutput)) {
      this.renderMessage("- Image flipped vertically" + System.lineSeparator());
    } else {
      this.renderMessage("Image couldn't be flipped" + System.lineSeparator());
    }
  }


  @Override
  public void greyscaleImage(String c, String filename, String fileoutput) {

    if (model.getPictures().containsKey(filename)) {
      this.model.addToMap(fileoutput, model.greyscale(c, filename));
    } else {
      this.renderMessage("File not found!");
    }
    if (model.getPictures().containsKey(fileoutput)) {
      this.renderMessage("- Image color changed" + System.lineSeparator());
    } else {
      this.renderMessage("Image color couldn't be changed" + System.lineSeparator());
    }
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IllegalStateException if transmission of the board to the data destination fails
   */
  private void renderMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private void converter(String filename, String filetype, String fileoutput) throws IOException {
    String outFormat = "%-17s: %s%n";
    BufferedImage image = ImagesImpl.getBufferedImage(model.getPictures().get(filename));
    File outputFile = Paths.get(fileoutput).toAbsolutePath().toFile();
    System.out.printf(outFormat, "output file", outputFile.getAbsolutePath());
    boolean check = ImageIO.write(image, filetype, outputFile);
    System.out.printf(outFormat, "Image Converted:", check);
  }

  @Override
  public void filter(String filename, double[][] filter) {
    if (filter.length % 2 == 0) {
      throw new IllegalArgumentException("Has to be odd.");
    }
    int width = model.getImageWidth(filename);
    int height = model.getImageHeight(filename);
    int[][][] array = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        array[height][width] = pixelColor(createKernel(i, j,
                model.getPictures().get(filename), filter.length), filter);
      }
    }

  }

  private int[] pixelColor(int[][][] kernel, double[][] filter) {
    int[] pixel = new int[3];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        pixel[0] = pixel[0] + (int) Math.round((kernel[i][j][0] * filter[i][j]));
        pixel[1] = pixel[1] + (int) Math.round((kernel[i][j][1] * filter[i][j]));
        pixel[2] = pixel[2] + (int) Math.round((kernel[i][j][2] * filter[i][j]));
      }
    }
    return pixel;
  }

  private int[][][] createKernel(int row, int col, int[][][] array, int length) {
    int[][][] kernel = new int[length][length][3];
    int limit = (int) Math.floor(length / 2.0);
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        try {
          kernel[i][j][0] = array[i + (row - limit)][j + (col - limit)][0];
          kernel[i][j][1] = array[i + (row - limit)][j + (col - limit)][1];
          kernel[i][j][2] = array[i + (row - limit)][j + (col - limit)][2];
        } catch (IndexOutOfBoundsException ex) {
          kernel[i][j][0] = 0;
          kernel[i][j][1] = 0;
          kernel[i][j][2] = 0;
        }
      }

    }
    return kernel;
  }



}
