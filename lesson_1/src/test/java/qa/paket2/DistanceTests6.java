package qa.paket2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Slava on 25.02.2016.
 */
public class DistanceTests6 {

  @Test
  public void testDistance(){
    Point p3 = new Point('a','b');
    Point p4 = new Point('c','d');
    Point p5 = new Point();
    Assert.assertEquals(p5.distance(p3,p4),2.0);

  }
}