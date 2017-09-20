package recognizer;

/**
 * Created by lyly513 on 4/24/17.
 */
public class Snowman_Hat_Hands implements Shape{
  private Snowman snowman;
  private Triangle hat;
  private Line leftHand = null;
  private Line rightHand = null;

  public Snowman_Hat_Hands(Snowman snowman, Triangle hat, Line leftHand, Line rightHand) {
    this.snowman = snowman;
    this.hat = hat;
    this.leftHand = leftHand;
    this.rightHand = rightHand;
  }

  /**
   * get snow man.
   * @return snowman.
   */
  public Snowman getSnowman() {
    return snowman;
  }

  /**
   * get hat.
   * @return hat.
   */
  public Triangle getHat() {
    return hat;
  }

  /**
   * get left hand.
   * @return left hand.
   */
  public Line getLeftHand() {
    return leftHand;
  }

  /**
   * get right hand.
   * @return right hand.
   */
  public Line getRightHand() {
    return rightHand;
  }
}
