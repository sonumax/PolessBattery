package hibernate.dbService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

public class DBService<T> {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private final Session session;

    public DBService() {
        session = sessionFactory.openSession();
    }

    public Collection<T> getCollectionResult(ResultHandler<T> resultHandler) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = resultHandler.handle(builder);
        Collection<T> resultList = session.createQuery(criteria).getResultList();
        session.close();
        return resultList;
    }

    public T getUniqueResult(ResultHandler<T> resultHandler) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = resultHandler.handle(builder);
        T result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    public void insertToDB(T initial) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(initial);
        session.getTransaction().commit();
        session.close();
    }

    public void updateInDB(T initial) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(initial);
        transaction.commit();
        session.close();
    }

    public void deleteInDB(T initial) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(initial);
        transaction.commit();
        session.close();
    }

    public T getByID(final Class<T> parameter, final int id) {
        Session session = sessionFactory.openSession();
        T answer = session.load(parameter, id);
        session.close();
        return answer;
    }
}
