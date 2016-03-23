package qa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;
import qa.addressbook.model.Contacts;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.getContactHelper().all().size()== 0) {
      app.getContactHelper().create(new ContactData()
              .withFirstName("Viacheslav").withLastName("Pykhydko")
              .withAddress("Ukraine, Kiev"));
    }
  }

  @Test
  public void testContactDeletion(){

    Contacts before = app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().selectContactById(deletedContact.getId());
    app.getContactHelper().deletionContact();
    app.goTo().closeDialogWindow();
    app.goTo().homePage();
    Contacts after = app.getContactHelper().all();

    Assert.assertEquals(after.size(),before.size()-1);

    assertThat(after, equalTo(before.withOut(deletedContact)));

  }
}
