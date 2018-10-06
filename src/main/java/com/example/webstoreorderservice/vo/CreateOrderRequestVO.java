package com.example.webstoreorderservice.vo;

import java.util.List;

public class CreateOrderRequestVO {

	private List<OrderItemRequestVO> items;

	private Integer cartId;

	private Integer customerId;

	private String address;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderItemRequestVO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequestVO> items) {
		this.items = items;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

}
