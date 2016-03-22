package qa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Slava on 22.03.2016.
 */
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups);
  }

  /* Я: Каким образом обьект groups вмещает в себя список групп ?
  [21:09:30] Alexei Barantsev: ну, это фокус среды разработки
[21:10:25] Alexei Barantsev: она видит, что Groups реализует интерфейс Set, и отображает его таким образом -- показывает, что он как бы сам содержит эти вложенные объекты типа GroupData
[21:10:54] Alexei Barantsev: а реально они спрятаны внутрь delegate (к которому все обращения делегируются, поэтому он так и назван)
[21:12:32] Alexei Barantsev: обман зрения, в общем*/

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group){
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups withOut(GroupData group){
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }


}
