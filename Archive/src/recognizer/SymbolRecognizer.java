package recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains Symbol Recognizer models including fields and methods.
 */
public class SymbolRecognizer implements ISymbolRecognizer {

  private ArrayList<Shape> shapes;
  private ArrayList<Line> trail;
  private double errorRate;
  private int lineCount;
  private int circleCount;
  private int triangleCount;
  private int equalTriangleCount;
  private int snowManCount;
  private int rectangleCount;

  /**
   * constructor to create a Symbol Recognizer.
   *
   * @param e represents the error range we set.
   */
  public SymbolRecognizer(double e) {
    shapes = new ArrayList<>();
    trail = new ArrayList<>();
    errorRate = e;
    lineCount = 0;
    circleCount = 0;
    triangleCount = 0;
    equalTriangleCount = 0;
    snowManCount = 0;
    rectangleCount = 0;
  }

  /**
   * get list of shapes.
   *
   * @return list of shapes.
   */
  public ArrayList<Shape> getShapes() {
    return shapes;
  }

  /**
   * Add a shape to the list of shapes,and do shape checks on the air.
   *
   * @param s shape represents the shape to be added.
   */
  public void addShape(Shape s) {
    lineCount = 0;
    circleCount = 0;
    triangleCount = 0;
    equalTriangleCount = 0;
    snowManCount = 0;
    this.shapes.add(s);
    shapeRec(s);
    System.out.println();

    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i) instanceof Line) {
        lineCount++;
      } else if (shapes.get(i) instanceof Circle) {
        circleCount++;
      } else if (shapes.get(i) instanceof Triangle) {
        triangleCount++;
      } else if (shapes.get(i) instanceof EqualTriangle) {
        equalTriangleCount++;
      } else if (shapes.get(i) instanceof Snowman) {
        snowManCount++;
      } else if (shapes.get(i) instanceof Rectangle) {
        rectangleCount++;
      }
    }
    System.out.println(
        "We have " + lineCount + " lines,\n" + circleCount + " circles,\n" + triangleCount
            + " Triangles\n" + equalTriangleCount + " Equilateral triangles,\n"
            + snowManCount + " Snowmen" + " and " + rectangleCount + "rectangles");
  }

  /**
   * Do the shape recognition every time adding a shape.
   *
   * @param s shape represents the shape added this time.
   */
  public void shapeRec(Shape s) {
    if (s instanceof Line) {
      triangleRec();
      equalTriangleRec();
      rectangleRec();
      deathlyHallowRec();
      snowman_hat_handsRec();
    } else if (s instanceof Circle) {
      snowManRec();
      deathlyHallowRec();
      snowman_hat_handsRec();
    } else {
      throw new IllegalArgumentException("input shape should be either line or circle.");
    }
  }

  /**
   * recognize Snowman_hat_hands.
   */
  public void snowman_hat_handsRec() {
    if (shapes.size() < 4) {
      return;
    }
    Snowman snowman = null;
    Triangle hat = null;
    Line leftHand = null;
    Line rightHand = null;
    List<Line> lineList = new ArrayList<>();

    for (int i = shapes.size() - 1; i >= 0; i--) {
      if (shapes.get(i) instanceof Line) {
        Line line = (Line) shapes.get(i);
        if (lineList.size() > 1) {
          return;
        } else {
          lineList.add(line);
        }
      } else if (shapes.get(i) instanceof Snowman) {
        if (snowman == null) {
          snowman = (Snowman) shapes.get(i);
        } else {
          return;
        }
      } else if (shapes.get(i) instanceof Triangle) {
        if (hat == null) {
          hat = (Triangle) shapes.get(i);
        } else {
          return;
        }
      }
      if (snowman != null && hat != null && lineList.size() == 2) {
        break;
      }
    }
    if (snowman == null || hat == null || lineList.size() < 2) {
      return;
    }
    if ((lineList.get(0).getP1().distance(snowman.getC2().getC()) - snowman.getC2().getR())
        < errorRate
        || (lineList.get(0).getP2().distance(snowman.getC2().getC()) - snowman.getC2().getR())
        < errorRate
        && (lineList.get(1).getP1().distance(snowman.getC2().getC()) - snowman.getC2().getR())
        < errorRate
        || (lineList.get(1).getP2().distance(snowman.getC2().getC()) - snowman.getC2().getR())
        < errorRate) {
      int line1Cnt = 0;
      int line2Cnt = 0;
      double angle1 = 0;
      double angle2 = 0;
      double angle = 0;
      List<Point> pointList1 = new ArrayList<>();
      List<Point> pointList2 = new ArrayList<>();
      pointList1.add(lineList.get(0).getP1());
      pointList1.add(lineList.get(0).getP2());
      pointList2.add(lineList.get(1).getP1());
      pointList2.add(lineList.get(1).getP2());

      for (int i = 0; i < 2; i++) {
        if (pointList1.get(i).distance(snowman.getC2().getC()) - snowman.getC2().getR()
            < errorRate) {
          double x1 = pointList1.get(1 - i).getX() - snowman.getC2().getC().getX();
          double y1 = pointList1.get(1 - i).getY() - snowman.getC2().getC().getY();
          double x2 = snowman.getC3().getC().getX() - snowman.getC2().getC().getX();
          double y2 = snowman.getC3().getC().getY() - snowman.getC2().getC().getY();
          angle1 = Math.acos(
              (x1 * x2 + y1 * y2) / (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
          line1Cnt = i;
        }
        if (pointList2.get(i).distance(snowman.getC2().getC()) - snowman.getC2().getR()
            < errorRate) {
          double x1 = pointList2.get(1 - i).getX() - snowman.getC2().getC().getX();
          double y1 = pointList2.get(1 - i).getY() - snowman.getC2().getC().getY();
          double x2 = snowman.getC3().getC().getX() - snowman.getC2().getC().getX();
          double y2 = snowman.getC3().getC().getY() - snowman.getC2().getC().getY();
          angle2 = Math.acos(
              (x1 * x2 + y1 * y2) / (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
          line2Cnt = i;
        }
      }
      double x1 = pointList1.get(1 - line1Cnt).getX() - snowman.getC2().getC().getX();
      double y1 = pointList1.get(1 - line1Cnt).getY() - snowman.getC2().getC().getY();
      double x2 = pointList2.get(1 - line2Cnt).getX() - snowman.getC2().getC().getX();
      double y2 = pointList2.get(1 - line2Cnt).getY() - snowman.getC2().getC().getY();
      angle = Math.acos(
          (x1 * x2 + y1 * y2) / (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
      if (Math.abs(angle - angle1 - angle2) < errorRate
          && (x1 * x2) <= 0
          || (y1 * y2 <= 0)) {
        leftHand = lineList.get(0);
        rightHand = lineList.get(1);
      } else {
        return;
      }
    } else {
      return;
    }

    for (int i = 0; i < 3; i++) {
      if (hat.getLines().get(i).distPoint(snowman.getC3().getC()) - snowman.getC3().getR()
          < errorRate) {
        if (hat.getPoints().get(i).distance(snowman.getC2().getC())
            - hat.getPoints().get(i).distance(snowman.getC3().getC())
            - snowman.getC3().getC().distance(snowman.getC2().getC()) < errorRate) {
          break;
        }
      }
      if (i == 2) {
        return;
      }
    }
    shapes.remove(snowman);
    shapes.remove(hat);
    shapes.remove(leftHand);
    shapes.remove(rightHand);
    shapes.add(new Snowman_Hat_Hands(snowman, hat, leftHand, rightHand));
  }

  /**
   * check if there is a deathly hallow.
   */
  public void deathlyHallowRec() {
    if (shapes.size() < 3) {
      return;
    }
    Line stick = null;
    Circle stone = null;
    Triangle cloak1 = null;
    EqualTriangle cloak2 = null;
    for (int i = shapes.size() - 1; i >= 0; i--) {
      if (shapes.get(i) instanceof Line) {
        if (stick == null) {
          stick = (Line) shapes.get(i);
        } else {
          return;
        }
      } else if (shapes.get(i) instanceof Triangle) {
        if (cloak1 == null && cloak2 == null) {
          cloak1 = (Triangle) shapes.get(i);
        } else {
          return;
        }
      } else if (shapes.get(i) instanceof Circle) {
        if (stone == null) {
          stone = (Circle) shapes.get(i);
        } else {
          return;
        }
      } else if (shapes.get(i) instanceof EqualTriangle) {
        if (cloak1 == null && cloak2 == null) {
          cloak2 = (EqualTriangle) shapes.get(i);
        } else {
          return;
        }
      }

      if (stick != null && stone != null && (cloak1 != null || cloak2 != null)) {
        if (cloak1 != null && isDeathlyHallow1(stick, cloak1, stone)) {
          shapes.remove(stick);
          shapes.remove(stone);
          shapes.remove(cloak1);
          shapes.add(new DeathlyHallow(stick, stone, cloak1));
        } else if (cloak2 != null && isDeathlyHallow2(stick, cloak2, stone)) {
          shapes.remove(stick);
          shapes.remove(stone);
          shapes.remove(cloak2);
          shapes.add(new DeathlyHallow(stick, stone, cloak2));
        }
        break;
      }
    }
  }


  /**
   * check if there is a rectangle after we added a new line.
   */
  private void rectangleRec() {
    if (this.shapes.size() < 4) {
      return;
    }
    ArrayList<Line> lines = new ArrayList<>();
    for (int i = shapes.size() - 1; i >= 0; i--) {
      if (shapes.get(i) instanceof Line) {
        lines.add((Line) shapes.get(i));
      }
      if (lines.size() == 4) {
        break;
      }
    }
    if (lines.size() < 4) {
      return;
    } else {
      Line l1 = lines.get(0);
      Line l2 = lines.get(1);
      Line l3 = lines.get(2);
      Line l4 = lines.get(3);
      if (l1.isIntersect(l2, errorRate) && l2.isIntersect(l3, errorRate) && l3
          .isIntersect(l4, errorRate) && l4.isIntersect(l1, errorRate)) {
        //check if four line segments connect consecutively.
        boolean b1 = l1.intersection(l2).distance(l2.intersection(l3)) < errorRate;
        boolean b2 = l2.intersection(l3).distance(l3.intersection(l4)) < errorRate;
        boolean b3 = l1.intersection(l2).distance(l4.intersection(l1)) < errorRate;
        if (b1 || b2 || b3 || l1.isIntersect(l3, errorRate) || l2.isIntersect(l4, errorRate)) {
          return;
        } else if (l1.isTangent(l2, errorRate)
            && l2.isTangent(l3, errorRate)
            && l3.isTangent(l4, errorRate)
            && l4.isTangent(l1, errorRate)) {

          shapes.remove(l1);
          shapes.remove(l2);
          shapes.remove(l3);
          shapes.remove(l4);
          shapes.add(new Rectangle(l1, l2, l3, l4));
        }
      }
    }
  }

  /**
   * check if there is a triangle after we added a new line.
   *
   * @return true if there is a triangle.
   */
  public boolean triangleRec() {
    Line l1;
    Line l2;
    Line l3;
    if (this.shapes.size() < 3) {
      return false;
    }
    int count = 0;
    ArrayList<Line> al = new ArrayList<>();

    for (int i = this.shapes.size() - 1; i >= 0; i--) {
      if (count <= 3) {
        if (shapes.get(i) instanceof Line) {
          al.add((Line) this.shapes.get(i));
          count++;
        }
      } else {
        break;
      }
      if (i == 0 && al.size() < 3) {
        return false;
      }
    }
    l1 = al.get(0);
    l2 = al.get(1);
    l3 = al.get(2);

    if (l1.isIntersect(l2, errorRate) && l2.isIntersect(l3, errorRate) && l3
        .isIntersect(l1, errorRate)) {
      double d1 = l1.intersection(l2).distance(l2.intersection(l3));
      if (d1 < errorRate
          || l2.intersection(l3).distance(l3.intersection(l1)) < errorRate) {
        return false;
      } else {
        shapes.remove(l1);
        shapes.remove(l2);
        shapes.remove(l3);
        shapes.add(new Triangle(l1, l2, l3));
        return true;
      }
    } else {
      return false;
    }
  }

  /**
   * Check if the triangle we formed just now is a Equilateral Triangle.
   *
   * @return true if it is the false otherwise.
   */
  public boolean equalTriangleRec() {
    if (shapes.get(shapes.size() - 1) instanceof Triangle) {
      try {
        Triangle triangle = (Triangle) shapes.get(shapes.size() - 1);
        if (areTheyClose(triangle.getL1().length(), triangle.getL2().length(),
            triangle.getL3().length())) {
          System.out.println("An Equilateral triangle has been detected.");
          shapes.remove(shapes.size() - 1);
          shapes.add(new EqualTriangle(triangle.getL1(), triangle.getL2(), triangle.getL3()));
          return true;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return false;
  }

  /**
   * This function checks whether 3 double numbers are close enough.
   *
   * @param d1 represents 1of the 3 double numbers.
   * @param d2 represents 1of the 3 double numbers.
   * @param d3 represents 1of the 3 double numbers.
   * @return true if they are close and false otherwise.
   */
  private boolean areTheyClose(double d1, double d2, double d3) {
    double mean = (d1 + d2 + d3) / 3;
    return (d1 > mean - errorRate && d1 < mean + errorRate
        && d2 > mean - errorRate && d2 < mean + errorRate
        && d3 > mean - errorRate && d3 < mean + errorRate);
  }

  /**
   * This function checks if the shape list contains snowman object.
   *
   * @return true if contains and false otherwise.
   */
  public boolean snowManRec() {
    Circle c1;
    Circle c2;
    Circle c3;
    int headIndex = 0;
    int neckIndex = 0;
    int bodyIndex = 0;
    if (this.shapes.size() < 3) {
      return false;
    }
    try {
      int count = 0;
      ArrayList<Circle> ac = new ArrayList<>();
      for (int i = this.shapes.size() - 1; i >= 0; i--) {
        if (count < 3) {
          if (shapes.get(i) instanceof Circle) {
            ac.add((Circle) this.shapes.get(i));
            count++;
          }
        } else {
          break;
        }
        if (i == 0 && ac.size() < 3) {
          return false;
        }
      }
      for (int i = 0; i < 3; i++) {
        if (ac.get(i).getR() < ac.get(headIndex).getR()) {
          headIndex = i;
        }
        if (ac.get(i).getR() > ac.get(bodyIndex).getR()) {
          bodyIndex = i;
        }
      }
      for (int i = 0; i < 3; i++) {
        if (i != headIndex && i != bodyIndex) {
          neckIndex = i;
        }
      }
      //body
      c1 = ac.get(bodyIndex);
      //neck
      c2 = ac.get(neckIndex);
      //head
      c3 = ac.get(headIndex);
      if ((c3.isTangent(c2, errorRate) && c2.isTangent(c1, errorRate)
          && c1.getC().distance(c3.getC()) - c1.getC().distance(c2.getC()) - c2.getC()
          .distance(c3.getC()) < errorRate) && c1.getC()
          .sameLine(c2.getC(), c3.getC(), errorRate)) {
        System.out.println("A snowman has been detected.");
        shapes.remove(c1);
        shapes.remove(c2);
        shapes.remove(c3);
        shapes.add(new Snowman(c1, c2, c3));
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * get trail.
   *
   * @return trail.
   */
  public ArrayList<Line> getTrail() {
    return trail;
  }

  /**
   * get errorrange.
   *
   * @return errorrange.
   */
  public double getErrorRate() {
    return errorRate;
  }

  /**
   * check if the symbol is a valid deathly hallow.
   *
   * @param line stick.
   * @param triangle cloak.
   * @param circle stone.
   * @return boolean.
   */
  private boolean isDeathlyHallow1(Line line, Triangle triangle, Circle circle) {
    List<Point> pointList = new ArrayList<>();
    pointList.add(triangle.getL1().intersection(triangle.getL2()));
    pointList.add(triangle.getL2().intersection(triangle.getL3()));
    pointList.add(triangle.getL3().intersection(triangle.getL1()));
    List<Line> lineList = new ArrayList<>();
    lineList.add(triangle.getL3());
    lineList.add(triangle.getL1());
    lineList.add(triangle.getL2());

    boolean lineIntersectTriangle = false;
    boolean circleIntersectTriangle = true;

    for (int i = 0; i < 3; i++) {
      if (line.getP1().distance(pointList.get(i)) < errorRate
          && line.getP2().distance(lineList.get(i).getMiddlePoint()) < errorRate) {
        lineIntersectTriangle = true;
        break;
      }
      if (line.getP2().distance(pointList.get(i)) < errorRate
          && line.getP1().distance(lineList.get(i).getMiddlePoint()) < errorRate) {
        lineIntersectTriangle = true;
        break;
      }
    }

    for (Line l : lineList) {
      if (Math.abs(l.distPoint(circle.getC()) - circle.getR()) > errorRate) {
        circleIntersectTriangle = false;
      }
    }

    return lineIntersectTriangle && circleIntersectTriangle;
  }

  /**
   * check if the symbol is a valid deathly hallow.
   *
   * @param line stick.
   * @param triangle cloak.
   * @param circle stone.
   * @return boolean.
   */
  private boolean isDeathlyHallow2(Line line, EqualTriangle triangle, Circle circle) {
    List<Point> pointList = new ArrayList<>();
    pointList.add(triangle.getLine1().intersection(triangle.getLine2()));
    pointList.add(triangle.getLine2().intersection(triangle.getLine3()));
    pointList.add(triangle.getLine3().intersection(triangle.getLine1()));
    List<Line> lineList = new ArrayList<>();
    lineList.add(triangle.getLine3());
    lineList.add(triangle.getLine1());
    lineList.add(triangle.getLine2());

    boolean lineIntersectTriangle = false;
    boolean circleIntersectTriangle = true;

    for (int i = 0; i < 3; i++) {
      if (line.getP1().distance(pointList.get(i)) < errorRate
          && line.getP2().distance(lineList.get(i).getMiddlePoint()) < errorRate) {
        lineIntersectTriangle = true;
        break;
      }
      if (line.getP2().distance(pointList.get(i)) < errorRate
          && line.getP1().distance(lineList.get(i).getMiddlePoint()) < errorRate) {
        lineIntersectTriangle = true;
        break;
      }
    }
    for (Line l : lineList) {
      if (Math.abs(l.distPoint(circle.getC()) - circle.getR()) > errorRate) {
        circleIntersectTriangle = false;
      }
    }
    return lineIntersectTriangle && circleIntersectTriangle;
  }
}