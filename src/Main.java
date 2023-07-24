import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import controller.ImageController;
import model.ImageModelImpl;
import view.JFrameView;
import view.ViewModel;
import view.ViewModelImpl;

/**
 * main class that runs the main method.
 */
public class Main {
  /**
   * main method to run the program.
   *
   * @param args that you want to have in main program
   */
  public static void main(String[] args) {
    if (args.length == 1 && args[0].equals("-text")) {
      ImageModelImpl model = new ImageModelImpl();
      Readable rd = new InputStreamReader(System.in);
      Appendable ap = System.out;
      ViewModel view = new ViewModelImpl(ap);
      ImageController controller = new ImageController(model, view, rd);
      controller.run();
    } else if (args.length == 2 && args[0].equals("-file")) {
      try {
        Readable rd = new FileReader(args[1]);
        ImageModelImpl model = new ImageModelImpl();
        Appendable ap = System.out;
        ViewModel view = new ViewModelImpl(ap);
        ImageController controller = new ImageController(model, view, rd);
        controller.run();
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    } else {
      ImageModelImpl model = new ImageModelImpl();
      Readable rd = new InputStreamReader(System.in);
      Appendable ap = System.out;
      ViewModel view = new ViewModelImpl(ap);
      ImageController controller = new ImageController(model, view, rd);
      JFrameView.setDefaultLookAndFeelDecorated(false);
      JFrameView frame = new JFrameView(controller, model);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      controller.run();

    }
  }
}