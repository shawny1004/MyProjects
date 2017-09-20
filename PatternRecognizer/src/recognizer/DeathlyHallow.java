package recognizer;

/**
 * Created by lyly513 on 4/23/17.
 */
public class DeathlyHallow implements Shape {
  private Line line;
  private Circle circle;
  private Triangle triangle;
  private EqualTriangle equalTriangle;
  private boolean withTriangle = false;
  private boolean withEqualTriangle = false;

  /**
   * constructor with triangle.
   * @param line stick.
   * @param circle stone.
   * @param triangle cloak.
   */
  public DeathlyHallow(Line line, Circle circle, Triangle triangle) {
    this.line = line;
    this.circle = circle;
    this.triangle = triangle;
    withTriangle = true;
  }

  /**
   * constructor with equilateral triangle.
   * @param line stick.
   * @param circle stone.
   * @param equalTriangle cloak.
   */
  public DeathlyHallow(Line line, Circle circle, EqualTriangle equalTriangle) {
    this.line = line;
    this.circle = circle;
    this.equalTriangle = equalTriangle;
    withEqualTriangle = true;
  }

  /**
   * return if the deathly hallow is made with triangle.
   * @return boolean.
   */
  public boolean isWithTriangle() {
    return withTriangle;
  }

  /**
   * return if the deathly hallow is made with equilateral triangle.
   * @return boolean.
   */
  public boolean isWithEqualTriangle() {
    return withEqualTriangle;
  }

  /**
   * get stick.
   * @return stick.
   */
  public Line getLine() {
    return line;
  }

  /**
   * get stone.
   * @return stone.
   */
  public Circle getCircle() {
    return circle;
  }

  /**
   * get cloak.
   * @return cloak.
   */
  public Triangle getTriangle() {
    return triangle;
  }

  /**
   * get cloak.
   * @return cloak.
   */
  public EqualTriangle getEqualTriangle() {
    return equalTriangle;
  }
}
