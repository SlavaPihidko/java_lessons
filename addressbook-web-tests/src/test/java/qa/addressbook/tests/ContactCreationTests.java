package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test
  public void testsAddNewAddress() {
    app.goTo().homePage();
    Set<ContactData> before = app.getContactHelper().all();
    app.getContactHelper().initNewContact();
    ContactData contact = new ContactData()
            .withFirstName("Viacheslav").withLastName("Pykhydko").withNickname("Slava17").withTitle("3D Printers")
            .withCompany("Printers Ltd.").withAddress("Ukraine, Kiev").withHomePhone("044-11-22-3-33")
            .withMobilePhone("+308-63-077-77-77").withWorkPhone("044-11-22-444").withEmail("slava17puh@gmail.com")
            .withGroup("test_group");
    app.getContactHelper().fillNewContact(contact,true);
    app.getContactHelper().submitNewContact();
    app.goTo().homePage();
    Set<ContactData> after = app.getContactHelper().all();

    Assert.assertEquals(after.size(),before.size()+1);

    int max=0;
    for(ContactData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    contact.withId(max);
    before.add(contact);
    Assert.assertEquals(after,before);
  }

}
