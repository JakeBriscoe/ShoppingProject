/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author brija795
 */
public interface DAOInterface {

    void deleteProduct(Product product);

    Collection<String> getCategories();

    Collection<Product> getProducts();

    void saveProduct(Product product);

    Collection<Product> searchCategory(String category);

    Product searchId(String id);
    
}
