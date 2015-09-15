package haus.maus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "ITEM")
public class Item {
	@Id
	@GeneratedValue
	private long id;
	@OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
	private List<Bid> bids;

	public Item() {
		this.bids = new ArrayList<Bid>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void addBid(Bid bid) {
		this.bids.add(bid);
	}

}
