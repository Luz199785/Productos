package com.demo.productos.entities;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Se establece la entidad del producto
public class ProductsEntity {
    private UUID id;
    private String name;
    private String category;
    private double price;
    private int stock;
}
