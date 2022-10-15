package org.cloud.federation.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the allocated_resource database table.
 * 
 */
@Entity
@Table(name="allocated_resource")
@NamedQuery(name="AllocatedResource.findAll", query="SELECT a FROM AllocatedResource a")
public class AllocatedResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String context;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String login;

	private String password;

	public AllocatedResource() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}