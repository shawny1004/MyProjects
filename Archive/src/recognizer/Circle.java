package recognizer;

/**
 * Created by Shuo on 4/5/2017.
 */
public class Circle implements Shape {

  private Point c;
  private double r;

  /**
   * constructor to create a circle.
   *
   * @param c center of circle.
   * @param r radius.
   * @throws IllegalArgumentException if the given radius is less or equal to 0.
   */
  public Circle(Point c, double r) throws IllegalArgumentException {
    if (r <= 0) {
      throw new IllegalArgumentException("The radius you entered is not valid.");
    } else {
      this.c = c;
      this.r = r;
    }
  }

  /**
   * get center of the current circle.
   *
   * @return center as a Point.
   */
  public Point getC() {
    return c;
  }

  /**
   * get radius of the current circle.
   *
   * @return radius as a double number.
   */
  public double getR() {
    return r;
  }

  /**
   * Determine whether 2 circles are tangent with each other.
   *
   * @param cir Circle that are being checked with.
   * @param a represents the approximate parameter ranges from 0.0-1.0
   * @return whether the 2 circles are tangent with given error range.
   */
  public boolean isTangent(Circle cir, Double a) throws IllegalArgumentException {
    if (this.c.getX() == cir.c.getX() && this.c.getY() == cir.c.getY()) {
      return false;
    } else {
      return ((this.r + cir.r) > (this.c.distance(cir.c) - a)
          && (this.r + cir.r) < (this.c.distance(cir.c) + a));
    }
  }
}
