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

    public void insertToDB(final T initial) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(initial);
        session.getTransaction().commit();
        session.close();
    }

    public void updateInDB(final T initial) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(initial);
        transaction.commit();
        session.close();
    }

    public void deleteInDB(final T initial) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(initial);
        transaction.commit();
        session.close();
    }

    public T getByID(final Class<T> parameter, final int id) {
        Session session = sessionFactory.openSession();
        T answer = session.get(parameter, id);
        session.close();
        return answer;
    }

    public T getUniqueResult(final ResultHandler<T> resultHandler) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = resultHandler.createCriteria(builder);
        T result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    public Collection<T> getCollectionResult(final ResultHandler<T> resultHandler) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = resultHandler.createCriteria(builder);
        Collection<T> resultList = session.createQuery(criteria).getResultList();
        session.close();
        return resultList;
    }
}
