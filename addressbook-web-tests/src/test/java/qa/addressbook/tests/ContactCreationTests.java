package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testsAddNewAddress() {

    app.getContactHelper().initNewContact();
    app.getContactHelper().fillNewContact(new ContactData("Viacheslav",
                                  "Pykhydko",
                                  "Slava17",
                                  "3D Printers",
                                  "Printers Ltd.",
                                  "Ukraine, Kiev",
                                  "044-11-22-3-33",
                                  "+308-63-077-77-77",
                                  "044-11-22-444",
                                  "slava17puh@gmail.com",
                                  "test_group"),true);
    app.getContactHelper().submitNewContact();
  }

}
