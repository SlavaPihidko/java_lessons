package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test(enabled=false)
  public void testsAddNewAddress() {
    app.goTo().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initNewContact();
    ContactData contact = new ContactData("Viacheslav",
                                          "Pykhydko",
                                          "Slava17",
                                          "3D Printers",
                                          "Printers Ltd.",
                                          "Ukraine, Kiev",
                                          "044-11-22-3-33",
                                          "+308-63-077-77-77",
                                          "044-11-22-444",
                                          "slava17puh@gmail.com",
                                          "test_group");
    app.getContactHelper().fillNewContact(contact,true);
    app.getContactHelper().submitNewContact();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size()+1);

    int max=0;
    for(ContactData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }

}
