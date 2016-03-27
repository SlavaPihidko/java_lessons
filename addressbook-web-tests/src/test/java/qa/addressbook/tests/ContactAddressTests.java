package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    assertThat(contact.getAddress(), equalTo(mergeAddress(addressInfoFromEditForm)));
  }

  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream()
            .map(ContactAddressTests:: cleaned)
            .collect(Collectors.joining(""));
  }

  public static String cleaned(String address) {

    String a1= address.replaceAll("\\s+", " ");
    return a1.replaceAll("\\s","\n");
  }

}
