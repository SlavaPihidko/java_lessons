package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    //int before = app.group().getGroupCount();
    GroupData group = new GroupData().withName("test_group");
    app.group().create(group);
    //int after = app.group().getGroupCount();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size()+1);

    int max=0;
    for(GroupData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    group.withId(max);
    before.add(group);
    Assert.assertEquals(before, after );
  }

}
