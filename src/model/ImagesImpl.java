package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The class model.ImagesImpl reads a given file and creates a 3D array for the given image.
 */
public class ImagesImpl implements Images {

  ImageModelImpl model;

  public ImagesImpl(ImageModelImpl model) {
    this.model = model;
  }

  public ImagesImpl() {
    // beginning constructor when nothing is given
  }

  /**
   * Process the given file and create a 3D array.
   *
   * @param filename a given file that could be png or jpeg
   * @return a 3D array of an image
   */
  public int[][][] readImage(String filename) throws IOException {
    BufferedImage image;
    image = ImageIO.read(new FileInputStream(filename));
    int[][][] pixels = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int color = image.getRGB(j, i);
        Color c = new Color(color);
        pixels[i][j][0] = c.getRed();
        pixels[i][j][1] = c.getGreen();
        pixels[i][j][2] = c.getBlue();
      }
    }
    return pixels;
  }

  /**
   * Process the given file and create a 3D array.
   *
   * @param image a given image that could be png or jpeg
   * @return a 3D array of an image
   */
  public int[][][] readBufferedImage(BufferedImage image) throws IOException {
    int[][][] pixels = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int color = image.getRGB(j, i);
        Color c = new Color(color);
        pixels[i][j][0] = c.getRed();
        pixels[i][j][1] = c.getGreen();
        pixels[i][j][2] = c.getBlue();
      }
    }
    return pixels;
  }

  @Override
  public void saveImage(int[][][] pixels, String filename) throws IOException {
    BufferedImage output = new BufferedImage(pixels[0].length, pixels.length,
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int r = pixels[i][j][0];
        int g = pixels[i][j][1];
        int b = pixels[i][j][2];

        int color = (r << 16) + (g << 8) + b; //color is represented as one int
        output.setRGB(j, i, color);
      }
    } //type could be png, jpeg, ppm
    String type = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(output, type, new File(filename));
  }


  @Override
  public int[][][] filter(String filename, double[][] filter) {
    if (filter.length % 2 == 0) {
      throw new IllegalArgumentException("Has to be odd.");
    }
    int[][][] array2 = model.makeCopy(filename);
    for (int i = 0; i < array2.length; i++) {
      for (int j = 0; j < array2[0].length; j++) {
        array2[i][j] = pixelColor(createKernel(i, j,
                model.getPictures().get(filename), filter.length), filter);
      }
    }

    return check(array2);
  }

  /**
   * checks to make sure the color isn't greater than min.
   * @param pixel 3d array for each pixel
   * @return corrected 3d array for each pixel
   */
  protected int[][][] check(int[][][] pixel) {
    int min = 0;
    int max = 255;
    for (int i = 0; i < pixel.length; i++) {
      for (int j = 0; j < pixel[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          if (pixel[i][j][k] < min) {
            pixel[i][j][k] = min;
          } else if (pixel[i][j][k] > max) {
            pixel[i][j][k] = max;
          }
        }
      }
    }
    return pixel;
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

  /**
   * Get a buffered image from an int[][][].
   *
   * @param rgb the image data as a 3D array of integers. The dimensions are row, col and channel
   *            respectively
   * @throws IOException if the file cannot be written to the provided path
   */
  public static BufferedImage getBufferedImage(int[][][] rgb) throws IOException {
    BufferedImage output = new BufferedImage(rgb[0].length, rgb.length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < rgb.length; i++) {
      for (int j = 0; j < rgb[0].length; j++) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    return output;
  }


}

