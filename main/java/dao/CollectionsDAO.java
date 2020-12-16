/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author brija795
 */
public class CollectionsDAO implements DAOInterface {
    
    static Collection<Product> products = new HashSet<>();
    Collection<String> categories = new HashSet<>();
    Map<String, Product> productsById = new HashMap<>();
    Multimap<String, Product> productsByCategory = HashMultimap.create();
    
    public CollectionsDAO() {
        
    }
    
    @Override
    public void saveProduct(Product product) {
        products.add(product);
        productsById.put(product.getProductId(), product);
        productsByCategory.put(product.getCategory(), product);
    }
    
    @Override
    public Collection<Product> getProducts() {
        return products;
    }
    
    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
        productsById.remove(product.getProductId());
        productsByCategory.remove(product.getCategory(), product);
    }
    
    @Override
    public Collection<String> getCategories() {
        for(Product product: products) {
            categories.add(product.getCategory());
        }
        return categories;
    }

    @Override
    public Product searchId(String id) {
        return productsById.get(id);
    }
    
    @Override
    public Collection<Product> searchCategory(String category) {
        return productsByCategory.get(category); 
    }
}
