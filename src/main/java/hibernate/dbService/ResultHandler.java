package hibernate.dbService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@FunctionalInterface
public interface ResultHandler<T> {
    CriteriaQuery<T> handle(CriteriaBuilder builder);
}
