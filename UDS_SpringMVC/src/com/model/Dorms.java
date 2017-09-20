package com.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Dorms")
public class Dorms {
	
	@Id
	@GeneratedValue
    private int id;
	
	private int deletestatus;
	
	private String	roomType; 
	private String	dormNum;
	private String	roomNum;
	private String	price;//
	private String	area;//
	private int number_of_room;
	private int number_of_use;
	private String image;
	
	private int advertisement;
	
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
	public String getDormNum() {
		return dormNum;
	}
	public void setDormNum(String dormNum) {
		this.dormNum = dormNum;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getNumber_of_room() {
		return number_of_room;
	}
	public void setNumber_of_room(int number_of_room) {
		this.number_of_room = number_of_room;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNumber_of_use() {
		return number_of_use;
	}
	public void setNumber_of_use(int number_of_use) {
		this.number_of_use = number_of_use;
	}
	public int getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(int advertisement) {
		this.advertisement = advertisement;
	}


	


	
}
