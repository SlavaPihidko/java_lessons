package qa.paket2;

/**
 * Created by Slava on 19.02.2016.
 */
public class MainPoint {
  public static void main(String[] args) {
    Point p1 = new Point();
    {
      p1.x = 17;
      p1.y = 15;
    }
    Point p2 = new Point();
    {
      p2.x = 37;
      p2.y = 45;
    }
    Point p = new Point();
    p.distance(p1,p2);
  }
}


