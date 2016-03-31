package qa.addressbook.tests;

//import com.sun.java.util.jar.pack.Package;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.addressbook.model.ContactData;
import qa.addressbook.model.Contacts;
import qa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line !=null) {
      json+=line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List < ContactData >>(){}.getType());
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testsAddNewAddress(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.getContactHelper().all();
    File photo = new File("src/test/resources/ava.png");
    /*ContactData contact = new ContactData()
            .withFirstName("Viacheslav").withLastName("Pykhydko").withNickname("Slava17").withTitle("3D Printers")
            .withCompany("Printers Ltd.").withAddress("Ukraine, Kiev").withHomePhone("044-11-22-3-33")
            .withMobilePhone("+308-63-077-77-77").withWorkPhone("044-11-22-444").withEmail("slava17puh@gmail.com")
            .withGroup("test_group").withPhoto(photo);*/

    app.getContactHelper().create(contact);
    Contacts after = app.getContactHelper().all();

    Assert.assertEquals(after.size(),before.size()+1);

    int max=0;
    for(ContactData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    contact.withId(max);

    assertThat(after, equalTo(before.withAdded(contact)));
  }

  @Test(enabled = false)
  public void currentDir(){
    File current = new File(".");
    System.out.println(current.getAbsolutePath()); // это директория модуля, нужно указывать путь относительно этой директории
    File photo = new File("src/test/resources/ava.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());

  }

}
