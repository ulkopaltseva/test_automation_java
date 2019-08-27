package ru.ulko.addressbook.model;

// форма заполнения группы при создании и модификации
public class GroupData {
    private String name;
    private String header;
    private String footer;
    private int id; // уникальный идентефикатор, находится в теге input и берется из значения value

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    // конструктор с инициализацией полей
    public GroupData(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;

        this.footer = footer;
    }

    // конструктор с инициализацией полей без указания id
    public GroupData(String name, String header, String footer) {
        this.id = 0;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }


    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

}
