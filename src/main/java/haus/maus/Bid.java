package haus.maus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="BID")
public class Bid {
	@Id
	private long id;
	@ManyToOne
	@JoinColumn(name="ITEM_ID", nullable=false)
	private Item item;

	public Bid() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
