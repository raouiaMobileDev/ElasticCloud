package org.cloud.federation.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="CPU")
	private int cpu;

	private int imageID;

	private String login;

	private String network;

	private String password;

	@Temporal(TemporalType.DATE)
	private Date periode;

	private int price;

	private int storage;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpu() {
		return this.cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getImageID() {
		return this.imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNetwork() {
		return this.network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPeriode() {
		return this.periode;
	}

	public void setPeriode(Date periode) {
		this.periode = periode;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStorage() {
		return this.storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

}