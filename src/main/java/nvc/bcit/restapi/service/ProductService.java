package nvc.bcit.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nvc.bcit.restapi.model.Product;
import nvc.bcit.restapi.model.Review;
import nvc.bcit.restapi.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }

    public List<Product> findByProductName(String name){
        return productRepository.findByNameContaining(name);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(String id, Product product){
        // search product เดิม
        Product currentProduct = productRepository.findById(id).get();
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setUnit_in_stock(product.getUnit_in_stock());
        return Optional.of(productRepository.save(currentProduct));
    }

    public Optional<Product> addReview(String id, Review review){
        // search product เดิม
        Product currentProduct = productRepository.findById(id).get();
        List<Review> reviews = currentProduct.getReviews();
        reviews.add(review);
        currentProduct.setReviews(reviews);
        return Optional.of(productRepository.save(currentProduct));
    }

    public boolean deleteProduct(String id){
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
