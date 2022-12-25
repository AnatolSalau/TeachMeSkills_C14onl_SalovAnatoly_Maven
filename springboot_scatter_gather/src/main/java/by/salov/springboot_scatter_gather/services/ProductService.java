package by.salov.springboot_scatter_gather.services;

import by.salov.springboot_scatter_gather.model.Product;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProductService {

    public Product getProductById(int productId, String shop){
        Random random = new Random();

        Product product = new Product(
                productId,
                "Test product",
                random.nextInt(),
                "from " + shop
        );
        return product;
    }
}
