package qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.addressbook.model.ContactData;
import qa.addressbook.model.Contacts;

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
    attach(By.name("photo"), newContact.getPhoto());



    if (creation) {
      if(newContact.getGroup()!= null) new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContact.getGroup());
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
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a"));
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

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element: elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      //String phones [] = cells.get(5).getText().split("\n"); -режем строку
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
     /* ContactData contact = new ContactData() //для резки строки
              .withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]); */
      ContactData contact = new ContactData()
              .withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
              .withAllEmails(allEmails).withAllPhones(allPhones);
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

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back(); // навигация назад
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

  }

  public ContactData infoFromEditForm() {
    modifyInAllDataOfContact();
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back(); // навигация назад
    return new ContactData().withFirstName(firstname).withLastName(lastname)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

  }

  public ContactData infoFromEditFormForPhoneAddressEmail(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back(); // навигация назад
    return new ContactData().withFirstName(firstname).withLastName(lastname)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

  }


  public void contactDetails(int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id="+id+"']")).click();
  }

  public ContactData allContactInfo() {
    //Contacts contacts = new Contacts();
    WebElement element = wd.findElement(By.id("content"));
    String allDataOfContact = element.getText();
     return new ContactData().withAllDataOfContacts(allDataOfContact);
  }

  public void modifyInAllDataOfContact() {
    wd.findElement(By.name("modifiy")).click();
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a"));
  }
}
