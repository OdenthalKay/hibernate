package haus.maus;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
	public static void main(String[] args) {
		List<Item> items = new ArrayList<Item>();
		Item item1 = new Item();
		Item item2 = new Item();
		Bid bidA = new Bid(item1, "Succesful bid Playstation 4.");
		Bid bidB = new Bid(item1, "Succesful bid apple MacBook.");
		Bid bidC = new Bid(item1, "Succesful Android Smartphone.");
		Bid bidD = new Bid(item2, "some bid");
		Bid bidE = new Bid(item2, "another bid");
		item1.getBids().add(bidA);
		item1.addBid(bidA);
		item1.addBid(bidB);
		item1.addBid(bidC);
		item2.addBid(bidD);
		item2.addBid(bidE);
		items.add(item1);
		items.add(item2);
		saveAllItems(items);
	}

	public static void saveAllItems(List<Item> items) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for (Item item : items) {
				// use save instead of persist because ID is generated
				session.persist(item);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void loadAllItems() {

	}

}
