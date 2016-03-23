package qa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;
import qa.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.getContactHelper().all().size()== 0) {
      app.getContactHelper().create(new ContactData()
              .withFirstName("Viacheslav").withLastName("Pykhydko")
              .withAddress("Ukraine, Kiev").withGroup("test_group"));
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.getContactHelper().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("Slava").withLastName("Pykhydko").withNickname("Slava17").withTitle("3D Printers")
            .withCompany("Printers Ltd.").withAddress("Ukraine, Lvov").withHomePhone("044-11-22-3-33")
            .withMobilePhone("+308-63-077-77-77").withWorkPhone("044-11-22-444").withEmail("slava17puh@gmail.com")
            .withGroup("test_group");
    app.getContactHelper().modify(contact);
    Contacts after = app.getContactHelper().all();

    assertEquals(after.size(),before.size());

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }

}
