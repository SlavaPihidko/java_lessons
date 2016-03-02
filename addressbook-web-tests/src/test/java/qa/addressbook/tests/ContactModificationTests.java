package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContact(new ContactData("Viacheslav",
                                                          "Pykhydko",
                                                          "Slava17",
                                                          "3D Printers",
                                                          "Printers Ltd.",
                                                          "Ukraine, Kiev",
                                                          "044-11-22-3-33",
                                                          "+308-63-077-77-77",
                                                          "044-11-22-444",
                                                          "slava17puh@gmail.com1"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();


  }
}
