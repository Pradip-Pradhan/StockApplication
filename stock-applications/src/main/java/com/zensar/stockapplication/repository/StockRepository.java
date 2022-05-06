package com.zensar.stockapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.stockapplication.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
//	List<Stock> findByName(String name);
//	List<Stock> findByNameAndPrice(String name, double price);
	
	
	
	@Query(value = "select * from Stock s where s.name=:name",nativeQuery = true)
	List<Stock> findStockByName(@Param("name") String name);
	@Query(value = "select * from Stock s where s.name=:name and s.price=:price", nativeQuery = true)
	List<Stock> findByNameAndPrice(@Param("name") String name,@Param("price") double price);

}
