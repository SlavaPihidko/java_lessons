package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Slava on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
    public void ensurePreConditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
    }
  }

  @Test
  public void testGroupModification(){
    //int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getId(), "test1", "test pole 1", "test pole 3");
    app.getGroupHelper().modifyGroup(index, group);
    // int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(group);
    Assert.assertEquals( new HashSet<Object>(before), new HashSet<Object>(after) );
  }


}
