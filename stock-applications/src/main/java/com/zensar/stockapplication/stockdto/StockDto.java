package com.zensar.stockapplication.stockdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

	private long stockId;
	private String name;
	private String marketName;
	private double price;
	
}