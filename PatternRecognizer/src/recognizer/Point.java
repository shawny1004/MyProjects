package recognizer;

/**
 * This class contains methods and fields of Point.
 */
public class Point {

  private double x;
  private double y;

  /**
   * Construct a point with given x and y.
   *
   * @param x represents the x.
   * @param y represents the y.
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get x value of the point.
   *
   * @return x value as double number.
   */
  public double getX() {
    return x;
  }

  /**
   * Get y value of the point.
   *
   * @return y value as double number.
   */
  public double getY() {
    return y;
  }

  /**
   * Calculate the distance between this point and given point.
   *
   * @param p represents the point to be calculated with.
   * @return the distance as a double number.
   */
  public double distance(Point p) {
    return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
  }

  /**
   * Check if the current point is in line with other 2 points under given error range.
   *
   * @param p1 represents 1of the 2 given points.
   * @param p2 represents 1of the 2 given points.
   * @param err represents the error range.
   * @return true if they are in the same line and false otherwise.
   */

  public boolean sameLine(Point p1, Point p2, double err) {
    Line l = new Line(p1, p2);
    return distPointToLine(l) <= err*3;
  }

  /**
   * Return the distance between a point and a line.
   *
   * @param line represents the line to be calculate with.
   * @return the distance as a double number.
   */
  public double distPointToLine(Line line) {
    return Math.abs((this.getX() * line.getA() + this.getY() * line.getB() + line.getC()) / Math
        .sqrt(line.getA() * line.getA() + line.getB() * line.getB()));
  }
}
