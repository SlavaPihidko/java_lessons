package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.goTo().homePage();
    Set<ContactData> before = app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().selectContactById(deletedContact.getId());
    app.getContactHelper().deletionContact();
    app.goTo().closeDialogWindow();
    app.goTo().homePage();
    Set<ContactData> after = app.getContactHelper().all();

    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(deletedContact);
    Assert.assertEquals(after, before);

  }
}
