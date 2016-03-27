package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Slava on 27.03.2016.
 */
public class ContactEmailTests extends TestBase {

  @Test
  public void emailTests(){
    app.goTo().homePage();
    ContactData contact = app.getContactHelper().all().iterator().next();
    ContactData emailInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(emailInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
