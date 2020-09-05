
package com.product.promotion.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.promotion.api.exception.ProductException;
import com.product.promotion.api.request.OrderRequest;
import com.product.promotion.api.response.OrderResponse;
import com.product.promotion.api.service.OrderService;
import com.product.promotion.api.utils.Constants;

@Service
public class OrderServiceImpl implements OrderService {

	public OrderResponse productPrice(final List<OrderRequest> orderRequests) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setProductPrice(totalPriceAmount(orderRequests));
		return orderResponse;
	}

	private int totalPriceAmount(List<OrderRequest> orderRequests) {
		int counterA = 0;
		int counterB = 0;
		int counterC = 0;
		int counterD = 0;

		if (null == orderRequests || orderRequests.isEmpty()) {
			throw new ProductException(Constants.PRODUCT_TYPES_EMPTY_ERR_MSG, HttpStatus.BAD_REQUEST);
		} else {
			for (OrderRequest orderRequest : orderRequests) {
				if (null != orderRequest.getProductType()) {
					int orderCount = orderRequest.getOrderCount();
					switch (orderRequest.getProductType()) {
					case "A":
					case "a":
						counterA = counterA + orderCount;
						break;
					case "B":
					case "b":
						counterB = counterB + orderCount;
						break;
					case "C":
					case "c":
						counterC = counterC + orderCount;
						break;
					case "D":
					case "d":
						counterD = counterD + orderCount;
						break;
					default:
						throw new ProductException(Constants.PRODUCT_TYPES_ERR_MSG, HttpStatus.BAD_REQUEST);
					}
				} else {
					throw new ProductException(Constants.PRODUCT_TYPES_ERR_MSG, HttpStatus.BAD_REQUEST);
				}
			}

			return calculatetotalAmount(counterA, counterB, counterC, counterD);

		}

	}

	private int calculatetotalAmount(final int counterA, final int counterB, final int counterC, int counterD) {

		if (counterC > 0 && counterD > 0) {
			int count = counterC + counterD;
			return calculatetotalAmount(counterA, counterB) + ((count / 2) * 30) + (((count % 2) * Constants.priceCD));
		} else {
			return calculatetotalAmount(counterA, counterB) + (counterC * Constants.priceC)
					+ (counterD * Constants.priceD);
		}
	}

	private int calculatetotalAmount(final int counterA, final int counterB) {
		return (((counterA / 3) * 130) + (((counterA % 3) * Constants.priceA)))
				+ (((counterB / 2) * 45) + (((counterB % 2) * Constants.priceB)));
	}
}
