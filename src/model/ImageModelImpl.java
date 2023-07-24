package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * class for the image model that deals with the different types of pictures.
 */
public class ImageModelImpl implements ImageModel {

  // holds list images
  // String name and the int[][][] array of colors
  private Map<String, int[][][]> pictures;

  private int max;

  /**
   * constructor that accounts for the images loaded into it.
   *
   * @param pictures - holds list of pictures with name and array of colors
   */
  public ImageModelImpl(Map<String, int[][][]> pictures) {
    this.pictures = pictures;
  }

  /**
   * empty constructor to add pictures to.
   */
  public ImageModelImpl() {
    pictures = new HashMap<>();
  }

  @Override
  public Map<String, int[][][]> getPictures() {
    Set<Entry<String, int[][][]>> entries = this.pictures.entrySet();
    HashMap<String, int[][][]> copy = (HashMap<String, int[][][]>) entries.stream()
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    return copy;
  }

  @Override
  public int[][][] makeCopy(String name) {
    int height = pictures.get(name).length;
    int width = pictures.get(name)[0].length;
    int[][][] array = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        array[i][j][0] = pictures.get(name)[i][j][0];
        array[i][j][1] = pictures.get(name)[i][j][1];
        array[i][j][2] = pictures.get(name)[i][j][2];
      }
    }
    return array;
  }

  @Override
  public int getImageWidth(String name) throws NullPointerException {
    return pictures.get(name)[0].length;
  }

  @Override
  public int getImageHeight(String name) throws NullPointerException {
    return pictures.get(name).length;
  }

  @Override
  public void addToMap(String name, int[][][] pixel) {
    try {
      this.pictures.put(name, pixel);
    } catch (NullPointerException e) {
      e.getMessage();
    }
  }

  @Override
  public int[][][] flipVertical(String name) {
    int width = pictures.get(name)[0].length;
    int height = pictures.get(name).length;
    int[][][] copy = this.makeCopy(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        copy[(height - 1) - i][j] = pictures.get(name)[i][j];
      }
    }
    return copy;
  }

  @Override
  public int[][][] flipHorizontal(String name) {
    int width = pictures.get(name)[0].length;
    int height = pictures.get(name).length;
    int[][][] copy = this.makeCopy(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        copy[i][(width - 1) - j] = pictures.get(name)[i][j];
      }
    }
    return copy;
  }

  @Override
  public int[][][] greyscale(String c, String name) {
    int width = pictures.get(name)[0].length;
    int height = pictures.get(name).length;
    int[][][] array = this.makeCopy(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = array[i][j][0];
        int g = array[i][j][1];
        int b = array[i][j][2];
        switch (c) {
          case "green-component": {
            r = g;
            b = g;
            break;
          }
          case "blue-component": {
            r = b;
            g = b;
            break;
          }
          case "red-component": {
            g = r;
            b = r;
            break;
          }
          case "luma-component":
          case "greyscale": {
            int luma = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
            r = luma;
            g = luma;
            b = luma;
            break;
          }
          case "sepia": {
            int redsepia = (int) (0.393 * r + 0.769 * g + 0.0189 * b);
            int greensepia = (int) (0.349 * r + 0.686 * g + 0.0168 * b);
            int bluesepia = (int) (0.272 * r + 0.534 * g + 0.0131 * b);
            if (bluesepia > 255) {
              bluesepia = 255;
            }
            if (greensepia > 255) {
              greensepia = 255;
            }
            if (redsepia > 255) {
              redsepia = 255;
            }
            r = redsepia;
            g = greensepia;
            b = bluesepia;
            break;
          }
          case "intensity-component": {
            int intensity = (r + g + b) / 3;
            Math.round(intensity);
            r = intensity;
            g = intensity;
            b = intensity;
            break;
          }
          case "value-component": {
            int max1 = Math.max(r, g);
            int max2 = Math.max(max1, b);
            r = max2;
            g = max2;
            b = max2;
            break;
          }
          default:
            throw new IllegalArgumentException("Unexpected value: " + c);
        }
        array[i][j][0] = r;
        array[i][j][1] = g;
        array[i][j][2] = b;
      }
    }
    return array;
  }

  @Override
  public int[][][] bright(int brightness, String name) throws IllegalArgumentException {
    int width = pictures.get(name)[0].length;
    int height = pictures.get(name).length;
    int[][][] array = this.makeCopy(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = array[i][j][0];
        int g = array[i][j][1];
        int b = array[i][j][2];
        r += brightness;
        g += brightness;
        b += brightness;
        array[i][j][0] = r;
        array[i][j][1] = g;
        array[i][j][2] = b;
      }
    }
    return new ImagesImpl(this).check(array);
  }

  @Override
  public int[][][] blur(String filename) throws IllegalArgumentException {
    double[][] blur = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    return new ImagesImpl(this).filter(filename, blur);

  }

  @Override
  public int[][][] sharpen(String filename) throws IllegalArgumentException {
    double[][] sharpen = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};

    return new ImagesImpl(this).filter(filename, sharpen);
  }

  @Override
  public void getMax(int maxValue) {
    this.max = maxValue;
  }

  @Override
  public int getMax() {
    return max;
  }


}