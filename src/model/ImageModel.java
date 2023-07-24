package model;

import java.util.Map;

/**
 * interface of all the methods used to change the model.
 */
public interface ImageModel {

  /**
   * gets the width of the given name.
   *
   * @param name of the image that you want the width for
   * @return an integer of the width
   */
  int getImageWidth(String name);

  /**
   * gets the height of the given name.
   *
   * @param name of the image that you want the height for
   * @return an integer of the height
   */
  int getImageHeight(String name);

  /**
   * makes copy of the array of the given name.
   *
   * @param name of the image that you want the color array of
   * @return an array of ints with the same value as the given name
   */
  int[][][] makeCopy(String name);

  /**
   * adds file to the list of files after being loaded.
   *
   * @param filename name you want added into the map
   * @param pixel    the color scheme of that file
   */
  void addToMap(String filename, int[][][] pixel);

  /**
   * changes greyscale color of the picture.
   *
   * @param c    is the color type you want changed
   * @param name is the name of the image you want changed
   * @return a list of the new changed values in the color array
   */
  int[][][] greyscale(String c, String name);

  /**
   * gets the list of pictures that have been loaded.
   *
   * @returns the map of pictures that have been loaded
   */
  Map<String, int[][][]> getPictures();

  /**
   * adds brightness value to make the photo darker or brighter.
   *
   * @param brightness level of brightness you want to increment the image by
   * @param name       image you want changed
   * @return a list of new changed values in the color array
   */
  int[][][] bright(int brightness, String name);

  /**
   * gets the maximum value of color in the image.
   *
   * @param maxValue the maximum value of color
   */
  public void getMax(int maxValue);

  /**
   * gets the maximum value of the image.
   *
   * @return an integer of the max value
   */
  int getMax();

  /**
   * flips the image vertically.
   *
   * @param name of the image you want changed
   * @return a list of new changed values in the array
   */
  int[][][] flipVertical(String name);

  /**
   * flips the image horizontally.
   *
   * @param name of the image you want changed
   * @return a list of new changed values in the array
   */
  int[][][] flipHorizontal(String name);

  /**
   * blurs an image.
   * @param input the image you want changed
   * @return a new list of blurred pixels
   */
  int[][][] blur(String input);

  /**
   * sharpens an image.
   * @param input the image you want changed
   * @return a new list of sharpened pixels
   */
  int[][][] sharpen(String input);
}