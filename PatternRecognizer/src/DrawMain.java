import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import recognizer.SymbolRecognizer;

/**
 * Created by lyly513 on 4/11/17.
 */
public class DrawMain {

  /**
   * DrawMain function.
   *
   * @param args args.
   */
  public static void main(String[] args) {
    SymbolRecognizer symbolRecognizer = new SymbolRecognizer(20.0);
    Controller controller = new Controller(symbolRecognizer);
    View view = new View(symbolRecognizer, controller);
    controller.setView(view);
  }
}
