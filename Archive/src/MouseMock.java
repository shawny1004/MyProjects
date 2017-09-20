import java.awt.Component;
import java.awt.event.MouseEvent;

/**
 * This class mocks the mouseEvent for the tests.
 */
public class MouseMock extends MouseEvent {

  private int x = 10;
  private int y = 10;

  /**
   * Constructs a mouseMock object with given parameters.
   */
  public MouseMock(Component component, int i, long l, int i1, int i2, int i3, int i4,
      boolean b, int i5) {
    super(component, i, l, i1, i2, i3, i4, b, i5);
  }


  /**
   * Override the getX() function of the mouseEvent.
   */
  @Override
  public int getX() {
    return x;
  }

  /**
   * Override the getY() function of the mouseEvent.
   */
  @Override
  public int getY() {
    return y;
  }
}
