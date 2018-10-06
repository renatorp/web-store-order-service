package com.example.webstoreorderservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webstoreorderservice.dao.OrderDAO;
import com.example.webstoreorderservice.dao.OrderItemDAO;
import com.example.webstoreorderservice.entity.Order;
import com.example.webstoreorderservice.entity.OrderItem;
import com.example.webstoreorderservice.exception.OrderNotFoundException;
import com.example.webstoreorderservice.serviceproxy.CartServiceProxy;
import com.example.webstoreorderservice.vo.CreateOrderRequestVO;
import com.example.webstoreorderservice.vo.OrderItemRequestVO;
import com.example.webstoreorderservice.vo.ProductVO;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderItemDAO orderItemDAO;

	@Autowired
	private CartServiceProxy cartProxy;

	public Order placeOrder(CreateOrderRequestVO orderRequest) {

		List<ProductVO> products = cartProxy.findCartItemsProducts(orderRequest.getCartId());
		Order order = createOrder(orderRequest, products);

		cartProxy.clearCart(orderRequest.getCartId());
		
		return order;
	}

	private Order createOrder(CreateOrderRequestVO orderRequest, List<ProductVO> products) {
		Order order = new Order();
		order.setAddress(orderRequest.getAddress());
		order.setCustomerId(orderRequest.getCustomerId());
		order.setOrderItems(createOrderItems(orderRequest.getItems(), products, order));
		order.setTotalPrice(calculateTotalPriceOrder(orderRequest, order.getOrderItems()));
		return saveOrder(order);
	}

	private Order saveOrder(Order order) {
		order = orderDAO.save(order);
		order.getOrderItems().forEach(orderItemDAO::save);
		return order;
	}

	private List<OrderItem> createOrderItems(List<OrderItemRequestVO> orderItemsRequest, List<ProductVO> products,
			Order order) {
		List<OrderItem> orderItems = new ArrayList<>();

		Map<Integer, ProductVO> mapProducts = products.stream()
				.collect(Collectors.toMap(ProductVO::getId, Function.identity()));

		for (OrderItemRequestVO requestItem : orderItemsRequest) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setQuantity(requestItem.getQuantity());
			item.setProductId(requestItem.getProductId());
			
			BigDecimal productPrice = mapProducts.get(requestItem.getProductId()).getPrice();
			item.setPrice(productPrice.multiply(BigDecimal.valueOf(item.getQuantity())));

			orderItems.add(item);
		}

		return orderItems;
	}

	private BigDecimal calculateTotalPriceOrder(CreateOrderRequestVO orderRequest, List<OrderItem> orderItems) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (OrderItem item : orderItems) {
			totalPrice = totalPrice.add(item.getPrice());
		}
		return totalPrice;
	}

	public Order getOrder(Integer id) {
		Optional<Order> opt = orderDAO.findById(id);
		
		if (!opt.isPresent()) {
			throw new OrderNotFoundException("Order not found!");
		}
		
		return opt.get();
	}

}
