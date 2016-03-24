package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;
import qa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation () {
      for(int i = 1;i>=1;i--) {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test_group");
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
  }

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
