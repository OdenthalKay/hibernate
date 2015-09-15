package haus.maus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Item item = new Item();
			item.setId(99);
			Bid bidA = new Bid();
			bidA.setId(1);
			bidA.setItem(item);
			Bid bidB = new Bid();
			Bid bidC = new Bid();
			bidC.setId(8);
			bidC.setItem(item);
			bidB.setId(2);
			bidB.setItem(item);
			item.getBids().add(bidA);
			item.getBids().add(bidB);

			session.persist(item);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
