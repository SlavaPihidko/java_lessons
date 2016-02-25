package qa.paket2;

/**
 * Created by Slava on 19.02.2016.
 */
public class Point {
  private double x;
  private double y;

  Point(){}

  Point(double x, double y){
    this.x=x;
    this.y=y;
  }

  public double distance(Point p1,Point p2){
    double rezx = p1.x-p2.x;
    double rezy = p1.y-p2.y;
    int z = (int) Math.sqrt(rezx*rezx+rezy*rezy);
    System.out.println("The distance between 2 points equals = " + z);
    return z;
  }
}
