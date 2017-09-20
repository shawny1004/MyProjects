package recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods and fields of Triangle.
 */
public class Triangle implements Shape {

  private Line l1;
  private Line l2;
  private Line l3;


  /**
   * Construct an Triangle with given 3 lines.
   *
   * @param l1 represents 1of the 3 lines.
   * @param l2 represents 1of the 3 lines.
   * @param l3 represents 1of the 3 lines.
   */
  public Triangle(Line l1, Line l2, Line l3) {
    this.l1 = l1;
    this.l2 = l2;
    this.l3 = l3;
  }

  /**
   * get line1.
   *
   * @return line1
   */
  public Line getL1() {
    return l1;
  }

  /**
   * get line2.
   *
   * @return line2.
   */
  public Line getL2() {
    return l2;
  }

  /**
   * get line3.
   *
   * @return line3
   */
  public Line getL3() {
    return l3;
  }

  /**
   * get list of lines.
   * @return list of lines.
   */
  public List<Line> getLines(){
    List<Line> lineList = new ArrayList<>();
    lineList.add(l1);
    lineList.add(l2);
    lineList.add(l3);
    return lineList;
  }

  /**
   * get list of points.
   * @return list of points.
   */
  public List<Point> getPoints(){
    List<Point> pointList = new ArrayList<>();
    pointList.add(l2.intersection(l3));
    pointList.add(l3.intersection(l1));
    pointList.add(l1.intersection(l2));
    return pointList;
  }
}
