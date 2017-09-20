package recognizer;

import java.util.Scanner;

/**
 * The DrawMain interactive class for the recognizer.
 */
public class RecognizerMain {

  /**
   * Main entry for the symbol recognizer.
   *
   * @param a follow the convention.
   */
  public static void main(String[] a) {
    Scanner sc = new Scanner(System.in);
    double err = 0.1;//in default
    System.out.println("Welcome to use Symbol Recognizer");
    SymbolRecognizer sr = new SymbolRecognizer(err);
    System.out.println("Symbol Recognizer created! With 0.1 error range.");
    while (true) {
      System.out.println();
      System.out.println("-------------------------------------");
      System.out.println(
          "Please choose which Shape you want to add,\n"
              + "press L for line,\n"
              + "C for circle,\n"
              + "D to delete last shape, \n"
              + "Q to quit the program");
      String c = sc.next().toLowerCase().substring(0, 1);
      switch (c) {
        case ("l"):
          System.out.println("Please enter the X value for first point on the line");
          double x1 = sc.nextDouble();
          System.out.println("Please enter the Y value for first point on the line");
          double y1 = sc.nextDouble();
          System.out.println("Please enter the X value for second point on the line");
          double x2 = sc.nextDouble();
          System.out.println("Please enter the Y value for second point on the line");
          double y2 = sc.nextDouble();
          System.out.println("A new line has been added to the recognizer.");
          sr.addShape(new Line(new Point(x1, y1), new Point(x2, y2)));

          break;
        case ("c"):
          System.out.println("Please enter the X value for the center of the circle");
          double x0 = sc.nextDouble();
          System.out.println("Please enter the Y value for the center of the circle");
          double y0 = sc.nextDouble();
          System.out.println("Please enter the radius value for the circle");
          double r = sc.nextDouble();
          System.out.println("A new Circle has been added to the recognizer.");
          sr.addShape(new Circle(new Point(x0, y0), r));
          break;

        case ("d"):
          if (sr.getShapes().size() == 0) {
            System.out.println("Your shape list is empty now.");
          } else if (sr.getShapes().size() != 0) {
            sr.getShapes().remove(sr.getShapes().size() - 1);
            System.out.println("Shape removed successfully!");
          }
          break;
        case ("q"):
          System.out.println("Program quit successfully!");
          return;
        default:
          break;

      }

    }
  }
}
