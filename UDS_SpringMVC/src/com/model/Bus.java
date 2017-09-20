package com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Bus")
public class Bus {
	
	@Id
	@GeneratedValue
    private int id;
	
	private int deletestatus;
	
	private String plateNumber;


	private String maintenanceTIme;
	
	@ManyToOne
	@JoinColumn(name="routeid")
	private Route route;

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

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getMaintenanceTIme() {
		return maintenanceTIme;
	}

	public void setMaintenanceTIme(String maintenanceTIme) {
		this.maintenanceTIme = maintenanceTIme;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}


	
	
	


	
}
