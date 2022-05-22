package kus.krzysztof.nullid.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.sessions.Session;
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
		cb = em.getCriteriaBuilder();
		cq = cb.createQuery(Knumbers.class);
		Root<Knumbers> root = cq.from(Knumbers.class);
		cq.select(root).where(cb.equal(root.get("id"), id));
		//return em.createQuery(cq).getFirstResult();
		return em.createQuery(cq).getSingleResult();
	}
	
	@Transactional
	public Knumbers insertNumber(Knumbers kn, Integer n) {
		//Session session = em.unwrap(Session.class);
		Connection conn = em.unwrap(Connection.class);
		//eclipselink
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//hibernate
		//session.doWork(conn -> conn.setAutoCommit(false));
		//session.doWork(conn -> conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED));
		kn.setTestNumber(n);
		em.persist(kn);
		//session.doWork(conn -> System.out.println(conn.getAutoCommit()));
		//session.doWork(conn -> System.out.println(conn.getTransactionIsolation()));
		System.out.println("ID3:" + kn.getId());
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kn;
	}
}
