package com.zensar.stockapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MyStock")
@Table(name = "Stock")

////@NamedQueries(value = { @NamedQuery(name = "Stock.findStockByName", query = "FROM MyStock s where s.name=?1"),
////		@NamedQuery(name = "Stock.findStockByNameAndPrice", query = "FROM MyStock s where s.name=?1 and s.price=?2") })
//@NamedNativeQuery(name = "Stock.findStockByName", query = "select * from Stock s where s.name=?1",resultClass = Stock.class)
public class Stock {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long stockId;
	private String name;
	private String marketName;
	private double price;

}
