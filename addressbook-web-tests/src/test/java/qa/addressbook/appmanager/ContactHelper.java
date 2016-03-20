package qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      if(app.group().all().size()!= 0) new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContact.getGroup());
    }
      else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      }


  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillNewContact(contact,false);
    submitContactModification();
    returnToHomePage();
  }


  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void initContactModification(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

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

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      WebElement cell1 = cells.get( 1 );
      WebElement cell2 = cells.get( 2 );
      WebElement cell3 = cells.get( 3 );
      String lastName = cell1.getText();
      String firstName = cell2.getText();
      String address = cell3.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData( id,
                                             firstName,
                                             lastName,
                                             null,
                                             null,
                                             null,
                                             address,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null);
      contacts.add(contact);
  }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData()
              .withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address);

      contacts.add(contact);
    }
    return contacts;
  }

  public void  create(ContactData contact) {
    initNewContact();
    fillNewContact(contact,true);
    submitNewContact();
    returnToHomePage();
  }

}
