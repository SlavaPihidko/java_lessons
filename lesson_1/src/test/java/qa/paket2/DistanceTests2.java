package qa.paket2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Slava on 25.02.2016.
 */
public class DistanceTests2 {

  @Test
  public void testDistance(){
    Point p3 = new Point(17,15);
    Point p4 = new Point(37,45);
    Point p5 = new Point();
    Assert.assertEquals(p5.distance(p3,p3),0.0);

  }
}