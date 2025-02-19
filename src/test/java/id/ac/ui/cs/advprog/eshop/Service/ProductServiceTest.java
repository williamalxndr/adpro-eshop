package id.ac.ui.cs.advprog.eshop.Service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    // Inject the mock into an instance of ProductServiceImpl
    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("aqua");
        product.setProductQuantity(50);
        service.create(product);

        System.out.println("Product Created: " + product.getProductName());

        if (service.productRepository != null) {
            System.out.println("Product Repository is not null");
            Iterator<Product> iterator = service.productRepository.findAll();
            try {if (iterator.hasNext()) System.out.println("Iterator not null");}
            catch (Exception e) {System.out.println("Iterator is null");}
        }

        List<Product> products = service.findAll();

        assertEquals("abc", products.get(0).getProductId());
        assertEquals("aqua", products.get(0).getProductId());
        assertEquals(50, products.get(0).getProductQuantity());

    }
}
