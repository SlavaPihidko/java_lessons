package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Slava on 02.03.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
    public void ensurePreConditions() {
    app.goTo().groupPage();
   // if (!app.group().isThereAGroup()) {
    if (app.group().list().size()== 0) {  // теперь используем размер списка, а не наличие локатора
      app.group().create(new GroupData("test_group", null, null));
    }
  }

  @Test
  public void testGroupModification(){
    //int before = app.group().getGroupCount();
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getId(), "test1", "test pole 1", "test pole 3");
    app.group().modify(index, group);
    // int after = app.group().getGroupCount();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(group);
    Assert.assertEquals( new HashSet<Object>(before), new HashSet<Object>(after) );
  }


}
