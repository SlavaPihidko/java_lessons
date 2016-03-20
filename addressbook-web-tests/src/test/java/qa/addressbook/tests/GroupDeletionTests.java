package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    app.goTo().groupPage();
    //if (!app.group().isThereAGroup()) {
    if (app.group().all().size()== 0) {
      app.group().create(new GroupData().withName("test_group"));
    }
  }

  @Test
  public void testsGroupDeletion() {
    //int before = app.group().getGroupCount();
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
   // int index = before.size()-1;
    app.group().delete(deletedGroup);
    //int after = app.group().getGroupCount();
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(deletedGroup);
   /* for(int i=0; i<after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i)); // сравниваем два элемента с одинаковыми индексами
    } */
    Assert.assertEquals(before, after); // цикл не нужен, тестовый фреймворк умеет сравнивать без цикла
  }

}
