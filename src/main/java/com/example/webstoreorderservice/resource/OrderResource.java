package com.example.webstoreorderservice.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.webstoreorderservice.entity.Order;
import com.example.webstoreorderservice.service.OrderService;
import com.example.webstoreorderservice.vo.CreateOrderRequestVO;

@RestController
@RequestMapping("orders")
public class OrderResource {

	@Autowired	
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestVO requestBody) {
		
		Order order = orderService.placeOrder(requestBody);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/orders/{orderId}")
				.buildAndExpand(order.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{id}")
	public Order checkOrder(@PathVariable("id") Integer id) {
		return orderService.getOrder(id);
	}
	
}
