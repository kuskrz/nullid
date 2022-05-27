package kus.krzysztof.nullid.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kus.krzysztof.nullid.entity.Knumbers;

@Service
public class KnumbersService {
	@PersistenceContext
    private EntityManager em;
	private CriteriaBuilder cb;
	private CriteriaQuery<Knumbers> cq;
	
	public Knumbers selectNumber(Integer id) {
		//cb = em.getCriteriaBuilder();
		cb = em.getCriteriaBuilder();

		cq = cb.createQuery(Knumbers.class);
		Root<Knumbers> root = cq.from(Knumbers.class);
		cq.select(root).where(cb.equal(root.get("id"), id));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Transactional
	public Knumbers insertNumber(Knumbers kn, Integer n) {	
		kn.setTestNumber(n);
		kn.setNumber(n);
		em.persist(kn);

		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return kn;
	}
}
