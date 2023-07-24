package view;

import java.io.IOException;

/**
 * interface for all the methods used in the view class.
 */
public interface ViewModel {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

}
