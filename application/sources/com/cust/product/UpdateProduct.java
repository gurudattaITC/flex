package com.cust.product;

public class UpdateProduct {
	public static void main(String[] args) {
		ProductUtils pm = new ProductUtils();
		int number1 = 20;
		boolean prime = pm.testPrime(number1);
		System.out.println("Testing number1:::"+number1+":::is prime:::"+prime);
	}
}
