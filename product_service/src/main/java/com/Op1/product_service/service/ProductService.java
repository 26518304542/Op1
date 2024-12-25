package com.Op1.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;

//import com.Op1.common_library.dto.UpdateStockRequest;
import com.Op1.product_service.domain.Product;
import com.Op1.product_service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    //private final WebClient.Builder webClientBuilder;

    public ProductService(ProductRepository productRepository /*, WebClient.Builder webClientBuilder*/) {
        this.productRepository = productRepository;
        //this.webClientBuilder = webClientBuilder;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found!"));
    }

    public int getProductStock(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        return product.getQuantity();
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found!"));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        return productRepository.save(product);
    }

    public void updateStock(Long productId, int quantityChange){

        /*webClientBuilder.build()
            .patch()
            .uri("http://localhost:8080/products/" + productId + "/update-stock")
            .bodyValue(new UpdateStockRequest(productId, quantityChange))
            .retrieve()
            .toBodilessEntity()
            .block();
            After determining the reason of the error belongs to updateRequest in common_library it will be used*/

        Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() + quantityChange < 0) {
            throw new RuntimeException("Insufficient stock for product!");
        }

        product.setQuantity(product.getQuantity() + quantityChange);
        productRepository.save(product);

    }



    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
