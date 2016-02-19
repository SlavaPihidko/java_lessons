package qa.paket2;

/**
 * Created by Slava on 19.02.2016.
 */
public class Point {
  double x;
  double y;

  /* public static void setdistance(Point p1,Point p2){

  }*/

  public static double distance(Point p1,Point p2){
    double rezx=p1.x-p2.x;
    double rezy=p1.y-p2.y;
    double z=Math.sqrt(rezx*rezx+rezy*rezy);
    System.out.println("The distance between 2 points equal = " + z);
    return z;
  }
}
