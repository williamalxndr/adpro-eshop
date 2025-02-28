package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class ProductRepository implements RepositoryInterface<Product> {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            String id = IdGenerator.generateUUID();
            product.setProductId(id);
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {  // Return product dengan id [id], jika tidak ditemukan return null
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product update(String id, Product updatedProduct) {
        if (!id.equals(updatedProduct.getProductId())) return null;
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(id)) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        return null;
    }

    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}
