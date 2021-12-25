package tn.esprit.spring.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table(name="Orders")
public class Order {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="Order_id")
	  private int order_id;
	  @Column(name="Order_date")
	  private Date order_date;
	  @Column(name="Order_address")
	  private String order_address;
	  @Column(name="Order_status")
	  private boolean order_status;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_address() {
		return order_address;
	}
	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}
	public boolean isOrder_status() {
		return order_status;
	}
	public void setOrder_status(boolean order_status) {
		this.order_status = order_status;
	}
	
	@ManyToOne
	Client customer;
	
	@ManyToMany(mappedBy = "orders",fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                    })
	
    private Set<Produit> products = new HashSet<>();
	public Set<Produit> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Produit> products) {
		this.products = products;
	}
	public Client getCustomer() {
		return customer;
	}
	public void setCustomer(Client customer) {
		this.customer = customer;
	}
	  

}
