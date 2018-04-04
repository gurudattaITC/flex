/**
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
*
* @author  DemoUser
* @version 1.0
* @since   2014-03-31 
*/


package com.custom.product;

import wt.fc.WTObject;
import wt.util.WTException;
import com.lcs.wc.product.LCSProduct;
import java.util.UUID;
import com.lcs.wc.foundation.LCSLogic;


// Class to handle all product utilities
public class ProductUtil {
	
	/**
   * This method is used to set the uniqueIdentifier
   * @param WTObject
   * @return void .
   */
	public static void updateProductRangeAndUniqueNumber(WTObject obj) throws WTException {
		
		// Convert to LCSProduct Object
		LCSProduct product = (LCSProduct) obj;
		// Get identifier from Product object
		String uniqueIdentifier = (String) product.getValue("uniqueIdentifier");
		System.out.println("uniqueIdentifier : " + uniqueIdentifier);
		if(uniqueIdentifier==null || "".equals(uniqueIdentifier.trim())) {
			// Set value to identifier.
			product.setValue("uniqueIdentifier", UUID.randomUUID().toString());
			LCSLogic.persist(product, true);
		}
	}
	
	
}
