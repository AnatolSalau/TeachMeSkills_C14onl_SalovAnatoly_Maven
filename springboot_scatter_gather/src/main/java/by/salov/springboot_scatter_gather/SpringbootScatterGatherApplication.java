package by.salov.springboot_scatter_gather;

import by.salov.springboot_scatter_gather.model.Product;
import by.salov.springboot_scatter_gather.tasks.GetProductTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class SpringbootScatterGatherApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(SpringbootScatterGatherApplication.class, args);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        int productId = 10;
        List<String> shops = List.of("amazon", "ebay", "walmart");
        List<Future<Product>> futureProductList = new ArrayList<>();

        for (String shop : shops) {
            futureProductList.add(executorService.submit(new GetProductTask(shop, productId)));
        }

        StringBuffer result = new StringBuffer();

        for (Future<Product> productFuture : futureProductList) {
            Product product = productFuture.get();
            result
                    .append(product.getName())
                    .append(": ")
                    .append(product.getDescription());
        }
        System.out.println(result);
        executorService.shutdown();
    }
}
