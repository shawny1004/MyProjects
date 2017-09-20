/**
 * Created by lyly513 on 4/10/17.
 */

import java.util.ArrayList;
import java.util.List;
import recognizer.Circle;
import recognizer.Line;
import recognizer.Point;
import recognizer.SymbolRecognizer;

public class SymbolDrawer {

  private SymbolRecognizer symbolRecognizer;
  private double errorRange;
  private int mouseStartX;
  private int mouseStartY;
  private double trailX = 0;
  private double trailY = 0;
  private int currentX;
  private int currentY;


  private List<Point> trail;

  /**
   * get mouse start x.
   *
   * @return x coordinate.
   */
  public int getMouseStartX() {
    return mouseStartX;
  }

  /**
   * get mouse start y.
   *
   * @return y coordinate.
   */
  public int getMouseStartY() {
    return mouseStartY;
  }

  /**
   * get average x of trail.
   * @return average x.
   */
  public double getTrailX() {
    return trailX;
  }

  /**
   * get average y of trail.
   * @return average y.
   */
  public double getTrailY() {
    return trailY;
  }

  /**
   * Creates a new <code>JPanel</code> with a double buffer
   * and a flow layout.
   */
  public SymbolDrawer(SymbolRecognizer symbolRecognizer) {
    this.symbolRecognizer = symbolRecognizer;
    errorRange = symbolRecognizer.getErrorRate();
    trail = new ArrayList<>();
  }

  /**
   * get distance between two points.
   */
  private double dist(double x1, double y1, double x2, double y2) {
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  /**
   * get current x.
   *
   * @return x coordinate.
   */
  public int getCurrentX() {
    return currentX;
  }

  /**
   * get current y.
   *
   * @return y coordinate.
   */
  public int getCurrentY() {
    return currentY;
  }

  /**
   * get linegoodness.
   *
   * @param trail list of point.
   * @return linegoodness.
   */
  public double getLineGoodness(List<Point> trail) {
    double q;
    double theta1;
    double theta2;
    double fm;
    double sxy = 0;
    double sxx = 0;
    double syy = 0;
    double m = 0;
    double a = 0;
    double b = 0;
    double tmin;
    double tmax;
    double tomin;
    double tomax;

    //calculate line.
    for (Point point : trail) {
      sxy += (point.getX() - trailX) * (point.getY() - trailY);
      sxx += (point.getX() - trailX) * (point.getX() - trailX);
      syy += (point.getY() - trailY) * (point.getY() - trailY);
    }

    q = 2 * sxy / (sxx - syy);
    theta1 = Math.atan(q);
    theta2 = theta1 + 180;
    double[] thetaList = {theta1, theta2};
    for (int i = 0; i < 2; i++) {
      fm = 2 * sxy * Math.sin(thetaList[i]) - (syy - sxx) * Math.cos(thetaList[i]);
      if (fm > 0) {
        m = thetaList[i];
        a = Math.cos(m / 2);
        b = Math.sin(m / 2);
        break;
      }
    }
    tomin = b * (trail.get(0).getX() - trailX) - a * (trail.get(0).getY() - trailY);
    tomax = b * (trail.get(0).getX() - trailX) - a * (trail.get(0).getY() - trailY);
    tmin = a * (trail.get(0).getX() - trailX) + b * (trail.get(0).getY() - trailY);
    tmax = a * (trail.get(0).getX() - trailX) + b * (trail.get(0).getY() - trailY);
    for (Point point : trail) {
      if (a * (point.getX() - trailX) + b * (point.getY() - trailY) < tmin) {
        tmin = a * (point.getX() - trailX) + b * (point.getY() - trailY);
      }
      if (a * (point.getX() - trailX) + b * (point.getY() - trailY) > tmax) {
        tmax = a * (point.getX() - trailX) + b * (point.getY() - trailY);
      }
      if (b * (point.getX() - trailX) - a * (point.getY() - trailY) < tomin) {
        tomin = b * (point.getX() - trailX) - a * (point.getY() - trailY);
      }
      if (b * (point.getX() - trailX) - a * (point.getY() - trailY) > tomax) {
        tomax = b * (point.getX() - trailX) - a * (point.getY() - trailY);
      }
    }
    if (((tomax - tomin) / (tmax - tmin)) < 1) {
      return 1 - ((tomax - tomin) / (tmax - tmin));
    } else {
      return 0;
    }
  }

  /**
   * get circlegoodness.
   *
   * @param trail list of point.
   * @return circlegoodness.
   */
  public double getCircleGoodness(List<Point> trail) {
    double sx = 0;
    double sy = 0;
    double sxy = 0;
    double sxx = 0;
    double syy = 0;
    double sx2y2 = 0;
    double sxx2y2 = 0;
    double syx2y2 = 0;
    double d = 0;
    double da = 0;
    double db = 0;
    double dc = 0;
    double n = trail.size();
    double a;
    double b;
    double c;
    double cx;
    double cy;
    double r;
    double m = 0;
    double q;

    for (Point point : trail) {
      sx += point.getX();
      sy += point.getY();
      sxy += point.getX() * point.getY();
      sxx += point.getX() * point.getX();
      syy += point.getY() * point.getY();
      sx2y2 += (point.getX() * point.getX() + point.getY() * point.getY());
      sxx2y2 += point.getX() * (point.getX() * point.getX() + point.getY() * point.getY());
      syx2y2 += point.getY() * (point.getX() * point.getX() + point.getY() * point.getY());
    }

    d = sxx * (n * syy - sy * sy) - sxy * (n * sxy - sx * sy) + sx * (sxy * sy - syy * sx);
    da = sxx2y2 * (n * syy - sy * sy) - sxy * (n * syx2y2 - sx2y2 * sy) + sx * (syx2y2 * sy
        - syy * sx2y2);
    db = sxx * (n * syx2y2 - sx2y2 * sy) - sxx2y2 * (n * sxy - sx * sy) + sx * (sxy * sx2y2
        - syx2y2 * sx);
    dc = sxx * (syy * sx2y2 - syx2y2 * sy) - sxy * (sxy * sx2y2 - syx2y2 * sx) + sxx2y2 * (sxy * sy
        - syy * sx);

    a = da / d;
    b = db / d;
    c = dc / d;

    cx = a / 2;
    cy = b / 2;
    r = Math.sqrt(c + cx * cx + cy * cy);

    for (Point point : trail) {
      double di = Math.abs(
          (point.getX() - cx) * (point.getX() - cx) + (point.getY() - cy) * (point.getY() - cy)
              - r * r);
      m += di;
    }
    q = Math.sqrt(m) / n;
    if (q / r < 1) {
      return 1 - q / r;
    } else {
      return 0;
    }
  }

  /**
   * get line.
   *
   * @return line.
   */
  public Line getLine() {
    double q;
    double theta1;
    double theta2;
    double fm;
    double trailX = 0;
    double trailY = 0;
    double sxy = 0;
    double sxx = 0;
    double syy = 0;
    double m = 0;
    double a = 0;
    double b = 0;
    double tmin = 0;
    double tmax = 0;

    for (Point point : trail) {
      trailX += point.getX();
      trailY += point.getY();
    }
    trailX /= trail.size();
    trailY /= trail.size();

    //calculate line.
    for (Point point : trail) {
      sxy += (point.getX() - trailX) * (point.getY() - trailY);
      sxx += (point.getX() - trailX) * (point.getX() - trailX);
      syy += (point.getY() - trailY) * (point.getY() - trailY);
    }
    q = 2 * sxy / (sxx - syy);

    theta1 = Math.atan(q);
    theta2 = theta1 + Math.PI;
    double[] thetaList = {theta1, theta2};

    for (int i = 0; i < 2; i++) {
      fm = 2 * sxy * Math.sin(thetaList[i]) - (syy - sxx) * Math.cos(thetaList[i]);
      if (fm > 0) {
        m = thetaList[i];
        a = Math.cos(m / 2);
        b = Math.sin(m / 2);
        break;
      }
    }
    tmin = a * (trail.get(0).getX() - trailX) + b * (trail.get(0).getY() - trailY);
    tmax = a * (trail.get(0).getX() - trailX) + b * (trail.get(0).getY() - trailY);
    for (Point point : trail) {
      if (a * (point.getX() - trailX) + b * (point.getY() - trailY) < tmin) {
        tmin = a * (point.getX() - trailX) + b * (point.getY() - trailY);
      }
      if (a * (point.getX() - trailX) + b * (point.getY() - trailY) > tmax) {
        tmax = a * (point.getX() - trailX) + b * (point.getY() - trailY);
      }
    }
    return new Line(new Point(trailX + tmin * a, trailY + tmin * b),
        new Point(trailX + tmax * a, trailY + tmax * b));
  }

  /**
   * get circle.
   *
   * @return circle.
   */
  public Circle getCircle() {
    double sx = 0;
    double sy = 0;
    double sxy = 0;
    double sxx = 0;
    double syy = 0;
    double sx2y2 = 0;
    double sxx2y2 = 0;
    double syx2y2 = 0;
    double d = 0;
    double da = 0;
    double db = 0;
    double dc = 0;
    double n = trail.size();
    double a;
    double b;
    double c;
    double cx;
    double cy;
    double r;
    double m = 0;
    double q;

    for (Point point : trail) {
      sx += point.getX();
      sy += point.getY();
      sxy += point.getX() * point.getY();
      sxx += point.getX() * point.getX();
      syy += point.getY() * point.getY();
      sx2y2 += (point.getX() * point.getX() + point.getY() * point.getY());
      sxx2y2 += point.getX() * (point.getX() * point.getX() + point.getY() * point.getY());
      syx2y2 += point.getY() * (point.getX() * point.getX() + point.getY() * point.getY());
    }

    d = sxx * (n * syy - sy * sy) - sxy * (n * sxy - sx * sy) + sx * (sxy * sy - syy * sx);
    da = sxx2y2 * (n * syy - sy * sy) - sxy * (n * syx2y2 - sx2y2 * sy) + sx * (syx2y2 * sy
        - syy * sx2y2);
    db = sxx * (n * syx2y2 - sx2y2 * sy) - sxx2y2 * (n * sxy - sx * sy) + sx * (sxy * sx2y2
        - syx2y2 * sx);
    dc = sxx * (syy * sx2y2 - syx2y2 * sy) - sxy * (sxy * sx2y2 - syx2y2 * sx) + sxx2y2 * (sxy * sy
        - syy * sx);

    a = da / d;
    b = db / d;
    c = dc / d;

    cx = a / 2;
    cy = b / 2;
    r = Math.sqrt(c + cx * cx + cy * cy);

    return new Circle(new Point(cx, cy), r);
  }

  /**
   * add shape to shapes.
   */
  public void addShape() {
    double lineGoodness;
    double circleGoodness;
    if (!trail.isEmpty()) {
      lineGoodness = getLineGoodness(trail);
      circleGoodness = getCircleGoodness(trail);

      if (lineGoodness > 0.4) {
        symbolRecognizer.addShape(getLine());
      } else if (circleGoodness > 0.9) {
        symbolRecognizer.addShape(getCircle());
      }
    }
  }

  /**
   * set mouse start x.
   *
   * @param mouseStartX x.
   */
  public void setMouseStartX(int mouseStartX) {
    this.mouseStartX = mouseStartX;
  }

  /**
   * set mouse start y.
   *
   * @param mouseStartY y.
   */
  public void setMouseStartY(int mouseStartY) {
    this.mouseStartY = mouseStartY;
  }

  /**
   * get trail.
   *
   * @return trail.
   */
  public List<Point> getTrail() {
    return trail;
  }

  /**
   * calculate and set trailx and traily.
   */
  public void getTrailXY() {
    for (Point point : trail) {
      trailX += point.getX();
      trailY += point.getY();
    }
    trailX /= trail.size();
    trailY /= trail.size();
  }

  /**
   * set trailx.
   *
   * @param trailX x coordinate.
   */
  public void setTrailX(double trailX) {
    this.trailX = trailX;
  }

  /**
   * set traily.
   *
   * @param trailY y coordinate.
   */
  public void setTrailY(double trailY) {
    this.trailY = trailY;
  }

  /**
   * set currentx.
   *
   * @param currentX x coordinate.
   */
  public void setCurrentX(int currentX) {
    this.currentX = currentX;
  }

  /**
   * set currenty.
   *
   * @param currentY y coordinate.
   */
  public void setCurrentY(int currentY) {
    this.currentY = currentY;
  }

  /**
   *
   * @return
   */
  public SymbolRecognizer getSymbolRecognizer() {
    return symbolRecognizer;
  }


}