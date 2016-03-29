package qa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
    ContactData allEditContact = app.getContactHelper().infoFromEditForm();

    assertThat(mergeAllData2(allcontact), equalTo(mergeAllData(allEditContact)));
  }

  private String mergeAllData(ContactData contact) {
    String [] getEmail = contact.getEmail().split("@");
    String [] getEmail2 = contact.getEmail2().split("@");
    String [] getEmail3 = contact.getEmail3().split("@");
    return Arrays.asList(contact.getFirstName(), contact.getLastName(),contact.getAddress(),
            "H:"+contact.getHomePhone(), "M:"+contact.getMobilePhone(), "W:"+contact.getWorkPhone(),
            contact.getEmail(),"(www."+getEmail[1]+")",contact.getEmail2(),"(www."+getEmail2[1]+")",
            contact.getEmail3(),"(www."+getEmail3[1]+")")
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoTests:: cleaned)
            .collect(Collectors.joining(""));
  }

  private String mergeAllData2(ContactData contact) {
    return Arrays.asList(contact.getAllDataOfContacts())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoTests:: cleaned)
            .collect(Collectors.joining(""));
  }

  public static String cleaned(String address) {

    return address.replaceAll("\\s+", " ").
            replaceAll("\\s","");

  }

}
