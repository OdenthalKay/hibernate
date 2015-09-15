package haus.maus;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
	public static void main(String[] args) {
		List<Item> items = new ArrayList<Item>();

		// create and persist items
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

		// load items
		Item item = loadItem(1);
		System.out.println(item.toString());
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

	public static Item loadItem(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Item item = null;
		try {
			transaction = session.beginTransaction();
			item = session.get(Item.class, new Long(id));
			if (item == null) {
				throw new HibernateException("No item with id " + id + " exists!");
			}
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return item;
	}

}
