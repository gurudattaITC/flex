package com.cust.product;

public class SearchProduct {
    public static void main(String[] args) {
        SearchProduct hello = new SearchProduct();
        System.out.println(hello.sayHello());
    }
    protected String sayHello() {
        return "Flex Search product started!";
    }
}