package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.stockdto.StockDto;

public interface StockService {
	
	List<StockDto> getAllStock(int pageNumber,int pageSize,String sortBy);
	
	StockDto getStock(long stockId) throws InvalidStockIdException;
	
	StockDto createStock( StockDto stock,String token );
	
	String deleteStock(long stockId);
	
	StockDto updateStock( int stockId, StockDto stock) throws InvalidStockIdException;
	
	String deleteAllStock();
	
	
	
	
	//Custom db
	
//	List<StockResponse> getStockByName(String name);
//	List<StockResponse> getStockByNameAndPrice(String name,double price);
	
	
	
	List<StockDto> findStockByName(String name);
	
	List<StockDto> findStockByNameAndPrice(String name,double price);

}
