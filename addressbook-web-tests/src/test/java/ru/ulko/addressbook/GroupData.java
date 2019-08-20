package ru.ulko.addressbook;

public class GroupData {
    private final String name;
    private final String header;

    public GroupData(String name, String header) {
        this.name = name;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }
}
