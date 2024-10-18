package com.demo.productos.services;

import com.demo.productos.entities.ProductsEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    private List<ProductsEntity> productos;

    public ProductsService() {
        //Se crea una Lista para amacenar los productos
        productos = new ArrayList<>();
        productos.add(new ProductsEntity(UUID.randomUUID(),"Televisor", "Electrodomésticos", 3200000, 15));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Lavadora", "Electrodomésticos", 1500000, 10));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Refrigerador", "Electrodomésticos", 2800000, 5));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Microondas", "Electrodomésticos", 500000, 15));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Aspiradora", "Electrodomésticos", 700000, 8));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Plancha", "Electrodomésticos", 200000, 25));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Licuadora", "Electrodomésticos", 300000, 30));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Cafetera", "Electrodomésticos", 250000, 12));
        productos.add(new ProductsEntity(UUID.randomUUID(),"Horno eléctrico", "Electrodomésticos", 1200000, 7));
    }

    //Retorna todos los productos
    public List<ProductsEntity> getAllProducts() {
        return productos;
    }

    //Retorna el producto por su ID
    public ProductsEntity getProductById(UUID id) {
        return productos.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

    //Crea un producto con un ID
    public UUID createProduct(ProductsEntity product) {
        product.setId(UUID.randomUUID());
        productos.add(product);
        return product.getId();
    }

    //Actualiza los productos
    public Optional<ProductsEntity> updateProduct(UUID id, ProductsEntity updateproduct) {
        Optional<ProductsEntity> existingProduct = productos.stream().filter(product -> product.getId().equals(id)).findFirst();
        if (existingProduct.isPresent()) {
            ProductsEntity product = existingProduct.get();
            product.setId(id);
            productos.add(product);
            return Optional.of(product);
        }
        return existingProduct;
    }


    //Elimina los productos
    public boolean deleteProducts(UUID id) {
        Optional<ProductsEntity> productToDelete = productos.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();

        if (productToDelete.isPresent()) {
            productos.remove(productToDelete.get());
            return true;
        }
        return false;
    }
}
