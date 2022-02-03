package nvc.bcit.restapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nvc.bcit.restapi.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    public List<Product> findByNameContaining(String name);
    
}
