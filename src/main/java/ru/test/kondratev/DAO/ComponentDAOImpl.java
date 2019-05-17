package ru.test.kondratev.DAO;

import ru.test.kondratev.model.Component;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class ComponentDAOImpl implements ComponentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addComponent(Component component) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(component);
    }

    @Override
    public void updateComponent(Component component) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(component);
    }

    @Override
    public void removeComponent(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Component component = session.load(Component.class, id);
        if (component != null) {
            session.delete(component);
        }
    }

    @Override
    public Component getComponentByID(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Component.class, id);
    }

    @Override
    public List<Component> getComponentsByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Component> componentList = session.createQuery("from ru.test.kondratev.model.Component").list();
        List<Component> resultList = new ArrayList<>();
        for (Component c : componentList) {
            if (c.getName().contains(name)) {
                resultList.add(c);
            }
        }

        return resultList;
    }

    @Override
    public List<Component> componentList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Component>) session.createQuery("from ru.test.kondratev.model.Component").list();
    }

    @Override
    public List<Component> componentFilteredList(boolean required) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Component> componentList = session.createQuery("from ru.test.kondratev.model.Component").list();

        List<Component> resultList = new ArrayList<>();
        for (Component c : componentList) {
            if (c.isRequired() == required) {
                resultList.add(c);
            }
        }
        return resultList;
    }
}
