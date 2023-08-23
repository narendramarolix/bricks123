package com.marolix.Bricks99.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seller_registration")
public class SellerRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_id")
	private Integer sellerId;
	private String firstName;
	private String lastName;
	@Column(name = "email_id", unique = true, nullable = false)
	private String email;
	@Column(unique = true)
	private String contact;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", unique = true)
	private SellerAddress address;
	@Column(unique = true, nullable = false)
	private String adhaar;

	public String getAdhaar() {
		return adhaar;
	}

	public void setAdhaar(String adhaar) {
		this.adhaar = adhaar;
	}

	@Enumerated(EnumType.STRING)
	private SellerStatus status;

	public SellerStatus getStatus() {
		return status;
	}

	public void setStatus(SellerStatus status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SellerAddress getAddress() {
		return address;
	}

	public void setAddress(SellerAddress address) {
		this.address = address;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, adhaar, contact, email, firstName, lastName, password, sellerId, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerRegistration other = (SellerRegistration) obj;
		return Objects.equals(address, other.address) && Objects.equals(adhaar, other.adhaar)
				&& Objects.equals(contact, other.contact) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(sellerId, other.sellerId)
				&& status == other.status;
	}


}
