package com.cust.product;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.cust.product.UpdateProduct;


public class TestProductDetails {
    public static void main(String[] args) {
        /*Result result = JUnitCore.runClasses(
            TrxAmountTest.class,
            TrxCatTest.class,
            TrxDateParserTest.class,
            TrxDateTest.class,
            TrxSetTest.class,
            TrxTest.class
        );
*/
	Result result = JUnitCore.runClasses(
          
            UpdateProduct.class
        );

        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.exit(0);
        }
        System.exit(1);
    }
}