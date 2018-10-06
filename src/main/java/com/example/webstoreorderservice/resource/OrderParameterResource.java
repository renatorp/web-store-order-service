package com.example.webstoreorderservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webstoreorderservice.configuration.Configuration;

@RestController
@RequestMapping("order/parameter")
public class OrderParameterResource {

	@Autowired
	private Configuration configuration;

	@GetMapping("maxitems")
	public Integer retrieveMaxItemsPerOrder() {
		return configuration.getMaxItemsPerOrder();
	}

}
