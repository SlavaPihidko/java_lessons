package qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.addressbook.model.ContactData;

/**
 * Created by Slava on 02.03.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewContact(ContactData newContact, boolean creation) {
    type(By.name("firstname"), newContact.getFirstName());
    type(By.name("lastname"), newContact.getLastName());
    type(By.name("nickname"), newContact.getNickname());
    type(By.name("title"), newContact.getTitle());
    type(By.name("company"), newContact.getCompany());
    type(By.name("address"), newContact.getAddress());
    type(By.name("home"), newContact.getHomePhone());
    type(By.name("mobile"), newContact.getMobilePhone());
    type(By.name("work"), newContact.getWorkPhone());
    type(By.name("email"), newContact.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContact.getGroup());
    }
      else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      }

  }


  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.id("9"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void deletionContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
}
