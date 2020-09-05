package com.product.promotion.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.product.promotion.api.exception.ProductException;
import com.product.promotion.api.request.OrderRequest;
import com.product.promotion.api.response.OrderResponse;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Test
	public void productPriceTestOne() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		OrderRequest orderRequest1 = new OrderRequest();
		orderRequest1.setOrderCount(3);
		orderRequest1.setProductType("A");

		orderRequests.add(orderRequest1);
		OrderRequest orderRequest2 = new OrderRequest();
		orderRequest2.setOrderCount(5);
		orderRequest2.setProductType("B");

		orderRequests.add(orderRequest2);
		OrderRequest orderRequest3 = new OrderRequest();
		orderRequest3.setOrderCount(1);
		orderRequest3.setProductType("c");

		orderRequests.add(orderRequest3);
		OrderRequest orderRequest4 = new OrderRequest();
		orderRequest4.setOrderCount(1);
		orderRequest4.setProductType("d");

		orderRequests.add(orderRequest4);

		OrderResponse orderResponse = orderServiceImpl.productPrice(orderRequests);
		Assert.assertEquals("productPriceTestOne", 280, orderResponse.getProductPrice().intValue());
	}

	@Test
	public void productPriceTestTwo() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		OrderRequest orderRequest1 = new OrderRequest();
		orderRequest1.setOrderCount(1);
		orderRequest1.setProductType("A");

		orderRequests.add(orderRequest1);
		OrderRequest orderRequest2 = new OrderRequest();
		orderRequest2.setOrderCount(1);
		orderRequest2.setProductType("B");

		orderRequests.add(orderRequest2);
		OrderRequest orderRequest3 = new OrderRequest();
		orderRequest3.setOrderCount(1);
		orderRequest3.setProductType("c");

		orderRequests.add(orderRequest3);

		OrderResponse orderResponse = orderServiceImpl.productPrice(orderRequests);
		Assert.assertEquals("productPriceTestTwo", 100, orderResponse.getProductPrice().intValue());
	}

	@Test
	public void productPriceTestThree() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		OrderRequest orderRequest1 = new OrderRequest();
		orderRequest1.setOrderCount(5);
		orderRequest1.setProductType("A");

		orderRequests.add(orderRequest1);
		OrderRequest orderRequest2 = new OrderRequest();
		orderRequest2.setOrderCount(5);
		orderRequest2.setProductType("B");

		orderRequests.add(orderRequest2);
		OrderRequest orderRequest3 = new OrderRequest();
		orderRequest3.setOrderCount(1);
		orderRequest3.setProductType("c");

		orderRequests.add(orderRequest3);

		OrderResponse orderResponse = orderServiceImpl.productPrice(orderRequests);
		Assert.assertEquals("productPriceTestThree", 370, orderResponse.getProductPrice().intValue());
	}

	@Test
	public void productPriceTestFour() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		OrderRequest orderRequest1 = new OrderRequest();
		orderRequest1.setOrderCount(5);
		orderRequest1.setProductType("A");

		orderRequests.add(orderRequest1);
		OrderRequest orderRequest2 = new OrderRequest();
		orderRequest2.setOrderCount(5);
		orderRequest2.setProductType("B");

		orderRequests.add(orderRequest2);

		OrderResponse orderResponse = orderServiceImpl.productPrice(orderRequests);
		Assert.assertEquals("productPriceTestFour", 350, orderResponse.getProductPrice().intValue());
	}

	@Test(expected = ProductException.class)
	public void productPriceErrorTestOne() {

		List<OrderRequest> orderRequests = new ArrayList<>();

		orderServiceImpl.productPrice(orderRequests);
	}

	@Test(expected = ProductException.class)
	public void productPriceErrorTestTwo() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		orderRequests.add(new OrderRequest());
		orderServiceImpl.productPrice(orderRequests);
	}

	@Test(expected = ProductException.class)
	public void productPriceErrorTestThree() {

		List<OrderRequest> orderRequests = null;
		orderServiceImpl.productPrice(orderRequests);
	}

	@Test(expected = ProductException.class)
	public void productPriceErrorTestFour() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		OrderRequest orderRequest1 = new OrderRequest();
		orderRequest1.setOrderCount(5);
		orderRequest1.setProductType("F");

		orderRequests.add(orderRequest1);

		orderServiceImpl.productPrice(orderRequests);
	}

	@Test(expected = ProductException.class)
	public void productPriceErrorTestFive() {

		List<OrderRequest> orderRequests = new ArrayList<>();
		OrderRequest orderRequest1 = new OrderRequest();
		orderRequest1.setOrderCount(5);
		orderRequest1.setProductType(null);

		orderRequests.add(orderRequest1);

		orderServiceImpl.productPrice(orderRequests);
	}

}
