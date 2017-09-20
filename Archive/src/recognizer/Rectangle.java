package recognizer;

import java.util.List;

/**
 * Created by lyly513 on 4/20/17.
 */
public class Rectangle implements Shape{
  private Line l1;
  private Line l2;
  private Line l3;
  private Line l4;

  public Rectangle(Line l1, Line l2, Line l3, Line l4) {
    this.l1 = l1;
    this.l2 = l2;
    this.l3 = l3;
    this.l4 = l4;
  }

  public Line getL1() {
    return l1;
  }

  public Line getL2() {
    return l2;
  }

  public Line getL3() {
    return l3;
  }

  public Line getL4() {
    return l4;
  }

  public void setL1(Line l1) {
    this.l1 = l1;
  }

  public void setL2(Line l2) {
    this.l2 = l2;
  }

  public void setL3(Line l3) {
    this.l3 = l3;
  }

  public void setL4(Line l4) {
    this.l4 = l4;
  }
}
