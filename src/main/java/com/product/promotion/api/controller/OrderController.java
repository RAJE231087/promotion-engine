
package com.product.promotion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.promotion.api.exception.ProductException;
import com.product.promotion.api.response.OrderResponse;
import com.product.promotion.api.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/Order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> productPrice(final @Valid @RequestBody List<String> productTypes) {
		try {
			final OrderResponse orderResponse = orderService.productPrice(productTypes);
			return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
		} catch (ProductException e) {
			return new ResponseEntity<ProductException>(e, e.getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}