package br.com.famis.controller;

import br.com.famis.model.Product;
import br.com.famis.service.FamisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/products")
public class ProductController {

    private final FamisService famisService;

    public ProductController( FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") UUID productId){
        Optional<Product> product = this.famisService.findProductById(productId);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = famisService.findAllProducts();
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (product == null) || (product.getName() == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (product == null) || (product.getName() == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Product> updatedProduct = this.famisService.updateProduct(product);
        return updatedProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteById(@PathVariable("productId") UUID productId) {
        Optional<Product> product = this.famisService.findProductById(productId);
        if(product.isPresent()) {
            famisService.deleteProduct(product.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
