package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testsGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
     if (! app.getGroupHelper().isThereAGroup()){
       app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
     }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
