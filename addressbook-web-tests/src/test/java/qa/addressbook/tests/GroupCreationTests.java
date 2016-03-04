package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
  }

}
