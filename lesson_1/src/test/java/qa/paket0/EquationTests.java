package qa.paket0;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Slava on 03.03.2016.
 */
public class EquationTests {

  @Test
  public void testEquation0(){
    Equation eq = new Equation(1,1,1);
   //System.out.println("Дискриминант равен =  "+eq.getN());
    Assert.assertEquals(eq.getN(),0);
  }

  @Test
  public void testEquation1(){
    Equation eq = new Equation(1,2,1);
    //System.out.println("Дискриминант равен =  "+eq.getN());
    Assert.assertEquals(eq.getN(),1);
  }

  @Test
  public void testEquation2(){
    Equation eq = new Equation(1,5,6);
    //System.out.println("Дискриминант равен =  "+eq.getN());
    Assert.assertEquals(eq.getN(),2);
  }
}
