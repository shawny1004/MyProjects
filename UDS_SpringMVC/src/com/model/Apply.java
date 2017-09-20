package com.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//Apply dorm
@Entity
@Table(name="t_Apply")
public class Apply {
	
	@Id
	@GeneratedValue
    private int id;
	
	private int deletestatus;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="dormsid")
	private Dorms dorms;
	
	private String applytime;
	
	@Column(name="applyExplain", columnDefinition="TEXT")
	private String applyExplain;
	
	private String status;//application_status  Not_reviewed ，Passed  ，Failed ,Cancel_application
	
	@Column(name="applyReply", columnDefinition="TEXT")
	private String applyReply;
 
	
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

	public Dorms getDorms() {
		return dorms;
	}

	public void setDorms(Dorms dorms) {
		this.dorms = dorms;
	}

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyExplain() {
		return applyExplain;
	}

	public void setApplyExplain(String applyExplain) {
		this.applyExplain = applyExplain;
	}

	public String getApplyReply() {
		return applyReply;
	}

	public void setApplyReply(String applyReply) {
		this.applyReply = applyReply;
	}

	
	
	


	
}
