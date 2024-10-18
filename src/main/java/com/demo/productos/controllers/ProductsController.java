package com.demo.productos.controllers;

import com.demo.productos.entities.ProductsEntity;
import com.demo.productos.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    //Metodo para llamar todos los productos
    @GetMapping
    public List<ProductsEntity> getProducts(){
        return productsService.getAllProducts();
    }

    //Metodo para llamar un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductsEntity> getProductById(@PathVariable UUID id){
        ProductsEntity product = productsService.getProductById(id);
        if(product != null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    //Metodo para crear un producto
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductsEntity product) {
        UUID newProductId = productsService.createProduct(product);
        return ResponseEntity.created(URI.create("/api/products/" + newProductId))
                .body("Product created successfully");
    }

    //Metodo para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable UUID id, @RequestBody ProductsEntity product) {
        Optional<ProductsEntity> updatedProduct = productsService.updateProduct(id, product);
        return ResponseEntity.ok().body("Producto Actualizado Correctamente");
    }

    //Metodo para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        boolean isDeleted = productsService.deleteProducts(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
