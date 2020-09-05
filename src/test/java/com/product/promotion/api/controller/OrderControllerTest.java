package com.product.promotion.api.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.product.promotion.api.exception.ProductException;
import com.product.promotion.api.response.OrderResponse;
import com.product.promotion.api.service.OrderService;
import com.product.promotion.api.utils.Constants;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {

	private MockMvc mockMvc;

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void productPriceTest() throws Exception {
		String content = "[\r\n" + "  {\r\n" + "    \"productType\": \"A\",\r\n" + "    \"orderCount\": 1\r\n"
				+ "  },\r\n" + "  {\r\n" + "    \"productType\": \"A\",\r\n" + "    \"orderCount\": 2\r\n" + "  },\r\n"
				+ "  {\r\n" + "    \"productType\": \"B\",\r\n" + "    \"orderCount\": 5\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"productType\": \"C\",\r\n" + "    \"orderCount\": 1\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"productType\": \"D\",\r\n" + "    \"orderCount\": 1\r\n" + "  }\r\n" + "]";
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setProductPrice(280);
		Mockito.when(orderService.productPrice(Mockito.anyList())).thenReturn(orderResponse);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/Order").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
				.andReturn();
		Assert.assertEquals("productPriceTest", HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		Assert.assertEquals("productPriceTest", "{\"productPrice\":280}", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void productPriceErrorTest() throws Exception {
		String content = "[]";
		ProductException productException = new ProductException(Constants.PRODUCT_TYPES_EMPTY_ERR_MSG,
				HttpStatus.BAD_REQUEST);
		Mockito.when(orderService.productPrice(Mockito.anyList())).thenThrow(productException);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/Order").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		Assert.assertEquals("productPriceTest", HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
		Assert.assertEquals("productPriceTest",
				"{\"errorMessage\":\"Product types can not be empty or null\",\"statusCode\":400}",
				mvcResult.getResponse().getContentAsString());

	}

}
