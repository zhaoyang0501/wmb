package com.pzy.entity;
import java.util.ArrayList;
import java.util.Date;




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
/***
 * 分类
 *
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**订单实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_order")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date orderDate;
	
	private String orderTime;
	
	private String addr;
	
	private String remark;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="_user") 
	private User user;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date createDate;
	
	private String state;
	
	private Double toalprice;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL) 
	private List<Item> items;
	
	private Double sendprice;
	 
	private Double orderprice;
	
	@ManyToOne
	private Seller seller;

	
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Double getSendprice() {
		return sendprice;
	}

	public void setSendprice(Double sendprice) {
		this.sendprice = sendprice;
	}

	public Double getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(Double orderprice) {
		this.orderprice = orderprice;
	}

	public void addPorject(Project project){
		if(items==null)
			items=new ArrayList<Item>();
		Item tagerItem=null;
		for(Item item:items){
			if(item.getProject().getId().equals(project.getId())){
				item.setNum(item.getNum()+1);
				this.setSendprice(project.getSeller().getSendprice());
				
				Double orderprice=0d;
				for(Item item1:items){
					orderprice+=item1.getProject().getPrice()*item1.getNum();
				}
				this.setOrderprice(orderprice);
				this.setToalprice(orderprice+this.getSendprice());
				return;
			}
				
		}	
		if(tagerItem==null){
			tagerItem=new Item();
			tagerItem.setProject(project);
			tagerItem.setNum(1);
			tagerItem.setToorder(this);
			this.items.add(tagerItem);
		}
		
		this.setSendprice(project.getSeller().getSendprice());
		Double orderprice=0d;
		
		for(Item item:items){
			orderprice+=item.getProject().getPrice()*item.getNum();
		}
		this.setOrderprice(orderprice);
		this.setToalprice(orderprice+this.getSendprice());
	} 
	 
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Double getToalprice() {
		return toalprice;
	}
	public void setToalprice(Double toalprice) {
		this.toalprice = toalprice;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}