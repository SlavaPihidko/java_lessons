package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Slava on 03.03.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test(enabled=false)
  public void testContactModification(){
    app.getNavigationHelper() .gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
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
                                                          "slava17puh@gmail.com1",
                                                          null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size());

    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));


  }
}
