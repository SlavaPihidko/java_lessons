package qa.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import qa.addressbook.appmanager.ApplicationManager;

/**
 * Created by Slava on 01.03.2016.
 */
public class TestBase {

  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); //взять значение системного свойства "browser"

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
