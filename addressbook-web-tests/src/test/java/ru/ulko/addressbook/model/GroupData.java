package ru.ulko.addressbook.model;

// форма заполнения группы при создании и модификации
public class GroupData {
    private final String name;
    private final String header;
    private final String footer;

    // конструктор с инициализацией полей
    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
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

    // сгенерирован для сравнения объектов типа GroupData
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return name != null ? name.equals(groupData.name) : groupData.name == null;

    }

    // сгенерирован для преобразования во множества (?)
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    // сгенерирован, чтобы считывать тест полей в удобном виде
    @Override
    public String toString() {
        return "GroupData{" +

                "name='" + name + '\'' +
                '}';
    }
}
