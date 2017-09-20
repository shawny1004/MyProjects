
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import recognizer.Circle;
import recognizer.DeathlyHallow;
import recognizer.EqualTriangle;
import recognizer.Line;
import recognizer.Rectangle;
import recognizer.Snowman;
import recognizer.Snowman_Hat_Hands;
import recognizer.SymbolRecognizer;
import recognizer.Triangle;

public class BlankArea extends JPanel {

  Dimension minSize = new Dimension(1600, 900);
  SymbolRecognizer symbolRecognizer;
  private int numDrew;

  /**
   * Count the drew shapes on the canvas.
   *
   * @return the number of shapes drew.
   */
  public int getNumDrew() {
    return numDrew;
  }

  /**
   * constructor for BlankArea.
   *
   * @param color bgcolor.
   * @param symbolRecognizer symbolRecognizer.
   */
  public BlankArea(Color color, SymbolRecognizer symbolRecognizer) {
    setBackground(color);
    setOpaque(true);
    setBorder(BorderFactory.createLineBorder(Color.black));
    this.symbolRecognizer = symbolRecognizer;
    numDrew = 0;
  }

  /**
   * get minimun size of panel.
   *
   * @return size.
   */
  public Dimension getMinimumSize() {
    return minSize;
  }

  /**
   * get preferred size of panel.
   *
   * @return size.
   */
  public Dimension getPreferredSize() {
    return minSize;
  }

  /**
   * this method paints everything on panel.
   *
   * @param g graphics.
   */
  public void paintComponent(Graphics g) {

    g = (Graphics2D) g;
    g.clearRect(0, 0, this.getWidth(), this.getHeight());
    g.setColor(Color.gray);
    ((Graphics2D) g).setStroke(new BasicStroke(0.5f));

    for (int i = 0; i < this.getHeight(); i += 20) {
      g.drawLine(0, i, this.getWidth(), i);
    }
    for (int i = 0; i < this.getWidth(); i += 20) {
      g.drawLine(i, 0, i, this.getHeight());
    }
    ((Graphics2D) g).setStroke(new BasicStroke(3.0f));

    g.setColor(Color.black);
    g.setFont(new Font("SansSerif", Font.PLAIN, 30));
    g.setColor(Color.black);
    ((Graphics2D) g).drawString("Total shapes drew: " + numDrew + "", 1200, 800);
    ((Graphics2D) g)
        .drawString("Total shapes now:" + symbolRecognizer.getShapes().size() + "", 1200, 850);
    for (Line trail : symbolRecognizer.getTrail()) {
      g.drawLine((int) trail.getP1().getX(), (int) trail.getP1().getY(), (int) trail.getP2().getX(),
          (int) trail.getP2().getY());
    }
    if (symbolRecognizer != null && !symbolRecognizer.getShapes().isEmpty()) {
      for (recognizer.Shape shape : symbolRecognizer.getShapes()) {
        //draw line.
        if (shape instanceof Line) {
          numDrew++;
          g.drawLine((int) ((Line) shape).getP1().getX(), (int) ((Line) shape).getP1().getY(),
              (int) ((Line) shape).getP2().getX(), (int) ((Line) shape).getP2().getY());
          g.drawString("Line",
              (int) (0.5 * (((Line) shape).getP1().getX() + ((Line) shape).getP2().getX())),
              (int) (0.5 * (((Line) shape).getP1().getY() + ((Line) shape).getP2().getY())));
        } else if (shape instanceof Circle) {
          numDrew++;
          //draw Circle.
          int r = (int) ((Circle) shape).getR();
          g.drawOval((int) ((Circle) shape).getC().getX() - r,
              (int) ((Circle) shape).getC().getY() - r, 2 * r, 2 * r);
          g.drawString("Circle",
              (int) ((Circle) shape).getC().getX(),
              (int) ((Circle) shape).getC().getY());
        } else if (shape instanceof Triangle) {
          numDrew++;
          //draw triangle.
          g.drawLine((int) ((Triangle) shape).getL1().getP1().getX(),
              (int) ((Triangle) shape).getL1().getP1().getY(),
              (int) ((Triangle) shape).getL1().getP2().getX(),
              (int) ((Triangle) shape).getL1().getP2().getY());
          g.drawLine((int) ((Triangle) shape).getL2().getP1().getX(),
              (int) ((Triangle) shape).getL2().getP1().getY(),
              (int) ((Triangle) shape).getL2().getP2().getX(),
              (int) ((Triangle) shape).getL2().getP2().getY());
          g.drawLine((int) ((Triangle) shape).getL3().getP1().getX(),
              (int) ((Triangle) shape).getL3().getP1().getY(),
              (int) ((Triangle) shape).getL3().getP2().getX(),
              (int) ((Triangle) shape).getL3().getP2().getY());
          g.drawString("Triangle",
              (int) (0.5 * (((Triangle) shape).getL2().getP1().getX() + ((Triangle) shape).getL2()
                  .getP2().getX())),
              (int) (0.5 * (((Triangle) shape).getL2().getP1().getY() + ((Triangle) shape).getL2()
                  .getP2().getY())));
        } else if (shape instanceof EqualTriangle) {
          numDrew++;
          //draw EqualTriangle.
          g.drawLine((int) ((EqualTriangle) shape).getLine1().getP1().getX(),
              (int) ((EqualTriangle) shape).getLine1().getP1().getY(),
              (int) ((EqualTriangle) shape).getLine1().getP2().getX(),
              (int) ((EqualTriangle) shape).getLine1().getP2().getY());
          g.drawLine((int) ((EqualTriangle) shape).getLine2().getP1().getX(),
              (int) ((EqualTriangle) shape).getLine2().getP1().getY(),
              (int) ((EqualTriangle) shape).getLine2().getP2().getX(),
              (int) ((EqualTriangle) shape).getLine2().getP2().getY());
          g.drawLine((int) ((EqualTriangle) shape).getLine3().getP1().getX(),
              (int) ((EqualTriangle) shape).getLine3().getP1().getY(),
              (int) ((EqualTriangle) shape).getLine3().getP2().getX(),
              (int) ((EqualTriangle) shape).getLine3().getP2().getY());
          g.drawString("Equilateral triangle",
              (int) (0.5 * (((EqualTriangle) shape).getLine2().getP1().getX()
                  + ((EqualTriangle) shape).getLine2().getP2().getX())),
              (int) (0.5 * (((EqualTriangle) shape).getLine2().getP1().getY()
                  + ((EqualTriangle) shape).getLine2().getP2().getY())));
        } else if (shape instanceof Snowman) {
          numDrew++;
          //draw Snowman.
          int r1 = (int) ((Snowman) shape).getC1().getR();
          int r2 = (int) ((Snowman) shape).getC2().getR();
          int r3 = (int) ((Snowman) shape).getC3().getR();
          g.drawOval((int) ((Snowman) shape).getC1().getC().getX() - r1,
              (int) ((Snowman) shape).getC1().getC().getY() - r1,
              r1 * 2,
              r1 * 2);
          g.drawOval((int) ((Snowman) shape).getC2().getC().getX() - r2,
              (int) ((Snowman) shape).getC2().getC().getY() - r2,
              r2 * 2,
              r2 * 2);
          g.drawOval((int) ((Snowman) shape).getC3().getC().getX() - r3,
              (int) ((Snowman) shape).getC3().getC().getY() - r3,
              r3 * 2,
              r3 * 2);
          g.drawString("Snowman",
              (int) ((Snowman) shape).getC2().getC().getX(),
              (int) ((Snowman) shape).getC2().getC().getY());
        } else if (shape instanceof Rectangle) {
          numDrew++;
          //draw Rectangle.
          g.drawLine((int) ((Rectangle) shape).getL1().getP1().getX(),
              (int) ((Rectangle) shape).getL1().getP1().getY(),
              (int) ((Rectangle) shape).getL1().getP2().getX(),
              (int) ((Rectangle) shape).getL1().getP2().getY());
          g.drawLine((int) ((Rectangle) shape).getL2().getP1().getX(),
              (int) ((Rectangle) shape).getL2().getP1().getY(),
              (int) ((Rectangle) shape).getL2().getP2().getX(),
              (int) ((Rectangle) shape).getL2().getP2().getY());
          g.drawLine((int) ((Rectangle) shape).getL3().getP1().getX(),
              (int) ((Rectangle) shape).getL3().getP1().getY(),
              (int) ((Rectangle) shape).getL3().getP2().getX(),
              (int) ((Rectangle) shape).getL3().getP2().getY());
          g.drawLine((int) ((Rectangle) shape).getL4().getP1().getX(),
              (int) ((Rectangle) shape).getL4().getP1().getY(),
              (int) ((Rectangle) shape).getL4().getP2().getX(),
              (int) ((Rectangle) shape).getL4().getP2().getY());
          g.drawString("Rectangle",
              (int) (0.5 * (((Rectangle) shape).getL2().getP1().getX() + ((Rectangle) shape).getL2()
                  .getP2().getX())),
              (int) (0.5 * (((Rectangle) shape).getL2().getP1().getY() + ((Rectangle) shape).getL2()
                  .getP2().getY())));
        } else if (shape instanceof DeathlyHallow) {
          numDrew++;
          int r = (int) ((DeathlyHallow) shape).getCircle().getR();
          g.drawLine((int) ((DeathlyHallow) shape).getLine().getP1().getX(),
              (int) ((DeathlyHallow) shape).getLine().getP1().getY(),
              (int) ((DeathlyHallow) shape).getLine().getP2().getX(),
              (int) ((DeathlyHallow) shape).getLine().getP2().getY());

          g.drawOval((int) ((DeathlyHallow) shape).getCircle().getC().getX() - r,
              (int) ((DeathlyHallow) shape).getCircle().getC().getY() - r,
              2 * r, 2 * r);

          if (((DeathlyHallow) shape).isWithTriangle()) {
            g.drawLine((int) ((DeathlyHallow) shape).getTriangle().getL1().getP1().getX(),
                (int) ((DeathlyHallow) shape).getTriangle().getL1().getP1().getY(),
                (int) ((DeathlyHallow) shape).getTriangle().getL1().getP2().getX(),
                (int) ((DeathlyHallow) shape).getTriangle().getL1().getP2().getY());
            g.drawLine((int) ((DeathlyHallow) shape).getTriangle().getL2().getP1().getX(),
                (int) ((DeathlyHallow) shape).getTriangle().getL2().getP1().getY(),
                (int) ((DeathlyHallow) shape).getTriangle().getL2().getP2().getX(),
                (int) ((DeathlyHallow) shape).getTriangle().getL2().getP2().getY());
            g.drawLine((int) ((DeathlyHallow) shape).getTriangle().getL3().getP1().getX(),
                (int) ((DeathlyHallow) shape).getTriangle().getL3().getP1().getY(),
                (int) ((DeathlyHallow) shape).getTriangle().getL3().getP2().getX(),
                (int) ((DeathlyHallow) shape).getTriangle().getL3().getP2().getY());
            g.drawString("Deathly Hallows",
                (int) (0.5 * (((DeathlyHallow) shape).getTriangle().getL1().getP1().getX()
                    + (((DeathlyHallow) shape).getTriangle().getL1().getP2().getX()))),
                (int) (0.5 * (((DeathlyHallow) shape).getTriangle().getL1().getP1().getY()
                    + (((DeathlyHallow) shape).getTriangle().getL1().getP2().getY()))));
          } else {
            g.drawLine((int) ((DeathlyHallow) shape).getEqualTriangle().getLine1().getP1().getX(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine1().getP1().getY(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine1().getP2().getX(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine1().getP2().getY());
            g.drawLine((int) ((DeathlyHallow) shape).getEqualTriangle().getLine2().getP1().getX(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine2().getP1().getY(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine2().getP2().getX(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine2().getP2().getY());
            g.drawLine((int) ((DeathlyHallow) shape).getEqualTriangle().getLine3().getP1().getX(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine3().getP1().getY(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine3().getP2().getX(),
                (int) ((DeathlyHallow) shape).getEqualTriangle().getLine3().getP2().getY());
            g.drawString("Deathly Hallows",
                (int) (0.5 * (((DeathlyHallow) shape).getEqualTriangle().getLine1().getP1().getX()
                    + (((DeathlyHallow) shape).getEqualTriangle().getLine1().getP2().getX()))),
                (int) (0.5 * (((DeathlyHallow) shape).getEqualTriangle().getLine1().getP1().getY()
                    + (((DeathlyHallow) shape).getEqualTriangle().getLine1().getP2().getY()))));
          }
        } else if (shape instanceof Snowman_Hat_Hands) {
          numDrew++;
          Snowman_Hat_Hands snowman_hat_hands = (Snowman_Hat_Hands) shape;
          //draw Snowman.
          Circle c1 = ((Snowman_Hat_Hands) shape).getSnowman().getC1();
          Circle c2 = ((Snowman_Hat_Hands) shape).getSnowman().getC2();
          Circle c3 = ((Snowman_Hat_Hands) shape).getSnowman().getC3();
          List<Circle> circleList = new ArrayList<>();
          circleList.add(c1);
          circleList.add(c2);
          circleList.add(c3);
          for (Circle circle : circleList) {
            g.drawOval((int) (circle.getC().getX() - circle.getR()),
                (int) (circle.getC().getY() - circle.getR()),
                (int) (circle.getR() * 2),
                (int) (circle.getR() * 2));
          }
          //draw hat.
          for (Line line : snowman_hat_hands.getHat().getLines()){
            g.drawLine((int)line.getP1().getX(),(int)line.getP1().getY(),
                (int)line.getP2().getX(),(int)line.getP2().getY());
          }
          //draw hands.
          g.drawLine((int)snowman_hat_hands.getLeftHand().getP1().getX(),
              (int)snowman_hat_hands.getLeftHand().getP1().getY(),
              (int)snowman_hat_hands.getLeftHand().getP2().getX(),
              (int)snowman_hat_hands.getLeftHand().getP2().getY());
          g.drawLine((int)snowman_hat_hands.getRightHand().getP1().getX(),
              (int)snowman_hat_hands.getRightHand().getP1().getY(),
              (int)snowman_hat_hands.getRightHand().getP2().getX(),
              (int)snowman_hat_hands.getRightHand().getP2().getY());
          //draw string.
          g.drawString("Snowman with hats and hands",
              (int) (0.5 * (snowman_hat_hands.getRightHand().getP1().getX()
                  + (snowman_hat_hands.getRightHand().getP2().getX()))),
              (int) (0.5 * (snowman_hat_hands.getRightHand().getP1().getY()
                  + (snowman_hat_hands.getRightHand().getP2().getY()))));
        } else {
          throw new IllegalArgumentException("Wrong shape");
        }

      }
    }
  }
}
