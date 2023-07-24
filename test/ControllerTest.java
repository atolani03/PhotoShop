import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import controller.ImageController;
import model.ImageModelImpl;
import view.ViewModelImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * test class for the controller.
 */
public class ControllerTest {
  static ImageModelImpl model_1;
  static ImageModelImpl model_2;
  static ImageModelImpl model_3;
  static ViewModelImpl view_1;
  static ImageController controller_1;
  static ImageController controller_2;
  static Readable readable;
  static int[][][] array;
  static int[][][] array2;
  Map<String, int[][][]> pixels = new HashMap<>();
  Map<String, int[][][]> pixels_2 = new HashMap<>();

  @Before
  public void init() {
    array = new int[][][]{
            {
        {103, 92, 67}, {110, 30, 88}, {12, 45, 67}},
            {
        {103, 92, 67}, {110, 30, 88}, {12, 0, 67}}};
    array2 = new int[][][]{
            {
        {1, 9, 6}, {110, 30, 88}},
            {
        {103, 92, 67}, {110, 30, 88}},
            {
        {103, 92, 67}, {110, 30, 88}},
            {
        {103, 92, 67}, {110, 30, 88}}};
    pixels.put("koala", array);
    pixels_2.put("koala2", array2);
    model_1 = new ImageModelImpl();
    model_2 = new ImageModelImpl(pixels);
    model_3 = new ImageModelImpl(pixels_2);
    view_1 = new ViewModelImpl();
    readable = new StringReader("");
    controller_1 = new ImageController(model_1, view_1, readable);
    controller_2 = new ImageController(model_2, view_1, readable);
  }


  // tests load method
  @Test
  public void testLoad() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.blur("res/batman.ppm", "batman");
    c.loadImage("batman.png", "snap");
    assertEquals(256, m.getImageHeight("snap"));
  }

  // test horizontal flip method
  @Test
  public void testHorizontalFlip() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.flipHorizontal("batman", "b-horizontal-flip");
    c.loadImage("res/batmanhorizontalflip.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-horizontal-flip"), m.getPictures().get("bat"));
  }

  // test vertical flip
  @Test
  public void testVerticalFlip() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.blur("snap", "snap2");
    assertEquals(22, m.getPictures().get("snap2")[0][0][1]);
    c.loadImage("res/batman.ppm", "batman");
    c.flipVertical("batman", "b-vertical-flip");
    c.loadImage("res/batmanverticalflip.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-vertical-flip"), m.getPictures().get("bat"));
  }

  // test vertical and horizontal flip
  @Test
  public void testVerticalHorizontal() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.flipVertical("batman", "b-vertical-flip");
    c.flipHorizontal("b-vertical-flip", "b-horizontal-vertical");
    c.loadImage("res/batmanverticalhorizontal.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-horizontal-vertical"), m.getPictures().get("bat"));
  }

  // test green component
  @Test
  public void testGreenComponent() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("green-component", "batman", "b-green");
    c.loadImage("res/batmangreencomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-green"), m.getPictures().get("bat"));
  }

  // test red component
  @Test
  public void testRedComponent() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("red-component", "batman", "b-red");
    c.loadImage("res/batmanredcomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-red"), m.getPictures().get("bat"));
  }

  // test blue component
  @Test
  public void testBlueComponent() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("blue-component", "batman", "b-blue");
    c.loadImage("res/batmanbluecomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-blue"), m.getPictures().get("bat"));
  }

  // test luma component
  @Test
  public void testLumaComponent() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("luma-component", "batman", "b-luma");
    c.loadImage("res/batmanlumacomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-luma"), m.getPictures().get("bat"));
  }

  // test greyscale component
  @Test
  public void testGreyScale() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("greyscale", "batman", "b-luma");
    c.loadImage("res/batmanlumacomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-luma"), m.getPictures().get("bat"));
  }

  // test intensity component
  @Test
  public void testIntensityComponent() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("intensity-component", "batman", "b-intensity");
    c.loadImage("res/batmanintensitycomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-intensity"), m.getPictures().get("bat"));
  }

  // test value component
  @Test
  public void testValueComponent() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("value-component", "batman", "b-value");
    c.loadImage("res/batmanvaluecomponent.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-value"), m.getPictures().get("bat"));
  }

  // test brighten
  @Test
  public void testBrighten() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.brightenImage("batman", "b-bright", 50);
    c.loadImage("res/batmanbrightenby50.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-bright"), m.getPictures().get("bat"));
  }

  // test darken
  @Test
  public void testDarken() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.brightenImage("batman", "b-dark", -50);
    c.loadImage("res/batmandarkby50.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-dark"), m.getPictures().get("bat"));
  }


  // tests flip and change color together
  @Test
  public void testFlipChangeColor() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.flipHorizontal("batman", "bflip");
    c.greyscaleImage("green-component", "bflip", "b-change");
    c.loadImage("res/batmanflipgreen.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-change"), m.getPictures().get("bat"));
  }

  // test change color and then change brightness
  @Test
  public void testChangeColorChangeBrightness() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("blue-component", "batman", "b-color");
    c.brightenImage("b-color", "b-final", 50);
    c.loadImage("res/batmancolorbright.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-final"), m.getPictures().get("bat"));
  }

  // test change brightness and then change color
  @Test
  public void testChangeBrightnessChangeColor() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.brightenImage("batman", "b-bright", 50);
    c.greyscaleImage("blue-component", "b-bright", "b-color");
    c.loadImage("res/batmancolorbright.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-color"), m.getPictures().get("bat"));
  }

  // test change brightness and then change color and then flip
  @Test
  public void testChangeBrightnessChangeColorFlip() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.brightenImage("batman", "b-bright", 50);
    c.greyscaleImage("blue-component", "b-bright", "b-color");
    c.flipVertical("b-color", "b-final");
    c.loadImage("res/batmanbrightcolorflip.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-final"), m.getPictures().get("bat"));
  }

  // test flip and then change brightness and then change color
  @Test
  public void testFlipChangeBrightnessChangeColor() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.flipVertical("batman", "b-flip");
    c.brightenImage("b-flip", "b-bright", 50);
    c.greyscaleImage("blue-component", "b-bright", "b-final");
    c.loadImage("res/batmanbrightcolorflip.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-final"), m.getPictures().get("bat"));
  }

  // test change color and then flip and then change brightness
  @Test
  public void testChangeColorFlipChangeBrightness() {
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl();
    Reader r = new StringReader("");
    ImageController c = new ImageController(m, v, r);
    c.loadImage("res/batman.ppm", "batman");
    c.greyscaleImage("blue-component", "batman", "b-color");
    c.flipVertical("b-color", "b-flip");
    c.brightenImage("b-flip", "b-final", 50);
    c.loadImage("res/batmanbrightcolorflip.ppm", "bat");
    assertArrayEquals(m.getPictures().get("b-final"), m.getPictures().get("bat"));
  }


  //testing the welcome message and load and quit in the controller
  //testing method run and its output
  @Test
  public void testrun() {
    init();
    Readable in = new StringReader("load koala.ppm koala quit");
    Appendable out = new StringBuilder();
    ViewModelImpl view_1 = new ViewModelImpl(out);
    ImageController controller = new ImageController(model_2, view_1, in);
    controller.run();
    assertEquals("Welcome to Image Processing Program\n" +
            "Start by loading an image in\n" +
            "- Image loaded\n" +
            "System Quit\n", out.toString());
  }

  @Test
  public void testQuit() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl(b);
    Reader r = new StringReader("quit");
    ImageController c = new ImageController(m, v, r);
    c.run();
    assertEquals("Welcome to Image Processing Program\n" +
            "Start by loading an image in\n" +
            "System Quit\n", b.toString());
  }


  // tests to make sure error is thrown when color input is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorInput() {
    init();
    controller_2.greyscaleImage("jump", "koala", "koala1");
  }

  @Test
  // tests to make sure error is thrown when color input is invalid
  public void testInvalidCommand() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl(b);
    Reader r = new StringReader("jump");
    ImageController c = new ImageController(m, v, r);
    c.run();
    assertEquals("Welcome to Image Processing Program\n" +
            "Start by loading an image in\n" +
            "Invalid Command! Try again\n", b.toString());
  }

  @Test
  public void testInvalidBrightness() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl(b);
    Reader r = new StringReader("brighten jump koala k");
    ImageController c = new ImageController(m, v, r);
    c.run();
    assertEquals("Welcome to Image Processing Program\n" +
            "Start by loading an image in\n" +
            "Invalid input!\n" +
            "Invalid Command! Try again\n" +
            "Invalid Command! Try again\n", b.toString());
  }

  @Test
  public void testInvalidLoadInput() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl(b);
    Reader r = new StringReader("load jump.ppm k");
    ImageController c = new ImageController(m, v, r);
    c.run();
    assertEquals("Welcome to Image Processing Program\n" +
            "Start by loading an image in\n" +
            "File jump.ppm not found!", b.toString());
  }

  // checks to make sure script distinguishes between all of the commands given
  @Test
  public void testScriptRead() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl(b);
    Reader r = new StringReader("text res/commands.txt");
    ImageController c = new ImageController(m,v,r);
    c.run();
    assertEquals("Welcome to Image Processing Program\n" +
            "Start by loading an image in\n" +
            "- Image loaded\n" +
            "- Image color changed\n" +
            "- Image blurred\n" +
            "- Image sharpened\n" +
            "- Image color changed\n" +
            "System Quit\n", b.toString());
  }

  @Test
  public void testExportPNG() {
    StringBuilder b = new StringBuilder();
    ImageModelImpl m = new ImageModelImpl();
    ViewModelImpl v = new ViewModelImpl(b);
    Reader r = new StringReader("load batman.ppm b save batman.png b");
    ImageController c = new ImageController(m,v,r);
    c.run();
    String outFormat = "%-17s: %s%n";
    File outputFile = Paths.get("batman.png").toAbsolutePath().toFile();
    assertEquals("/Users/akshaytolani/Desktop/OOD/Assignment5/batman.png",
            outputFile.getAbsolutePath());
    outputFile = Paths.get("batman.ppm").toAbsolutePath().toFile();
    assertEquals("/Users/akshaytolani/Desktop/OOD/Assignment5/batman.ppm",
            outputFile.getAbsolutePath());
    outputFile = Paths.get("batman.jpg").toAbsolutePath().toFile();
    assertEquals("/Users/akshaytolani/Desktop/OOD/Assignment5/batman.jpg",
            outputFile.getAbsolutePath());
    outputFile = Paths.get("batman.jpeg").toAbsolutePath().toFile();
    assertEquals("/Users/akshaytolani/Desktop/OOD/Assignment5/batman.jpeg",
            outputFile.getAbsolutePath());
    outputFile = Paths.get("batman.bmp").toAbsolutePath().toFile();
    assertEquals("/Users/akshaytolani/Desktop/OOD/Assignment5/batman.bmp",
            outputFile.getAbsolutePath());

  }

}