package com.cust.product;
import static org.junit.Assert.assertEquals;

import com.cust.product.DeleteProduct;
import org.junit.Test;

public class TestDeleteProduct {
    @Test
    public void testHello() {
        DeleteProduct hello = new DeleteProduct();
        assertEquals("RetailProduct delete success", hello.sayHello());
    }
}