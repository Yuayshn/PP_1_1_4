package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "create table if not exists users (" +
                " id int not null auto_increment," +
                " name varchar(255)," +
                " lastName varchar(255)," +
                " age int," +
                " primary key (`id`))";
        session.createNativeQuery(sql, User.class).executeUpdate();
        System.out.println("������� users �������!");

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "drop table if exists Users";

        session.createNativeQuery(sql, User.class).executeUpdate();
        System.out.println("������� users �������!");

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = String.format("insert into users (name, lastName, age) values('%s', '%s', '%d')", name, lastName, age);

        session.createNativeQuery(sql, User.class).executeUpdate();
        System.out.println("New User added");

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = String.format("delete from users where id = %d", id);

        session.createNativeQuery(sql, User.class).executeUpdate();
        System.out.println("User deleted to id = " + id);

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        String sql = "select * from users";

        session.createNativeQuery(sql, User.class);

        List<User> list = session.createNativeQuery(sql, User.class).list();

        session.close();

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "truncate table users";

        session.createNativeQuery(sql, User.class).executeUpdate();
        transaction.commit();
        session.close();
    }
}
