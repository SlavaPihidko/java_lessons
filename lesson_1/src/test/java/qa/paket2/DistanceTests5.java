package qa.paket2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Slava on 25.02.2016.
 */
public class DistanceTests5 {

  @Test
  public void testDistance(){
    Point p3 = new Point(-2.9,-3.9);
    Point p4 = new Point(-2.0,-3.0);
    Point p5 = new Point();
    Assert.assertEquals(p5.distance(p3,p4),1.0);

  }
}