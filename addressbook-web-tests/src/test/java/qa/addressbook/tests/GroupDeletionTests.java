package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    app.goTo().groupPage();
    //if (!app.group().isThereAGroup()) {
    if (app.group().list().size()== 0) {
      app.group().create(new GroupData("test_group", null, null));
    }
  }

  @Test
  public void testsGroupDeletion() {
    //int before = app.group().getGroupCount();
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    app.group().delete(index);
    //int after = app.group().getGroupCount();
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(index);
   /* for(int i=0; i<after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i)); // сравниваем два элемента с одинаковыми индексами
    } */
    Assert.assertEquals(before, after); // цикл не нужен, тестовый фреймворк умеет сравнивать без цикла
  }

}
