
package com.product.promotion.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.promotion.api.exception.ProductException;
import com.product.promotion.api.response.OrderResponse;
import com.product.promotion.api.service.OrderService;
import com.product.promotion.api.utils.Constants;

@Service
public class OrderServiceImpl implements OrderService {

	public OrderResponse productPrice(final List<String> productTypes) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setProductPrice(totalPriceAmount(productTypes));
		return orderResponse;
	}

	private int totalPriceAmount(List<String> productTypes) {
		int counterA = 0;
		int counterB = 0;
		int counterC = 0;
		int counterD = 0;

		if (null == productTypes || productTypes.isEmpty()) {
			throw new ProductException(Constants.PRODUCT_TYPES_EMPTY_ERR_MSG, HttpStatus.BAD_REQUEST);
		} else {
			for (String productType : productTypes) {
				switch (productType) {
				case "A":
				case "a":
					counterA = counterA + 1;
					break;
				case "B":
				case "b":
					counterB = counterB + 1;
					break;
				case "C":
				case "c":
					counterC = counterC + 1;
					break;
				case "D":
				case "d":
					counterD = counterD + 1;
					break;
				default:
					throw new ProductException(Constants.PRODUCT_TYPES_ERR_MSG, HttpStatus.BAD_REQUEST);
				}
			}

			return calculatetotalAmount(counterA, counterB, counterC, counterD);

		}

	}

	private int calculatetotalAmount(final int counterA, final int counterB, final int counterC, int counterD) {
		return ((counterA / 3) * 130 + (counterA % 3 * Constants.priceA))
				+ ((counterB / 2) * 45 + (counterB % 2 * Constants.priceB)) + (counterC * Constants.priceC)
				+ (counterD * Constants.priceD);

	}

}
