package com.pzy.entity;
/***
 * 分类
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**订单实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_item")
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Order toorder;
	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;
	
	private Integer num;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getToorder() {
		return toorder;
	}

	public void setToorder(Order order) {
		this.toorder = order;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
}