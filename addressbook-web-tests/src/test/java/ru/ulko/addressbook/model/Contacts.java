package ru.ulko.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yulia on 05.09.2019.
 */
public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts contact) {
        this.delegate = contact;
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts withAddedAll(Set<ContactData> listContacts) {
        Contacts contacts = new Contacts(this);
        contacts.addAll(listContacts);
        return contacts;
    }

    public Contacts withModified(ContactData oldContact, ContactData newContact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(oldContact);
        contacts.add(newContact);
        return contacts;
    }

    public Contacts withRemoved(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
