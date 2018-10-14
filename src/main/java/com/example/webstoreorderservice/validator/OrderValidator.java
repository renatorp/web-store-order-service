package com.example.webstoreorderservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.webstoreorderservice.configuration.OrderConfiguration;
import com.example.webstoreorderservice.exception.TooManyItemsException;
import com.example.webstoreorderservice.vo.CreateOrderRequestVO;

@Component
public class OrderValidator {

	@Autowired
	private OrderConfiguration configuration;
	
	public void validateOrder(CreateOrderRequestVO orderRequest) {
		Integer maxItemsPerOrder = configuration.getMaxItemsPerOrder();
		if (orderRequest.getItems().size() > maxItemsPerOrder) {
			throw new TooManyItemsException(maxItemsPerOrder);
		}	
	}
	
}
