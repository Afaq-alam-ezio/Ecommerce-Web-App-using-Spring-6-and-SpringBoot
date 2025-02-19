package com.EcomProject.Ecom.Controller;

import com.EcomProject.Ecom.Model.Product;
import com.EcomProject.Ecom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
//    old funcName() without ResponseEntity
//    public List<Product> getAllProductInfo(){
    public ResponseEntity<List<Product>> getAllProductInfo(){

        return new ResponseEntity<>(service.getAllProductInfo(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProdById(@PathVariable int id){

        Product product = service.getProdById(id);

        if(product != null){

            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {

        try {

            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProdId(@PathVariable int productId){

        Product product = service.getProdById(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile){

        try{

            service.updateProductById(id, product, imageFile);
            return new ResponseEntity<>("Update Success", HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity<>("Failed to Update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id){

        try{

            service.deleteProductById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity<>("Not deleted", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchByProduct(@RequestParam String keyword){

//    Why @RequestParam in This Case?
//    Search Queries Are Dynamic
//
//    Users can search for anything (laptop, phone, shoes, etc.).
//    Using @RequestParam (?keyword=laptop) makes it easier to pass different values.

        List<Product> product = service.searchByProduct(keyword);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
