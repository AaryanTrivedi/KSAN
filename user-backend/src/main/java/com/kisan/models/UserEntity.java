package com.kisan.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column(name = "first_name", length = 20) 
	private String firstName;
	
	@Column(name = "last_name", length = 20) 
	private String lastName;
	
	@Column(length = 25, unique = true)
	private String email; 
	
	@Column(length=10,unique = true,nullable=false)
	private String mobile;	
	
	@Column(length = 500, nullable = false)
	private String password; 
	
	@Column(length=10)
	private String gender; 
	
	@Lob
	private byte[] profileImage; //large object to store image inside the database
	
	private String imageName;
	private String imageType;
	
	@Column
	private boolean status;
	
	@Column(name="adr_line1",length=100)
	private String adrLine1;
	
	@Column(name="adr_line2",length=100)
	private String adrLine2;
	
	@Column(length=20)
	private String city;
	
	@Column(length=20)
	private String state;
	
	@Column(length=20,name="zip_code")
	private String zipCode;
	
	@Enumerated(EnumType.STRING)
	private FarmingType farmingType;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	
	@OneToMany(mappedBy = "user", 
			cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Orders> orders;
	
	@OneToMany(mappedBy = "user", 
			cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Products> products;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart activeCart;
	public UserEntity() {}

	public UserEntity(String firstName, String lastName, String email, String mobile, String password, String gender, byte[] profileImage, String imageName, String imageType, boolean status, String adrLine1, String adrLine2, String city, String state, String zipCode, FarmingType farmingType, UserRole role, List<Orders> orders, List<Products> products, Cart activeCart) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.gender = gender;
		this.profileImage = profileImage;
		this.imageName = imageName;
		this.imageType = imageType;
		this.status = status;
		this.adrLine1 = adrLine1;
		this.adrLine2 = adrLine2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.farmingType = farmingType;
		this.role = role;
		this.orders = orders;
		this.products = products;
		this.activeCart = activeCart;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAdrLine1() {
		return adrLine1;
	}

	public void setAdrLine1(String adrLine1) {
		this.adrLine1 = adrLine1;
	}

	public String getAdrLine2() {
		return adrLine2;
	}

	public void setAdrLine2(String adrLine2) {
		this.adrLine2 = adrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public FarmingType getFarmingType() {
		return farmingType;
	}

	public void setFarmingType(FarmingType farmingType) {
		this.farmingType = farmingType;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public Cart getActiveCart() {
		return activeCart;
	}

	public void setActiveCart(Cart activeCart) {
		this.activeCart = activeCart;
	}
}