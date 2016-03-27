package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Slava on 27.03.2016.
 */
public class ContactAddressTests extends TestBase {

  @Test
  public void addressTest(){
    app.goTo().homePage();
    ContactData contact = app.getContactHelper().all().iterator().next();
    ContactData addressInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(addressInfoFromEditForm.getAddress()));
  }
}
