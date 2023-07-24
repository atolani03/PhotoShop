package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import model.ImageModelImpl;

/**
 * class for the histogram that shows the amount of each color.
 */
public class Histogram {
  JPanel panel;
  JPanel panelMain;
  ImageModelImpl model;
  String filename;
  JScrollPane pane;

  /**
   * Histogram constructor that allows us to print the histogram.
   * @param panelMain the area the histogram goes
   * @param model the model it is using
   * @param filename the file within the model that is used
   */
  public Histogram(JPanel panelMain, ImageModelImpl model, String filename) {

    this.panelMain = panelMain;
    this.model = model;
    this.filename = filename;
    this.pane = new JScrollPane();

    panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
    panelMain.add(new JScrollPane());
    panelMain.add(new Graph(), Component.BOTTOM_ALIGNMENT);
  }

  protected class Graph extends JPanel {
    public Graph() {
      Dimension minSize = new Dimension(800, 0);
      Dimension prefSize = new Dimension(1020, 256);
      setMinimumSize(minSize);
      setPreferredSize(prefSize);
    }


    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (g != null) {
        int xOffset = 0;
        int yOffset = 0;
        int width = getWidth() - 1 - (xOffset * 2);
        int height = getHeight() - 1 - (yOffset * 2);
        Graphics2D grd = (Graphics2D) g.create();
        Graphics2D ggd = (Graphics2D) g.create();
        Graphics2D gbd = (Graphics2D) g.create();
        Graphics2D gad = (Graphics2D) g.create();
        grd.setColor(Color.RED);
        ggd.setColor(Color.GREEN);
        gbd.setColor(Color.BLUE);
        gad.setColor(Color.CYAN);
        grd.drawRect(xOffset, yOffset, width, height);
        ggd.drawRect(xOffset, yOffset, width, height);
        gbd.drawRect(xOffset, yOffset, width, height);
        gad.drawRect(xOffset, yOffset, width, height);
        red(grd);
        green(ggd);
        blue(gbd);
        averageColor(gad);
        grd.dispose();
        ggd.dispose();
        gbd.dispose();
        gad.dispose();
      }
    }

    private void red(Graphics2D g2d) {
      int xPos = 0;
      int yPos = 0;
      int width = model.getPictures().get(filename)[0].length;
      int height = model.getPictures().get(filename).length;
      int[][][] array = model.getPictures().get(filename);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = array[i][j][0];
          Rectangle2D bar = new Rectangle2D.Float(
                  xPos, yPos, 5, r * 2);
          g2d.setColor(Color.RED);
          g2d.draw(bar);
          xPos += 5;
        }
      }

    }

    private void green(Graphics2D g2d) {
      int xPos = 0;
      int yPos = 0;
      int width = model.getPictures().get(filename)[0].length;
      int height = model.getPictures().get(filename).length;
      int[][][] array = model.getPictures().get(filename);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int g = array[i][j][1];
          Rectangle2D bar = new Rectangle2D.Float(
                  xPos, yPos, 5, g * 2);
          g2d.setColor(Color.GREEN);
          g2d.draw(bar);
          xPos += 5;
        }
      }

    }

    private void blue(Graphics2D g2d) {
      int xPos = 0;
      int yPos = 0;
      int width = model.getPictures().get(filename)[0].length;
      int height = model.getPictures().get(filename).length;
      int[][][] array = model.getPictures().get(filename);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int b = array[i][j][2];
          Rectangle2D bar = new Rectangle2D.Float(
                  xPos, yPos, 5, b * 2);
          g2d.setColor(Color.BLUE);
          g2d.draw(bar);
          xPos += 5;
        }
      }
    }

    private void averageColor(Graphics2D g2d) {
      int xPos = 0;
      int yPos = 0;
      int width = model.getPictures().get(filename)[0].length;
      int height = model.getPictures().get(filename).length;
      int[][][] array = model.getPictures().get(filename);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = array[i][j][0];
          int g = array[i][j][1];
          int b = array[i][j][2];
          int average = (r + g + b) / 3;
          Rectangle2D bar = new Rectangle2D.Float(
                  xPos, yPos, 5, average * 2);
          g2d.setColor(Color.CYAN);
          g2d.draw(bar);
          xPos += 5;
        }
      }
    }
  }

}
