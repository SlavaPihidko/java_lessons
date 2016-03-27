package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

/**
 * Created by Slava on 27.03.2016.
 */
public class ContactInfoTests extends TestBase {

  @Test
  public void contactInfoTests(){
    app.goTo().homePage();
    ContactData contact = app.getContactHelper().all().iterator().next();
    app.getContactHelper().contactDetails(contact.getId());
    ContactData allcontact = app.getContactHelper().allContactInfo();
    ContactData allEditContact = app.getContactHelper().infoFromEditForm(contact);
  }

}
