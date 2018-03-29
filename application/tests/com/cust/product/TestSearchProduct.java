package com.cust.product;
import static org.junit.Assert.assertEquals;

import com.cust.product.SearchProduct;
import org.junit.Test;

public class TestSearchProduct {
    @Test
    public void testHello() {
        SearchProduct hello = new SearchProduct();
        assertEquals("Flex Search cannot find item ", hello.sayHello());
    }
}