package qa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;
import qa.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Slava on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
    public void ensurePreConditions() {
    app.goTo().groupPage();
    if (app.group().all().size()== 0) {  // теперь используем размер списка, а не наличие локатора
      app.group().create(new GroupData().withName("test_group"));
    }
  }

  @Test
  public void testGroupModification(){

    Groups before = app.group().all();
    GroupData modifyGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifyGroup.getId()).withName("test1").withHeader("test pole 1").withFooter("test pole 3");
    app.group().modify(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size()));
    Groups after = app.group().all();
    assertEquals(after.size(),before.size());

    assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
  }


}
