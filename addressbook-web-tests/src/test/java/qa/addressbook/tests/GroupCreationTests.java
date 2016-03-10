package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before+1);
  }

}
