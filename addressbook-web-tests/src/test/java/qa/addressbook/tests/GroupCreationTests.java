package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test_group", null, null);
    app.getGroupHelper().creationGroups(group);
    //int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()+1);

    int max=0;
    for(GroupData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals( new HashSet<Object>(before), new HashSet<Object>(after) );
  }

}
