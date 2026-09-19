package com.kisan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    private int quantity;
    private int priceAtPurchase;

    public OrderItem(){}

    public OrderItem(Products product, Orders order, int quantity, int priceAtPurchase) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(int priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}
