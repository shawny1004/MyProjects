import java.awt.Color;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import recognizer.Point;
import recognizer.SymbolRecognizer;

/**
 * This class is meant to mock the View class for the test.
 */
public class ViewMock extends View {


  /**
   * Creates a new <code>JPanel</code> with a double buffer
   * and a flow layout.
   */
  public ViewMock(SymbolRecognizer symbolRecognizer, Controller controller) {
    super(symbolRecognizer, controller);
  }

  /**
   * The class calls its super class and returns a String to get the status.
   *
   * @return a string that represents the window status.
   */
  public String createAndShowGUITest() {
    super.createAndShowGUI();
    String result = "";
    result += this.getBlankArea().getMinimumSize().width + " *";
    result += this.getBlankArea().getMinimumSize().height + " ";
    result += this.getTrail().size();
    return result;
  }
}
