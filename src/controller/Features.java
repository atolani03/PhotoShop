package controller;

/**
 * features class to have the methods that the user can make.
 */
public interface Features {

  /**
   * Sharpens image.
   * @param input - file loaded
   * @param output - file outputted
   */
  void sharpen(String input, String output);

  /**
   * Blur image.
   * @param filename - file loaded
   * @param fileoutput - file outputted
   */
  void blur(String filename, String fileoutput);

  /**
   * loads image into the list of images.
   * @param filename - file loaded
   * @param fileoutput - file outputted
   */
  public void loadImage(String filename, String fileoutput);

  /**
   * saves image to the computer.
   * @param filename - file loaded
   * @param filedest - file outputted
   */
  public void saveImage(String filename, String filedest);

  /**
   * increases the brightness in image.
   * @param filename - file loaded
   * @param fileoutput - file outputted
   * @param brightness level of brightness desired
   */
  public void brightenImage(String filename, String fileoutput, int brightness);

  /**
   * flips image horizontally.
   * @param filename - file loaded
   * @param fileoutput - file outputted
   */
  public void flipHorizontal(String filename, String fileoutput);

  /**
   * flips image vertically.
   * @param filename - file loaded
   * @param fileoutput - file outputted
   */
  public void flipVertical(String filename, String fileoutput);

  /**
   * changes greyscale component of image.
   * @param c way you want the greyscale to change
   * @param filename - file loaded
   * @param fileoutput - file outputted
   */
  public void greyscaleImage(String c, String filename, String fileoutput);

  /**
   * filters the given image.
   * @param filename the file you want filtered
   * @param filter type of filter desired
   */
  void filter(String filename, double[][] filter);

}
