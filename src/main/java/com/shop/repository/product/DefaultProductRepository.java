package com.shop.repository.product;

import com.shop.entity.product.StandardProduct;
import com.shop.repository.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by kb on 2017-09-07.
 */
@Repository
public class DefaultProductRepository<T extends StandardProduct>  implements ProductRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T getProduct(long barcode, Class <T> productClass)  {
        T product =  entityManager.find(productClass, barcode);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @Override
    public T getProductByBarcode(Long barCode, Class <T> productClass)  {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(productClass);
        Root<T> rootEntry = cq.from(productClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        ParameterExpression<Long> p = cb.parameter(Long.class);
        cq.where(cb.equal(rootEntry.get("barcode"), p));
        TypedQuery<T> query = entityManager.createQuery(all);
        query.setParameter(p, barCode);
        List<T> result = query.getResultList();

        if (result.isEmpty())
        {
            throw new ProductNotFoundException();
        }

        return result.get(0);
    }

    @Override
    public void update(T product) {
        entityManager.merge(product);
    }

    @Override
    public void persist(T product) {
        entityManager.persist(product);
    }

    @Override
    public List<T> findAll(Class<T> productClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(productClass);
        Root<T> rootEntry = cq.from(productClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

}
