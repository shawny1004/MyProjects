package recognizer;

/**
 * This class contains methods and fields of Snowman.
 */
public class Snowman implements Shape {

  private Circle c1;
  private Circle c2;
  private Circle c3;

  /**
   * Construct an Snowman with given 3 Circles.
   *
   * @param c1 represents 1of the 3 Circles.
   * @param c2 represents 1of the 3 Circles.
   * @param c3 represents 1of the 3 Circles.
   */
  public Snowman(Circle c1, Circle c2, Circle c3) {
    if (c1.getR() < c2.getR() || c1.getR() < c3.getR() || c2.getR() < c3.getR()) {
      throw new IllegalArgumentException("sizes of three circles should be decreasing.");
    }
    this.c1 = c1;
    this.c2 = c2;
    this.c3 = c3;
  }

  /**
   * get center of circle1.
   *
   * @return center.
   */
  public Circle getC1() {
    return c1;
  }

  /**
   * get center of circle2.
   *
   * @return center.
   */
  public Circle getC2() {
    return c2;
  }

  /**
   * get center of circle3.
   *
   * @return center.
   */
  public Circle getC3() {
    return c3;
  }
}
