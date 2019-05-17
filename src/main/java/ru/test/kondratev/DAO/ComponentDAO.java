package ru.test.kondratev.DAO;

import ru.test.kondratev.model.Component;

import java.util.List;

public interface ComponentDAO {
    void addComponent(Component component);
    void updateComponent(Component component);
    void removeComponent(int id);
    Component getComponentByID(int id);
    List<Component> getComponentsByName(String name);
    List<Component> componentList();
    List<Component> componentFilteredList (boolean required);
}
