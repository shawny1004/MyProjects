package recognizer;

/**
 * This class contains methods and fields of Line.
 */
public class Line implements Shape {

  private double a;
  private double b;
  private double c;
  private Point p1;
  private Point p2;

  /**
   * get line segment between two points.
   *
   * @param p1 point1.
   * @param p2 point2.
   * @throws IllegalArgumentException if the 2 points are identical.
   */
  public Line(Point p1, Point p2) throws IllegalArgumentException {
    if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
      throw new IllegalArgumentException("These 2 points are the same !");
    }
    this.p1 = p1;
    this.p2 = p2;

    this.a = p2.getY() - p1.getY();
    this.b = p1.getX() - p2.getX();
    this.c = p2.getX() * p1.getY() - p1.getX() * p2.getY();
  }

  /**
   * get coefficient a of the line.
   *
   * @return a as double number.
   */
  public double getA() {
    return a;
  }

  /**
   * get coefficient b of the line.
   *
   * @return b as double number.
   */
  public double getB() {
    return b;
  }

  /**
   * get coefficient c of the line.
   *
   * @return c as double number.
   */
  public double getC() {
    return c;
  }

  /**
   * get point1 of the line.
   */
  public Point getP1() {
    return p1;
  }

  /**
   * get point2 of the line.
   */
  public Point getP2() {
    return p2;
  }


  /**
   * @param l Represents the other line to be compared with.
   * @param a Represents the approximate parameter ranges from 0.0-1.0; e.g. 0.05 stands for 5%.
   * @return a boolean result indicates whether the given line segment intersecting with another.
   */
  public boolean isIntersect(Line l, Double a) {
    return (this.getP1().distance(l.getP1()) <= a || this.getP1().distance(l.getP2()) <= a
        || this.getP2().distance(l.getP1()) <= a || this.getP2().distance(l.getP2()) <= a);
  }

  /**
   * return the intersection point if possible;
   *
   * @param l represents the other line to be compared with;
   * @return the intersection point and null if does not exist with given 1.0 error range.
   */
  public Point intersection(Line l) {
    if (this.getP1().distance(l.getP1()) <= a || this.getP1().distance(l.getP2()) <= a) {
      return this.getP1();
    } else {
      return this.getP2();
    }
  }

  /**
   * get length of the line segment.
   */
  public double length() {
    return this.p1.distance(p2);
  }

  public boolean isTangent(Line other, double error) {
    double temp = (this.getA() * other.getA()) + (this.getB() * other.getB());

    if (temp == 0) {
      return true;
    } else {
      double tan = (this.getA() * other.getB() - this.getB() * other.getA()) / temp;
      double angle = Math.abs(Math.atan(Math.abs(tan))/Math.PI*180-90);
      if (angle<error){
        return true;
      }else{
        return false;
      }
    }
  }

  public Point getMiddlePoint(){
    return new Point(0.5*(getP1().getX()+getP2().getX()), 0.5*(getP2().getY()+getP2().getY()));
  }

  public double distPoint(Point point){
    return (Math.abs(getA()*point.getX()+getB()*point.getY()+getC())
        /Math.sqrt(getA()*getA()+getB()*getB()));
  }

}
