package qa.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import qa.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Slava on 01.03.2016.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); //взять значение системного свойства "browser"

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + " with parameters "+ Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test " + m.getName());
  }

}
