package qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Slava on 02.03.2016.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd=wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
