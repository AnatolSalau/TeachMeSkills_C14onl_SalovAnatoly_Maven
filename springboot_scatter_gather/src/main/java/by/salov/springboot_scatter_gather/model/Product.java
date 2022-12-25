package by.salov.springboot_scatter_gather.model;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Product {

    private int id;
    private String name;
    private double price;
    private String description;

}
