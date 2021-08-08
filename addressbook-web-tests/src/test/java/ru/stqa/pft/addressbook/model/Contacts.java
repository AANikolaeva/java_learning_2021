package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

public class Contacts extends ForwardingSet<GroupData> {
  private Set<ContactData> delegate;

  @Override
  protected Set<GroupData> delegate() {
    return null;
  }
}
