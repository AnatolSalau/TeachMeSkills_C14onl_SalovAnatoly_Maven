package by.salov.springboot_scatter_gather.tasks;

import by.salov.springboot_scatter_gather.controllers.ProductController;
import by.salov.springboot_scatter_gather.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Callable for getting objects from link (url) by ObjectMapper (jackson-databind)
 */
@AllArgsConstructor
public class GetProductTask implements Callable<Product> {
    private String shop;
    private Integer id;

    @Override
    public Product call() throws Exception {
        URL url = new URL("http://127.0.0.1:8080/products/" + shop + "/" + id);
        URL url2 = new URL("http://127.0.0.1:8080/products/amazon/1");
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(url, Product.class);
        System.out.println(product);
        return product;
    }
}
