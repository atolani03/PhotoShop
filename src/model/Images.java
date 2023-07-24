package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Images interface for all of the methods.
 */
public interface Images {
  public int[][][] readImage(String filename) throws IOException;

  /**
   * Write an image to a file in a given format.
   *
   * @param pixels   a 3D array representing the colors of the picture.
   * @param filename the name of the file where the picture has to be stored.
   * @throws IOException if the provided filename does not exist and cannot hold the image
   */
  public void saveImage(int[][][] pixels, String filename) throws IOException;

  public int[][][] readBufferedImage(BufferedImage image) throws IOException;


  /**
   * filters through the image.
   * @param filename the file you want filtered
   * @param filter the type of filter desired
   * @return the new pixels that are filtered
   */
  public int[][][] filter(String filename, double[][] filter);

}
