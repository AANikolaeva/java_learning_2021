package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

 @BeforeMethod
 public void ensurePreconditions() {
  app.goTo().groupPage();
  if (app.group().all().size() == 0) {
   app.group().create(new GroupData().withName("test1"));
  }
 }

 @Test(enabled = false)
  public void testGroupModification() {
   Groups before = app.group().all();
   GroupData modifiedGroup = before.iterator().next();
   GroupData group = new GroupData()
           .withId(modifiedGroup.getId()).withName("test_0").withHeader("test_1").withFooter("test_2");
   app.group().modify(group);
   Groups after = app.group().all();
   assertEquals(after.size(), before.size());
   assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

 }


}
