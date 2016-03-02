package qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import qa.addressbook.model.ContactData;

/**
 * Created by Slava on 02.03.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewContact(ContactData newContact) {
    type(By.name("firstname"),newContact.getFirstName());
    type(By.name("lastname"),newContact.getLastName());
    type(By.name("nickname"),newContact.getNickname());
    type(By.name("title"),newContact.getTitle());
    type(By.name("company"),newContact.getCompany());
    type(By.name("address"),newContact.getAddress());
    type(By.name("home"),newContact.getHomePhone());
    type(By.name("mobile"),newContact.getMobilePhone());
    type(By.name("work"),newContact.getWorkPhone());
    type(By.name("email"),newContact.getEmail());
  }

  public void initNewContact() {
    click(By.linkText("add new"));
  }
}
