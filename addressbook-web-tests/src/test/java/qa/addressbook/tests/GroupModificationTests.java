package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Slava on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
    }
    //int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(), "test1", "test pole 1", "test pole 3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModefication();
    app.getGroupHelper().returnToGroupPage();
   // int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()-1);
    before.add(group);
    Assert.assertEquals( new HashSet<Object>(before), new HashSet<Object>(after) );
  }
}
