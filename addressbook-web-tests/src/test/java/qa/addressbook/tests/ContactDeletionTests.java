package qa.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deletionContact();
    app.getNavigationHelper().closeDialogWindow();
    app.getNavigationHelper().gotoHomePage();
  }
}
