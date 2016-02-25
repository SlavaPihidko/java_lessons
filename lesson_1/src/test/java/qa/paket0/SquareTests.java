package qa.paket0;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Slava on 25.02.2016.
 */
public class SquareTests {

  @Test
  public void testArea(){
    Square s = new Square(5);
    Assert.assertEquals(s.area(),25.0);
      }
}
