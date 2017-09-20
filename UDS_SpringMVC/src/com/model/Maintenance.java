package com.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Maintenance")
public class Maintenance {
	
	@Id
	@GeneratedValue
    private int id;
	
	private int deletestatus;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	private String maintenanceType;
	
	@Column(name="discription", columnDefinition="TEXT")
	private String discription;
	
	private String applttime;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="AssignedEmpID")
	private User assignedEmp;
	
	
	private String scheduledTIme;


	@Column(name="report", columnDefinition="TEXT")
	private String report;
	
	
	
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getMaintenanceType() {
		return maintenanceType;
	}


	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}


	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public String getApplttime() {
		return applttime;
	}


	public void setApplttime(String applttime) {
		this.applttime = applttime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	


	public String getScheduledTIme() {
		return scheduledTIme;
	}


	public void setScheduledTIme(String scheduledTIme) {
		this.scheduledTIme = scheduledTIme;
	}


	public User getAssignedEmp() {
		return assignedEmp;
	}


	public void setAssignedEmp(User assignedEmp) {
		this.assignedEmp = assignedEmp;
	}


	public String getReport() {
		return report;
	}


	public void setReport(String report) {
		this.report = report;
	}

	
	
	
	
}
