package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

/**
 * Created by Slava on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test_group",
                                                      "test pole 1",
                                                      "test pole 2"));
    app.getGroupHelper().submitGroupModefication();
    app.getGroupHelper().returnToGroupPage();
  }
}
