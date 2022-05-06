package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;
import com.zensar.stockapplication.stockdto.StockDto;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;
	
	//private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private ModelMapper modelMapper;

//List<Stock> stocks=new ArrayList<Stock>();
//	
//	public StockServiceImpl() {
//		stocks.add(new Stock(101, "zenl", "zensar", 10000));
//		stocks.add(new Stock(102, "zeno", "zensar", 10001));
//		stocks.add(new Stock(103, "zenv", "zensar", 10002));
//		stocks.add(new Stock(104, "zene", "zensar", 10003));
//	}

	@Override
	public List<StockDto> getAllStock(int pageNumber, int pageSize, String sortBy) {
		Page<Stock> pageStock = stockRepository.findAll(PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy)));
		List<Stock> content = pageStock.getContent();
		List<StockDto> stockDtos = new ArrayList<>();

		for (Stock stock : content) {
			StockDto mapToResponse = modelMapper.map(stock, StockDto.class);
			stockDtos.add(mapToResponse);
		}
		return stockDtos;
	}

//	@Override
//	public List<StockResponse> getStockByName(String name) {
//		
//		List<Stock> findName=stockRepository.findByName(name);
//		List<StockResponse> stocks=new ArrayList<>();
//		for(Stock st:findName) {
//			stocks.add( modelMapper.map(st, StockResponse.class));
//		}
//		return stocks;
//		
//	}
//	
//	@Override
//	public List<StockResponse> getStockByNameAndPrice(String name,double price) {
//		
//		List<Stock> findName=stockRepository.findByNameAndPrice(name, price);
//		List<StockResponse> stocks=new ArrayList<>();
//		for(Stock st:findName) {
//			stocks.add( modelMapper.map(st, StockResponse.class));
//		}
//		return stocks;
//		
//	}
	
	public List<StockDto> findStockByName(String name) {
		
		List<Stock> findName=stockRepository.findStockByName(name);
		List<StockDto> stocks=new ArrayList<>();
		for(Stock st:findName) {
			stocks.add( modelMapper.map(st, StockDto.class));
		}
		return stocks;
		
	}
	@Override
	public List<StockDto> findStockByNameAndPrice(String name,double price) {
		
		List<Stock> findName=stockRepository.findByNameAndPrice(name, price);
		List<StockDto> stocks=new ArrayList<>();
		for(Stock st:findName) {
			stocks.add( modelMapper.map(st, StockDto.class));
		}
		return stocks;
		
	}
	
	@Override
	public StockDto getStock(long stockId) throws InvalidStockIdException {

		Optional<Stock> optionalStock=stockRepository.findById(stockId);
		Stock stock=null;
		if(optionalStock.isPresent()) {
			stock=optionalStock.get();
			return modelMapper.map(stock, StockDto.class);
		}else {
			throw new InvalidStockIdException("Stock is not avilable with stockId "+stockId);
		}
		
		
//		Stock stock = stockRepository.findById(stockId).get();
//		StockDto stockResponse = new StockDto();
//		stockResponse.setStockId(stock.getStockId());
//		stockResponse.setName(stock.getName());
//		stockResponse.setPrice(stock.getPrice());
//		stockResponse.setMarketName(stock.getMarketName());

//		return stockResponse;

//		java.util.Optional<Stock> particularStock=stocks.stream().filter(stock-> stock.getStockId()==stockId).findAny();
//		if(particularStock.isPresent()) {
//			return particularStock.get();
//		}else {
//			return particularStock.orElseGet(()->{return new Stock();});
//		}
	}

	@Override
	public StockDto createStock(StockDto stock, String token) {

		Stock newStock = modelMapper.map(stock, Stock.class);

		// return stockRepository.save(stock);

		if (token.equals("sr43993")) {
			Stock save = stockRepository.save(newStock);
			return modelMapper.map(save, StockDto.class);

		} else {
			return null;
		}
	}

	@Override
	public String deleteStock(long stockId) {
		stockRepository.deleteById(stockId);
		return "deleted stockId " + stockId;
//		for(Stock stock: stocks) {
//			if(stock.getStockId()==stockId) {
//				stocks.remove(stock);
//				return "deleted stockId "
//						+ stockId;
//			}
//		}
//		return "not found";
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stock) throws InvalidStockIdException {

		StockDto stockResponse = getStock(stockId);

//		Stock stock2 = mapToEntity(stockResponse);
		Stock stock2 = modelMapper.map(stockResponse, Stock.class);

		if (stock2 != null) {
			stock2.setPrice(stock.getPrice());
			stock2.setName(stock.getName());
			stock2.setMarketName(stock.getMarketName());
			stock2.setStockId(stockId);
			Stock stock3 = stockRepository.save(stock2);
			return modelMapper.map(stock3, StockDto.class);

		}

		return null;
	}

	@Override
	public String deleteAllStock() {
		stockRepository.deleteAll();
//		stocks.removeAll(stocks);
		return "all stock is deleted succesfully";
	}

//	private Stock mapToEntity(StockDto stockRequest) {
//
//		Stock newStock = new Stock();
//
//		newStock.setMarketName(stockRequest.getMarketName());
//		newStock.setName(stockRequest.getName());
//		newStock.setPrice(stockRequest.getPrice());
//
//		return newStock;
//
//	}

//	private Stock mapToStock(StockDto stockResponse) {
//
//		Stock newStock = new Stock();
//
//		newStock.setMarketName(stockResponse.getMarketName());
//		newStock.setName(stockResponse.getName());
//		newStock.setPrice(stockResponse.getPrice());
//
//		return newStock;
//
//	}

//	private StockDto mapToDto(Stock stock) {
//
//		StockDto stockResponse = new StockDto();
//
//		stockResponse.setStockId(stock.getStockId());
//
//		stockResponse.setPrice(stock.getPrice());
//
//		stockResponse.setName(stock.getName());
//
//		stockResponse.setMarketName(stock.getMarketName());
//
//		return stockResponse;
//
//	}
	
	
}
