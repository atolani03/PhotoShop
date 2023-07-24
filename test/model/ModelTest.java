package model;

import org.junit.Before;
import org.junit.Test;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import controller.ImageController;
import model.ImageModelImpl;
import model.Images;
import model.ImagesImpl;
import view.ViewModel;
import view.ViewModelImpl;


import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link ImageModelImpl}.
 */
public class ModelTest {

  static ImageModelImpl model_1;
  static ImageModelImpl model_2;
  static ImageModelImpl model_3;
  static int[][][] array;
  static int[][][] array2;
  Map<String, int[][][]> pixels = new HashMap<>();
  Map<String, int[][][]> pixels_2 = new HashMap<>();

  // init conditions
  @Before
  public void init() {
    array = new int[][][]{{{103, 92, 67}, {110, 30, 88}, {12, 45, 67}},
      {{103, 92, 67}, {110, 30, 88}, {12, 0, 67}}};
    array2 = new int[][][]{{{1, 9, 6}, {110, 30, 88}},
      {{103, 92, 67}, {110, 30, 88}},
      {{103, 92, 67}, {110, 30, 88}},
      {{103, 92, 67}, {110, 30, 88}}};
    pixels.put("koala", array);
    pixels_2.put("koala2", array2);
    model_1 = new ImageModelImpl();
    model_2 = new ImageModelImpl(pixels);
    model_3 = new ImageModelImpl(pixels_2);
  }


  //testing the getPictures method to access the map in the model
  @Test
  public void getPictures() {
    init();
    assertEquals(pixels, model_2.getPictures());
    assertEquals(new HashMap<>(), model_1.getPictures());
    assertEquals(array, model_2.getPictures().get("koala"));
    assertEquals(103, model_2.getPictures().get("koala")[0][0][0]);
    assertEquals(30, model_2.getPictures().get("koala")[1][1][1]);
  }

  //testing a method that creates a copy
  @Test
  public void makeCopy() {
    init();
    int[][][] array_new = model_2.makeCopy("koala");
    assertEquals(array_new, model_2.makeCopy("koala"));
    assertEquals(model_2.getPictures().get("koala"), model_2.makeCopy("koala"));
    assertEquals(103, array_new[0][0][0]);
    assertEquals(30, array_new[1][1][1]);
  }

  //testing the get image width method that returns the width of the picture
  @Test
  public void getImageWidth() {
    init();
    assertEquals(array[0].length, model_2.getImageWidth("koala"));
    assertEquals(3, model_2.getImageWidth("koala"));
    assertEquals(array2[0].length, model_3.getImageWidth("koala2"));
    assertEquals(2, model_3.getImageWidth("koala2"));
  }

  //testing the get image height method that returns the height of the picture
  @Test
  public void getImageHeight() {
    init();
    assertEquals(array.length, model_2.getImageHeight("koala"));
    assertEquals(2, model_2.getImageHeight("koala"));
    assertEquals(array2.length, model_3.getImageHeight("koala2"));
    assertEquals(4, model_3.getImageHeight("koala2"));
  }

  //testing the addToMap correctly adds the String and int[][][] values to the map in the model
  @Test
  public void addToMap() {
    init();
    model_1.addToMap("koala", array);
    model_1.addToMap("koala_new", array2);
    assertEquals(model_2.getPictures().get("koala"), model_1.getPictures().get("koala"));
    assertEquals(array, model_1.getPictures().get("koala"));
    assertEquals(array2, model_1.getPictures().get("koala_new"));

  }

  //testing how the model/the array in the map of the model changed after flipping it vertically
  @Test
  public void flipVertical() {
    init(); //in the first example 0 and 45 switched rows
    array2 = new int[][][]{{{103, 92, 67}, {110, 30, 88}, {12, 0, 67}},
      {{103, 92, 67}, {110, 30, 88}, {12, 45, 67}}};
    assertEquals(array2, model_2.flipVertical("koala"));
    array2 = new int[][][]{{{103, 92, 67}, {110, 30, 88}},
      {{103, 92, 67}, {110, 30, 88}},
      {{103, 92, 67}, {110, 30, 88}},
      {{1, 9, 6}, {110, 30, 88}}};
    assertEquals(array2, model_3.flipVertical("koala2"));
  }

  //testing how the model/the array in the map of the model changed after flipping it horizontally
  @Test
  public void flipHorizontal() {
    init();
    array2 = new int[][][]{{{12, 45, 67}, {110, 30, 88}, {103, 92, 67}},
      {{12, 0, 67}, {110, 30, 88}, {103, 92, 67}}};
    assertEquals(array2, model_2.flipHorizontal("koala"));
    array2 = new int[][][]{{{110, 30, 88}, {1, 9, 6}},
      {{110, 30, 88}, {103, 92, 67}},
      {{110, 30, 88}, {103, 92, 67}},
      {{110, 30, 88}, {103, 92, 67}}};
    assertEquals(array2, model_3.flipHorizontal("koala2"));
  }

  //testing the changes made to the array after greyscaling it with green, red, and blue
  @Test
  public void greyscale() {
    init();
    // green-component test
    array2 = new int[][][]{{{92, 92, 92}, {30, 30, 30}, {45, 45, 45}},
      {{92, 92, 92}, {30, 30, 30}, {0, 0, 0}}};
    assertEquals(array2, model_2.greyscale("green-component", "koala"));
    array2 = new int[][][]{{{9, 9, 9}, {30, 30, 30}},
      {{92, 92, 92}, {30, 30, 30}},
      {{92, 92, 92}, {30, 30, 30}},
      {{92, 92, 92}, {30, 30, 30}}};
    assertEquals(array2, model_3.greyscale("green-component", "koala2"));
    init();
    // red-component test
    array2 = new int[][][]{{{103, 103, 103}, {110, 110, 110}, {12, 12, 12}},
      {{103, 103, 103}, {110, 110, 110}, {12, 12, 12}}};
    assertEquals(array2, model_2.greyscale("red-component", "koala"));
    array2 = new int[][][]{{{1, 1, 1}, {110, 110, 110}},
      {{103, 103, 103}, {110, 110, 110}},
      {{103, 103, 103}, {110, 110, 110}},
      {{103, 103, 103}, {110, 110, 110}}};
    assertEquals(array2, model_3.greyscale("red-component", "koala2"));
    init();
    // blue-component test
    array2 = new int[][][]{{{67, 67, 67}, {88, 88, 88}, {67, 67, 67}},
      {{67, 67, 67}, {88, 88, 88}, {67, 67, 67}}};
    assertEquals(array2, model_2.greyscale("blue-component", "koala"));
    array2 = new int[][][]{{{6, 6, 6}, {88, 88, 88}},
      {{67, 67, 67}, {88, 88, 88}},
      {{67, 67, 67}, {88, 88, 88}},
      {{67, 67, 67}, {88, 88, 88}}};
    assertEquals(array2, model_3.greyscale("blue-component", "koala2"));
    init();
    // value-component test
    array2 = new int[][][]{{{103, 103, 103}, {110, 110, 110}, {67, 67, 67}},
      {{103, 103, 103}, {110, 110, 110}, {67, 67, 67}}};
    assertEquals(array2, model_2.greyscale("value-component", "koala"));
    init();
    // intensity-component test
    array2 = new int[][][]{{{87, 87, 87}, {76, 76, 76}, {41, 41, 41}},
      {{87, 87, 87}, {76, 76, 76}, {26, 26, 26}}};
    assertEquals(array2, model_2.greyscale("intensity-component", "koala"));
    init();
    // luma-component test
    array2 = new int[][][]{{{92, 92, 92}, {51, 51, 51}, {39, 39, 39}},
      {{92, 92, 92}, {51, 51, 51}, {7, 7, 7}}};
    assertEquals(array2, model_2.greyscale("luma-component", "koala"));
  }


  //  testing the bright method by checking
  //   colors of the first pixel for koala
  @Test
  public void bright() {
    init();
    Readable in = new StringReader("10 101 101 10");
    ViewModel view_1 = new ViewModelImpl(new StringBuilder());
    ImageController controller = new ImageController(model_2, view_1, in);
    controller.loadImage("koala.ppm", "koala");
    assertEquals(201, model_2.bright(100, "koala")[0][0][0]);
    assertEquals(190, model_2.bright(100, "koala")[0][0][1]);
    assertEquals(158, model_2.bright(100, "koala")[0][0][2]);
  }

  //  testing the sepia method by checking
  //   colors of the first pixel for koala
  @Test
  public void sepia() {
    init();
    Images image = new ImagesImpl();
    Readable in = new StringReader("10 101 101 10");
    ViewModel view_1 = new ViewModelImpl(new StringBuilder());
    ImageController controller = new ImageController(model_2, view_1, in);
    controller.loadImage("koala.ppm", "koala");
    assertEquals(109, model_2.greyscale("sepia", "koala")[0][0][0]);
    assertEquals(97, model_2.greyscale("sepia", "koala")[0][0][1]);
    assertEquals(76, model_2.greyscale("sepia", "koala")[0][0][2]);
  }

  //  testing the blur method by checking
  //   colors of the first pixel for koala
  @Test
  public void blur() {
    init();
    Images image = new ImagesImpl();
    Readable in = new StringReader("10 101 101 10");
    ViewModel view_1 = new ViewModelImpl(new StringBuilder());
    ImageController controller = new ImageController(model_2, view_1, in);
    controller.loadImage("koala.ppm", "koala");
    assertEquals(58, model_2.blur( "koala")[0][0][0]);
    assertEquals(52, model_2.blur( "koala")[0][0][1]);
    assertEquals(34, model_2.blur( "koala")[0][0][2]);
  }

  //  testing the sharpen method by checking
  //   colors of the first pixel for koala
  @Test
  public void sharpen() {
    init();
    Images image = new ImagesImpl();
    Readable in = new StringReader("10 101 101 10");
    ViewModel view_1 = new ViewModelImpl(new StringBuilder());
    ImageController controller = new ImageController(model_2, view_1, in);
    controller.loadImage("koala.ppm", "koala");
    assertEquals(114, model_2.sharpen( "koala")[0][0][0]);
    assertEquals(100, model_2.sharpen( "koala")[0][0][1]);
    assertEquals(64, model_2.sharpen( "koala")[0][0][2]);
  }

  // tests to make sure the getMax works
  @Test
  public void testGetMax() {
    Readable in = new StringReader("10 101 101 10");
    ViewModel view_1 = new ViewModelImpl(new StringBuilder());
    ImageController controller = new ImageController(model_2, view_1, in);
    controller.loadImage("koala.ppm", "koala");
    assertEquals(255, model_2.getMax());
  }

  // tests to make sure error is thrown when color input is invalid
  @Test(expected = IllegalArgumentException.class)
  public void InvalidColorInput() {
    init();
    assertEquals(array2, model_2.greyscale("jump", "koala"));
  }

}