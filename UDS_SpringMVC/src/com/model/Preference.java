package com.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Preference")
public class Preference {
	
	@Id
	@GeneratedValue
    private int id;
	
	private int deletestatus;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	private String	roomType;
	
	private String roomarea;
	private String smoke;
	private String party;
	private String pet;
	private double priceLimit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDeletestatus() {
		return deletestatus;
	}
	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRoomarea() {
		return roomarea;
	}
	public void setRoomarea(String roomarea) {
		this.roomarea = roomarea;
	}
	public String getSmoke() {
		return smoke;
	}
	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getPet() {
		return pet;
	}
	public void setPet(String pet) {
		this.pet = pet;
	}
	public double getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(double priceLimit) {
		this.priceLimit = priceLimit;
	}


	

	
}
