/*package com.endava.com.endava.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 * Created by lfilote on 5/26/2016.
 *//*
@Service
@Transactional
public class GenericDao {

    @PersistenceContext
    private EntityManager em;

    public void persist(Object entity) {
        em.persist(entity);
    }

    public void refresh(Object entity) {
        em.merge(entity);
        em.refresh(entity);
    }

    public void deleteAll(List<? extends Object> entities) {
        for (Object entity : entities) {
            em.remove(em.merge(entity));
        }
    }
}
*/