package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Slava on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
    public void ensurePreConditions() {
    app.goTo().groupPage();
   // if (!app.group().isThereAGroup()) {
    if (app.group().all().size()== 0) {  // теперь используем размер списка, а не наличие локатора
      app.group().create(new GroupData().withName("test_group"));
    }
  }

  @Test
  public void testGroupModification(){
    //int before = app.group().getGroupCount();
    Set<GroupData> before = app.group().all();
    GroupData modifyGroup = before.iterator().next();
    //int index = before.size()-1;
    GroupData group = new GroupData()
            .withId(modifyGroup.getId()).withName("test1").withHeader("test pole 1").withFooter("test pole 3");
    app.group().modify(group);
    // int after = app.group().getGroupCount();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size());

    before.remove(modifyGroup);
    before.add(group);
    Assert.assertEquals(before,after);
  }


}
