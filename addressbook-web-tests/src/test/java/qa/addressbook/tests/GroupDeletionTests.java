package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testsGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();

     if (! app.getGroupHelper().isThereAGroup()){
       app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
     }
    app.getGroupHelper().selectGroup(before-1); //удаляем последнюю группу
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before-1);
  }

}
