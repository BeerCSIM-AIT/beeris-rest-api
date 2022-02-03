package nvc.bcit.restapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("products")
@Getter @Setter @NoArgsConstructor
public class Product {
    @Id
    private String _id;
    private String name;
    private Double price;           //double  Double
    private Integer unit_in_stock;  //int  Integer 

    private List<Review> reviews = new ArrayList<Review>(); 

    //auditing fields
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
