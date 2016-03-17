package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deletionContact();
    app.getNavigationHelper().closeDialogWindow();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }
}
