
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.MouseInputListener;
import recognizer.Line;
import recognizer.Point;
import recognizer.SymbolRecognizer;

/**
 * Created by lyly513 on 4/11/17.
 */
public class Controller {

  private SymbolDrawer symbolDrawer;
  private View view;

  public SymbolDrawer getSymbolDrawer() {
    return symbolDrawer;
  }

  public View getView() {
    return view;
  }

  /**
   * controller constructor.
   *
   * @param symbolRecognizer symbolRecognizer.
   */
  public Controller(SymbolRecognizer symbolRecognizer) {
    this.symbolDrawer = new SymbolDrawer(symbolRecognizer);
    view = new View(symbolRecognizer, this);
  }

  public void setView(View view) {
    this.view = view;
    view.setMouseListener(new MouseAdapter());
  }

  /**
   * add shape to shapes.
   */
  public void addShape() {
    symbolDrawer.addShape();
  }

  /**
   * set mouse start x.
   *
   * @param mouseStartX x coordinate.
   */
  public void setMouseStartX(int mouseStartX) {
    symbolDrawer.setMouseStartX(mouseStartX);
  }

  /**
   * set mouse start y.
   *
   * @param mouseStartY y coordinate.
   */
  public void setMouseStartY(int mouseStartY) {
    symbolDrawer.setMouseStartY(mouseStartY);
  }

  /**
   * get trail.
   *
   * @return trail of mouse.
   */
  public List<Point> getTrail() {
    return symbolDrawer.getTrail();
  }

  /**
   * calculate and set trailx and traily.
   */
  public void getTrailXY() {
    symbolDrawer.getTrailXY();
  }

  /**
   * set average x of trail.
   *
   * @param trailX x coordinate.
   */
  public void setTrailX(double trailX) {
    symbolDrawer.setTrailX(trailX);
  }

  /**
   * set average y of trail.
   *
   * @param trailY y coordinate.
   */
  public void setTrailY(double trailY) {
    symbolDrawer.setTrailY(trailY);
  }

  /**
   * set current x.
   *
   * @param currentX x coordinate.
   */
  public void setCurrentX(int currentX) {
    symbolDrawer.setCurrentX(currentX);
  }

  /**
   * set current y.
   *
   * @param currentY y coordinate.
   */
  public void setCurrentY(int currentY) {
    symbolDrawer.setCurrentY(currentY);
  }

  /**
   * get current x.
   *
   * @return x coordinate.
   */
  public int getCurrentX() {
    return symbolDrawer.getCurrentX();
  }

  /**
   * get current y.
   *
   * @return y coordinate.
   */
  public int getCurrentY() {
    return symbolDrawer.getCurrentY();
  }

  class MouseAdapter implements MouseInputListener {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent e) {
      setMouseStartX(e.getX());
      setMouseStartY(e.getY());
    }


    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      getTrailXY();
      addShape();
      view.repaint();
      setTrailX(0);
      setTrailY(0);
      view.clearTrail();
      symbolDrawer.getSymbolRecognizer().getTrail().clear();
    }


    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      symbolDrawer.getTrail().add(new Point(e.getX(), e.getY()));
      if (getCurrentX() != e.getX() && getCurrentY() != e.getY()) {
        symbolDrawer.getSymbolRecognizer().getTrail()
            .add(new Line(new Point(getCurrentX(), getCurrentY()),
                new Point(e.getX(), e.getY())));
        setCurrentX(e.getX());
        setCurrentY(e.getY());
        view.repaint();
      }
    }


    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      setCurrentX(e.getX());
      setCurrentY(e.getY());
    }

  }
}
