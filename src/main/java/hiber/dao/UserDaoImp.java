package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        getSession().save(user);
        getSession().flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return getSession().createQuery("from User").list();
    }

    @Override
    public User getUser(String model, int series) {
        return Optional.ofNullable((User)
                        getSession()
                                .createQuery(
                                        "SELECT a FROM User a INNER JOIN a.car c " +
                                                "WHERE c.model = :model and c.series = :series")
                                .setParameter("model", model)
                                .setParameter("series", series)
                                .uniqueResult())
                .orElse(null);
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }


}
