package com.product.promotion.api.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConstantsTest {

	@Test(expected = UnsupportedOperationException.class)
	public void constructorTest() {
		new Constants();
	}

}
