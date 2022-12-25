package by.salov.springboot_scatter_gather.controllers;

import by.salov.springboot_scatter_gather.model.Product;
import by.salov.springboot_scatter_gather.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/amazon/{productId}")
    public Product getProductFromAmazon(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId,"Amazon");
    }

    @GetMapping(path = "/walmart/{productId}")
    public Product getProductFromWalmart(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId,"Walmart");
    }

    @GetMapping(path = "/ebay/{productId}")
    public Product getProductFromEbay(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId,"Ebay");
    }
}
