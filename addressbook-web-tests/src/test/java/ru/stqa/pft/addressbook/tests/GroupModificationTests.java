package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

 @BeforeMethod
 public void ensurePreconditions() {
  app.goTo().groupPage();
  if (app.group().list().size() == 0) {
   app.group().create(new GroupData("test_0", null, null));
  }
 }

 @Test
  public void testGroupModification() {
   List<GroupData> before = app.group().list();
   int indexGroup = before.size() - 1;
   GroupData group = new GroupData(before.get(indexGroup).getId(),"test_0", "test_1", "test_2");
   app.group().modify(indexGroup, group);
   List<GroupData> after = app.group().list();
   Assert.assertEquals(after.size(), before.size());

   before.remove(indexGroup);
   before.add(group);
   Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
   before.sort(byID);
   after.sort(byID);
   Assert.assertEquals(before, after);
 }


}
