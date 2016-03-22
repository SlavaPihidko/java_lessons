package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;
import qa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test_group");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));

    int max=0;
    for(GroupData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    group.withId(max);

    assertThat(after, equalTo(before.withAdded(group)));
  }

}
