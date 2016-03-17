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

  @Test(enabled=false)
  public void testContactDeletion(){
    app.goTo().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deletionContact();
    app.goTo().closeDialogWindow();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }
}
