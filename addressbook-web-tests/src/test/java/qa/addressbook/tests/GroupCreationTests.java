package qa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;
import qa.addressbook.model.Groups;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

 /* @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }*/

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    //использование конструкции try для автоматического закрытия файла
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line !=null) {
        xml+=line;
        // String[] split = line.split(";");
        //list.add(new Object[] { new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])} );
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      // return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line !=null) {
      json+=line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List < GroupData >>(){}.getType());
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation (GroupData group) {
      //for(int i = 1;i>=1;i--) {

    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.withId(max);

    assertThat(after, equalTo(before.withAdded(group)));
 // }
  }

 /* @Test
  public void testBadGroupCreation () {
    for(int i = 1;i>=1;i--) {

      app.goTo().groupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName("test_group");
      app.group().create(group);
      assertThat(app.group().getGroupCount(), equalTo(before.size())); // Используеться техника хеширования
      Groups after = app.group().all();
      //assertThat(after.size(), equalTo(before.size()));
      int max = 0;
      for (GroupData g : after) {
        if (g.getId() > max) {
          max = g.getId();
        }
      }
      group.withId(max);

      assertThat(after, equalTo(before.withAdded(group)));
    }

  }*/
}
