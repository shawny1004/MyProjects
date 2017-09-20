package recognizer;

/**
 * This class contains methods and fields of EqualTriangle.
 */
public class EqualTriangle implements Shape {

  Line line1;
  Line line2;
  Line line3;

  /**
   * Construct an EqualTriangle with given 3 lines.
   *
   * @param l1 represents 1of the 3 lines.
   * @param l2 represents 1of the 3 lines.
   * @param l3 represents 1of the 3 lines.
   */
  public EqualTriangle(Line l1, Line l2, Line l3) {
    line1 = l1;
    line2 = l2;
    line3 = l3;
  }

  /**
   * get line1.
   * @return line1.
   */
  public Line getLine1() {
    return line1;
  }

  /**
   * get line2.
   * @return line2.
   */
  public Line getLine2() {
    return line2;
  }

  /**
   * get line3.
   * @return line3.
   */
  public Line getLine3() {
    return line3;
  }
}
