package qa.addressbook.tests;

import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test_group",
                                "test pole 1",
                                "test pole 2"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
