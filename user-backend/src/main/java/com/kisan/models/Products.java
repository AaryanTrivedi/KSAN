package com.kisan.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Products extends BaseEntity
{
	 @Column(length = 100, nullable = false)
	  private String productName;
	 
	 @Enumerated(EnumType.STRING)
	  private ProductType productType;
	 
	  private int price; // to be added when user sells
	  
	  private int totalStock; //the amount user has:
	  
	  private int stockToSell; //the amount user wants to sell, cannot be less that total stock:
	  
	  private boolean status; // for hard and soft delete
	  
	  private boolean markedForSale; //when the user lists the item for selling
	  
	  @Lob
	  private byte[] productImage; //large object to store image inside the database
		
	  private String imageName;
	  private String imageType;
	  
	  @Enumerated(EnumType.STRING)
	  private MetricType metric; //kg,l,unit
	  
	  @Enumerated(EnumType.STRING)
	  private FarmingType farmingType;
	  
	  private double landArea; //stored in acres
	  @ManyToOne 
	  @JoinColumn(name="user_id",nullable=false)
	  private UserEntity user;
	public Products(){}
	public Products(String productName, ProductType productType, int price, int totalStock, int stockToSell, boolean status, boolean markedForSale, byte[] productImage, String imageName, String imageType, MetricType metric, FarmingType farmingType, double landArea, UserEntity user) {
		this.productName = productName;
		this.productType = productType;
		this.price = price;
		this.totalStock = totalStock;
		this.stockToSell = stockToSell;
		this.status = status;
		this.markedForSale = markedForSale;
		this.productImage = productImage;
		this.imageName = imageName;
		this.imageType = imageType;
		this.metric = metric;
		this.farmingType = farmingType;
		this.landArea = landArea;
		this.user = user;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getStockToSell() {
		return stockToSell;
	}

	public void setStockToSell(int stockToSell) {
		this.stockToSell = stockToSell;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isMarkedForSale() {
		return markedForSale;
	}

	public void setMarkedForSale(boolean markedForSale) {
		this.markedForSale = markedForSale;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
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

	public MetricType getMetric() {
		return metric;
	}

	public void setMetric(MetricType metric) {
		this.metric = metric;
	}

	public FarmingType getFarmingType() {
		return farmingType;
	}

	public void setFarmingType(FarmingType farmingType) {
		this.farmingType = farmingType;
	}

	public double getLandArea() {
		return landArea;
	}

	public void setLandArea(double landArea) {
		this.landArea = landArea;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public boolean validStock() {
		  //to verify if the stock to be sold is the proper amount
		  
		  
		  //validate all proper information
		  boolean correct = true;
		  if(price<0) {
			  correct = false;
		  }
//		  if(totalStock)
		  
		  
		  return correct;
		  
	  }
}