import java.awt.Color;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import recognizer.Point;
import recognizer.SymbolRecognizer;

/**
 * Created by lyly513 on 4/14/17.
 */
public class View extends JPanel{

  private BlankArea blankArea;
  private Controller controller;
  private List<Point> trail;

  public BlankArea getBlankArea() {
    return blankArea;
  }

  public List<Point> getTrail() {
    return trail;
  }

  /**
   * Creates a new <code>JPanel</code> with a double buffer
   * and a flow layout.
   */
  public View(SymbolRecognizer symbolRecognizer, Controller controller) {
    blankArea = new BlankArea(Color.white, symbolRecognizer);
    add(blankArea);
    this.controller = controller;
    trail = controller.getTrail();
    createAndShowGUI();
  }

  /**
   * set mouseListener.
   * @param mouseListener mouseListener.
   */
  public void setMouseListener(Controller.MouseAdapter mouseListener){
    blankArea.addMouseListener(mouseListener);
    blankArea.addMouseMotionListener(mouseListener);
  }

  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event-dispatching thread.
   */
  public void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("Symbol Drawer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the content pane.
    JComponent newContentPane = this;
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);
    blankArea.repaint();

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public void clearTrail(){
    trail.clear();
  }

}
