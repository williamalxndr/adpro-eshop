package id.ac.ui.cs.advprog.eshop.Service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
//    @Mock
//    private ProductRepository productRepository;

    // Inject the mock into an instance of ProductServiceImpl
    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        ProductRepository productRepository = new ProductRepository();
        service.setRepository(productRepository);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("aqua");
        product.setProductQuantity(50);
        service.create(product);

        List<Product> products = service.findAll();

        assertEquals("abc", products.get(0).getProductId());
        assertEquals("aqua", products.get(0).getProductName());
        assertEquals(50, products.get(0).getProductQuantity());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(50);
        service.create(product);

        Product editedProduct = service.findById("abc");
        editedProduct.setProductQuantity(51);
        editedProduct.setProductName("kopi");

        service.update(editedProduct);

        Product target = service.findById("abc");

        assertEquals("kopi", target.getProductName());
        assertEquals(51, target.getProductQuantity());
    }

    @Test
    void testCheckEditDifferentIdProduct() {
        Product product1 = new Product();
        product1.setProductId("abc");
        product1.setProductName("teh");
        product1.setProductQuantity(50);
        service.create(product1);

        Product product2 = new Product();
        product2.setProductId("def");
        product2.setProductName("kopi");
        product2.setProductQuantity(20);
        service.create(product2);

        Product editedProduct = service.findById("def");
        editedProduct.setProductQuantity(30);
        editedProduct.setProductName("susu");
        service.update(editedProduct);

        Product target = service.findById("abc");
        assertEquals("teh", target.getProductName());
        assertEquals(50, target.getProductQuantity());
    }

    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(50);
        service.create(product);
        service.deleteById(product.getProductId());

        List<Product> products = service.findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void testDeleteNonExistingProduct() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(50);

        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("def");
        nonExistingProduct.setProductName("kopi");
        nonExistingProduct.setProductQuantity(20);

        service.create(product);

        service.deleteById(nonExistingProduct.getProductId());

        List<Product> products = service.findAll();
        assertFalse(products.isEmpty());
        assertEquals("abc", products.get(0).getProductId());
        assertEquals("teh", products.get(0).getProductName());
    }
}
