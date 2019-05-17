package ru.test.kondratev.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.test.kondratev.DAO.ComponentDAO;
import ru.test.kondratev.model.Component;


@Service
@Transactional
public class ComponentService {

    @Autowired
    private ComponentDAO componentDAOImpl;


    public void setComponentDAOImpl(ComponentDAO componentDAOImpl) {
        this.componentDAOImpl = componentDAOImpl;
    }

    @Transactional
    public List<Component> componentList(){
        return componentDAOImpl.componentList();
    }

    @Transactional
    public void addComponent(Component component) {
        componentDAOImpl.addComponent(component);
    }

    @Transactional
    public void updateComponent(Component component) {
        componentDAOImpl.updateComponent(component);
    }

    @Transactional
    public void removeComponent(int id) {
        componentDAOImpl.removeComponent(id);
    }

    @Transactional
    public Component getComponentByID(int id) {
        return componentDAOImpl.getComponentByID(id);
    }

    @Transactional
    public List<Component> componentFilteredList(boolean required){
        return componentDAOImpl.componentFilteredList(required);
    }

    @Transactional
    public List<Component> getComponentsByName(String name) {
        return componentDAOImpl.getComponentsByName(name);
    }


}
