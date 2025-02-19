package com.EcomProject.Ecom.Service;

import com.EcomProject.Ecom.Model.Product;
import com.EcomProject.Ecom.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getAllProductInfo() {

        return repo.findAll();
    }

    public Product getProdById(int id) {

//        you can use any one from below choices.
//        return repo.findById(Id).orElse(new Product());   returns null if product id not found
//        return repo.findById(Id).get();       returns error if product id not found

        return repo.findById(id).orElse(null); // could have also used return repo.findById(id).orElse(new Product());
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

//       VERY IMPORTANT BELOW  you struggled for like 1 hr, it's the imageFile.func() but  you were using product.func()

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public Product updateProductById(int id, Product product, MultipartFile imageFile) throws IOException {

            product.setImageData(imageFile.getBytes());
            product.setImageType(imageFile.getContentType());
            product.setImageName(imageFile.getOriginalFilename());

            return repo.save(product);
    }

    public void deleteProductById(int id) {

        Product product = getProdById(id);
        if(product != null){

            repo.deleteById(id);
        }
    }

    public List<Product> searchByProduct(String keyword) {

        return repo.searchByProduct(keyword);
    }
}
