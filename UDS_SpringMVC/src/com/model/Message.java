package com.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//消息
@Entity
@Table(name="t_Message")
public class Message {
	
	@Id
	@GeneratedValue
    private int id;
	private int deletestatus1;
	
	private int deletestatus2;
	
	@ManyToOne
	@JoinColumn(name="fromuserid")
	private User fromuser;
	
	@ManyToOne
	@JoinColumn(name="touserid")
	private User touser;
	

	private String messageTitle;
	

	@Column(name="messageContent", columnDefinition="TEXT")
	private String messageContent;
	
	private String createtime;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus1() {
		return deletestatus1;
	}

	public void setDeletestatus1(int deletestatus1) {
		this.deletestatus1 = deletestatus1;
	}

	public int getDeletestatus2() {
		return deletestatus2;
	}

	public void setDeletestatus2(int deletestatus2) {
		this.deletestatus2 = deletestatus2;
	}

	public User getFromuser() {
		return fromuser;
	}

	public void setFromuser(User fromuser) {
		this.fromuser = fromuser;
	}

	public User getTouser() {
		return touser;
	}

	public void setTouser(User touser) {
		this.touser = touser;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
