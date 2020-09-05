
package com.product.promotion.api.service;

import java.util.List;

import com.product.promotion.api.response.OrderResponse;

public interface OrderService {

	OrderResponse productPrice(final List<String> productTypes);

}
