package recognizer;

/**
 * The interface of SymbolRecognizer.
 */
public interface ISymbolRecognizer {

  /**
   * Add a shape to the list of shapes,and do shape checks on the air.
   *
   * @param s shape represents the shape to be added.
   */
  void addShape(Shape s);

  /**
   * Do the shape recognition every time adding a shape.
   *
   * @param s shape represents the shape added this time.
   */
  void shapeRec(Shape s);

  /**
   * check if there is a triangle after we added a new line.
   *
   * @return true if there is a triangle.
   */
  boolean triangleRec();

  /**
   * Check if the triangle we formed just now is a equaTriangle.
   *
   * @return true if it is the false otherwise.
   */
  boolean equalTriangleRec();

  /**
   * This function checks if the shape list contains snowman object.
   *
   * @return true if contains and false otherwise.
   */
  boolean snowManRec();
}
