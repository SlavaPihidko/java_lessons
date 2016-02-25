package qa.paket0;

/**
 * Created by Slava on 25.02.2016.
 */
public class Rectangle {

   double a;
   double b;

  Rectangle(double a, double b){
    this.a=a;
    this.b=b;
  }

  public double area(){
    double rez=a*b;
    return rez;
  }
}
