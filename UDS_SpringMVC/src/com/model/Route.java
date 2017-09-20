package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Route")
public class Route {
	
	@Id
	@GeneratedValue
    private int id;
	
	private int deletestatus;
	
	private String routeNumber;
	
	@ManyToOne
	@JoinColumn(name="driverid")
	private User driver;
	
	@ManyToOne
	@JoinColumn(name="busid")
	private Bus bus;//
	
	private String runningTImeStart;
	
	private String runningTImeEnd;

	private String gap;
	
	private String routeChange;
	
	@Column(name="discription", columnDefinition="TEXT")
	private String discription;

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

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public String getRunningTImeStart() {
		return runningTImeStart;
	}

	public void setRunningTImeStart(String runningTImeStart) {
		this.runningTImeStart = runningTImeStart;
	}

	public String getRunningTImeEnd() {
		return runningTImeEnd;
	}

	public void setRunningTImeEnd(String runningTImeEnd) {
		this.runningTImeEnd = runningTImeEnd;
	}

	public String getGap() {
		return gap;
	}

	public void setGap(String gap) {
		this.gap = gap;
	}

	public String getRouteChange() {
		return routeChange;
	}

	public void setRouteChange(String routeChange) {
		this.routeChange = routeChange;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	
	


	
}
