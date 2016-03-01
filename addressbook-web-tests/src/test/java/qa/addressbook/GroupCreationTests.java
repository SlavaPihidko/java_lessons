package qa.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test_group", "test pole 1", "test pole 2"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
