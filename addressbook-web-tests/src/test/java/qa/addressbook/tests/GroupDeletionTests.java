package qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().creationGroups(new GroupData("test_group", null, null));
    }
  }

  @Test
  public void testsGroupDeletion() {
    //int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1); //удаляем последнюю группу
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
   /* for(int i=0; i<after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i)); // сравниваем два элемента с одинаковыми индексами
    } */
    Assert.assertEquals(before, after); // цикл не нужен, тестовый фреймворк умеет сравнивать без цикла
  }

}
