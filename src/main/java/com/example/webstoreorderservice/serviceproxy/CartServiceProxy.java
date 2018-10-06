package com.example.webstoreorderservice.serviceproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webstoreorderservice.vo.ProductVO;

@RibbonClient
@FeignClient(name = "web-store-cart-service")
@RequestMapping("carts")
public interface CartServiceProxy {

	@GetMapping("{id}/items/products")
	public List<ProductVO> findCartItemsProducts(@PathVariable("id") Integer id);

	@DeleteMapping("{id}/items")
	public void clearCart(@PathVariable("id") Integer id);
}