package qa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;
import qa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

    @Test(dataProvider = "validGroups")
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
