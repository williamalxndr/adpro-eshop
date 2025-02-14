package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private int idIterator = 0;

    public Product create(Product product) {
        idIterator++;
        product.setProductId(idIterator);
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(int id) {  // Return product dengan id [id], jika tidak ditemukan return null
        Iterator<Product> productIterator = findAll();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.getProductId() == id) return product;
        }
        return null;
    }

    public void set(int id, Product product) {  // Mengubah product dengan id [id] menjadi [product]
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId() == id) {
                productData.set(i, product);
                return;
            }
        }
    }

    public void delete(Product product) {
        productData.remove(product);
    }
}
