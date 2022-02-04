package nvc.bcit.restapi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nvc.bcit.restapi.model.Product;
import nvc.bcit.restapi.model.Review;
import nvc.bcit.restapi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> getAllProducts(){
        List<Product> products = productService.getProducts();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK");
        map.put("data", products);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK");
        map.put("data", productService.findById(id));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProductsByName(@PathVariable String name){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK");
        map.put("data", productService.findByProductName(name));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK");
        map.put("data", productService.addProduct(product));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id,@RequestBody Product product){
        // ต้องการ ทุกฟิลด์ของ product 

        Product currentProduct = productService.findById(id).get();
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setUnit_in_stock(product.getUnit_in_stock());
        currentProduct.setCreatedAt(product.getCreatedAt()); 

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Update OK");
        map.put("data", productService.updateProduct(id, currentProduct));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> addReview(@PathVariable String id,@RequestBody Review review){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Add Review OK");
        map.put("data", productService.addReview(id, review));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        if(!productService.deleteProduct(id)){
            map.put("msg", "Error on delete product id: " + id);
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR); 
        } else{
            map.put("msg", "Delete product id " + id + " successfully.");
            return new ResponseEntity<Object>(map, HttpStatus.OK); 
        }
    }
}
