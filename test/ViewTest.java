import org.junit.Test;
import java.io.IOException;

import model.ImageModelImpl;
import view.ViewModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * tests the view class to make sure everything runs properly.
 */
public class ViewTest {

  // tests to make sure error is thrown when appendable is null
  @Test(expected = IllegalArgumentException.class)
  public void AppendableNull() {
    ImageModelImpl model1 = new ImageModelImpl();
    ViewModelImpl view = new ViewModelImpl(null);
  }


  @Test
  public void testRenderMessage() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl model = new ImageModelImpl();
    ViewModelImpl view = new ViewModelImpl(b);
    try {
      view.renderMessage("Hello");
      assertEquals("Hello", b.toString());
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }
}