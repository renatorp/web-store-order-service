package com.example.webstoreorderservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class OrderConfiguration {

	private Integer maxItemsPerOrder;

	public Integer getMaxItemsPerOrder() {
		return maxItemsPerOrder;
	}

	public void setMaxItemsPerOrder(Integer maxItemsPerOrder) {
		this.maxItemsPerOrder = maxItemsPerOrder;
	}

}
