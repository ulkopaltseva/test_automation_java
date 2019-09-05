package ru.ulko.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yulia on 05.09.2019.
 */
public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate());
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }


    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups withAddedAll(Set<GroupData> listGroups) {
        Groups groups = new Groups(this);
        groups.addAll(listGroups);
        return groups;
    }

    public Groups withRemoved(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

    public Groups withModified(GroupData oldGroup, GroupData newGroup) {
        Groups groups = new Groups(this);
        groups.remove(oldGroup);
        groups.add(newGroup);
        return groups;
    }
}
