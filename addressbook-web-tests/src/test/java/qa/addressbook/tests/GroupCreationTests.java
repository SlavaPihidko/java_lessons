package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    //int before = app.group().getGroupCount();
    GroupData group = new GroupData().withName("test_group");
    app.group().create(group);
    //int after = app.group().getGroupCount();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(),before.size()+1);

    int max=0;
    for(GroupData g: after){
      if(g.getId() > max) {
        max = g.getId();
      }
    }
    group.withId(max);
    before.add(group);
    Assert.assertEquals( new HashSet<Object>(before), new HashSet<Object>(after) );
  }

}
