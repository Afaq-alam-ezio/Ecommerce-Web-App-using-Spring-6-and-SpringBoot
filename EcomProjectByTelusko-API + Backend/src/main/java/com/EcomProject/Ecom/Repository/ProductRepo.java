package com.EcomProject.Ecom.Repository;

import com.EcomProject.Ecom.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     // Man!!! you were not able to get the data on api tool, cuz you forgot this annotation
public interface ProductRepo extends JpaRepository <Product, Integer> {

//    This is a JPQL (Java Persistence Query Language) query, not a native SQL query.
//    It searches for products where the keyword matches any of the following fields:

//    name
//    description
//    brand
//    category

//    It performs a case-insensitive search using LOWER(), meaning it treats uppercase and lowercase letters the same.
//    The LIKE operator is used to find partial matches (e.g., searching "phone" will match "Smartphone").
//    The CONCAT('%', :keyword, '%') part ensures that the keyword can appear anywhere in the field.

//      Why is LOWER() Used in Both p.name and :keyword?
//            ✅ LOWER(p.name) → Converts the product name (from the database) to lowercase.
//            ✅ LOWER(:keyword) → Converts the search input (from the user) to lowercase.
//
//    This ensures that both sides are in the same format before comparison, so case differences do not affect the search results.

//    What If We Used Product Instead of List<Product>?
//    If the return type was Product, it would only allow a single product to be returned.
//    But since the search can match multiple products, a List<Product> is needed.

//    Below p is an alias for Product class/Table

    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    public List<Product> searchByProduct(String keyword);
}