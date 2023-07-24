package view;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This example shows the different user interface elements in Java Swing.
 * Please use these examples as guidelines only to see how to use them. This
 * example has not been designed very well, it is only meant to illustrate code
 * snippets.
 * Feel free to try out different modifications to see how the program changes
 */

public class SwingFeatures {

  /**
   * catches errors for the swing features.
   * @param args passed into main method to see if there are errors
   */
  public static void main(String[] args) {
    SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
    SwingFeaturesFrame frame = new SwingFeaturesFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
      // handle exception
    }

  }

}
