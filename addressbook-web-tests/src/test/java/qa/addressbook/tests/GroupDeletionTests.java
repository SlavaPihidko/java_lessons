package qa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;
import qa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    app.goTo().groupPage();
    if (app.group().all().size()== 0) {
      app.group().create(new GroupData().withName("test_group"));
    }
  }

  @Test
  public void testsGroupDeletion() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();

    assertEquals(after.size(),before.size()-1);

    assertThat(after, equalTo(before.withOut(deletedGroup)));
  }

}
