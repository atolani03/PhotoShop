package view;

import java.io.IOException;

import view.ViewModel;

/**
 * view class to make sure the message is able to exported.
 */
public class ViewModelImpl implements ViewModel {

  private Appendable appendable;

  /**
   * Constructor that takes in just the file.
   */
  public ViewModelImpl() {
    appendable = System.out;
  }

  /**
   * Constructor that takes in the file and the appendable.
   * @param appendable the appendable you want added
   */
  public ViewModelImpl(Appendable appendable) {
    if (appendable == null) {
      throw new IllegalArgumentException("Model or/and appendable can't be null!");
    }
    this.appendable = appendable;
  }

  public void renderMessage(String message) throws IOException {
    appendable.append(message);
  }

}
