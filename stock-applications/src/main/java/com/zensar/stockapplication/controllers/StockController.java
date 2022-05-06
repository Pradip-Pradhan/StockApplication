package com.zensar.stockapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.service.StockService;
import com.zensar.stockapplication.stockdto.StockDto;

@RestController
//@Controller
//@CrossOrigin("*")
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;
	
	
	//get all stock
	//@GetMapping("/stocks")?pageNumber=0&pageSize=2&sortBy=marketName
	//@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<StockDto> getAllStock(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,@RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = "stockId",required = false) String sortBy) {
		return stockService.getAllStock(pageNumber,pageSize, sortBy);
	}
	
//	@RequestMapping(value = "/test", method = {RequestMethod.GET,RequestMethod.POST})
//	public void test() {
//		System.out.println("i m in test");
//	}
	
	
	//get specific stock
	//@GetMapping("/stocks/{stockId}")
//	@RequestMapping(value = "/{stockId}", method = RequestMethod.GET)
//	public Stock getStock(@PathVariable long stockId) {
//		
//		for(Stock stock: stocks) {
//			if(stock.getStockId()==stockId) {
//				return stock;
//			}
//		}
//		
//		return null;
//		
//	}
	
	@RequestMapping(value = "/{stockId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public StockDto getStock(@PathVariable long stockId) throws InvalidStockIdException {
		
		return stockService.getStock(stockId);
	}
	
//	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//	public List<StockResponse> getStockByName(@PathVariable("name") String name) {
//		
//		return stockService.getStockByName(name);
//	}
//	
//	@RequestMapping(value = "/name/{name}/price/{price}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//	public List<StockResponse> getStockByNameAndPrice(@PathVariable("name") String name,@PathVariable("price") double price) {
//		
//		return stockService.getStockByNameAndPrice(name,price);
//	}
	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<StockDto> getStockByName(@PathVariable("name") String name) {
		
		return stockService.findStockByName(name);
	}
	
	@RequestMapping(value = "/name/{name}/price/{price}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<StockDto> findStockByNameAndPrice(@PathVariable("name") String name,@PathVariable("price") double price) {
		
		return stockService.findStockByNameAndPrice(name,price);
	}
	
	
	
	
	
	
	//@GetMapping("/stocks/param")
//	@RequestMapping(value = "/param", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//	public Stock getStockParam(@RequestParam(required = false, value = "id", defaultValue = "101")  long stockId) {
//		
//		for(Stock stock: stocks) {
//			if(stock.getStockId()==stockId) {
//				return stock;
//			}
//		}
//		
//		return null;
//		
//	}
	
	
	
	//status update
	//@PostMapping("/stocks")
	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<StockDto> createStock( @RequestBody StockDto stock, @RequestHeader("auth-token")String token ) {
		System.out.println(token);
		StockDto createdStock= stockService.createStock(stock, token);	
		
		return new ResponseEntity<StockDto>(createdStock, HttpStatus.CREATED);
	}
	
	
	//delete specific stock
	@DeleteMapping(value = "/{stockId}")
	public String deleteStock(@PathVariable long stockId) {
		return stockService.deleteStock(stockId);
		
	}
	
	
	//update stock
	@PutMapping(value = "/{stockId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public StockDto updateStock(@PathVariable int stockId, @RequestBody StockDto stock) throws InvalidStockIdException {
		
		return stockService.updateStock(stockId, stock);
//		Stock availableStock=getStock(stockId);
//		availableStock.setMarketName(stock.getMarketName());
//		availableStock.setName(stock.getName());
//		availableStock.setPrice(stock.getPrice());
//		
//		return availableStock;
	}
	
	
	//delete all stock at-a-time
	@DeleteMapping
	public ResponseEntity<String> deleteAllStock(){
		stockService.deleteAllStock();
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
//	@ExceptionHandler(InvalidStockIdException.class)
//	public String handleException() {
//		return "invalid stockId";
//	}
//	
	
}
