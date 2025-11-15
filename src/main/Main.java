package main;

import service.ProductService;

public class Main {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        AppMenu appMenu = new AppMenu(productService);
        appMenu.start();
    }
}
