package qa.paket2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Slava on 25.02.2016.
 */
public class DistanceTests3 {

  @Test
  public void testDistance(){
    Point p3 = new Point(17.00,15.00);
    Point p4 = new Point(37.00,45.00);
    Point p5 = new Point();
    Assert.assertEquals(p5.distance(p3,p4),36.0);

  }
}