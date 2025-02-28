package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;


    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateWithoutId() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        Product target = productIterator.next();
        assertEquals("1", target.getProductId());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    // Unit Test Edit
    @Test
    void testEditName() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = productRepository.findById("abc");
        editedProduct.setProductName("kopi");
        productRepository.update("abc", editedProduct);

        Product target = productRepository.findById("abc");
        assertEquals("kopi", target.getProductName());
    }

    @Test
    void testEditQuantity() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = productRepository.findById("abc");
        editedProduct.setProductQuantity(20);
        productRepository.update("abc", editedProduct);

        Product target = productRepository.findById("abc");
        assertEquals(20, target.getProductQuantity());
    }

    @Test
    void testEditAll() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = productRepository.findById("abc");
        editedProduct.setProductName("kopi");
        editedProduct.setProductQuantity(20);
        productRepository.update("abc", editedProduct);

        Product target = productRepository.findById("abc");
        assertEquals(20, target.getProductQuantity());
        assertEquals("kopi", target.getProductName());
    }

    @Test
    void testEditMoreThanOneProduct() {
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setProductId("abc");
        product1.setProductName("teh");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        product2.setProductId("def");
        product2.setProductName("kopi");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        Product editedProduct1 = productRepository.findById("abc");
        Product editedProduct2 = productRepository.findById("def");

        editedProduct1.setProductName("susu");
        editedProduct2.setProductName("jus");
        editedProduct1.setProductQuantity(5);
        editedProduct2.setProductQuantity(40);

        productRepository.update("abc", editedProduct1);
        productRepository.update("def", editedProduct2);

        Product target1 = productRepository.findById("abc");
        Product target2 = productRepository.findById("def");

        assertEquals("susu", target1.getProductName());
        assertEquals("jus", target2.getProductName());
        assertEquals(5, target1.getProductQuantity());
        assertEquals(40, target2.getProductQuantity());
    }

    @Test
    void testEditDifferentId() {  // Test editing product with another product with different id
        Product product1 = new Product();
        product1.setProductId("abc");
        product1.setProductName("teh");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("def");
        product2.setProductName("kopi");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        Product editedProduct = productRepository.findById("def");
        editedProduct.setProductName("jus");
        productRepository.update("abc", editedProduct);  // Seharusnya tidak bisa diedit karena editedProduct punya id "def"

        Product target = productRepository.findById("abc");

        System.out.println(target.getProductId());
        System.out.println(target.getProductName());
        System.out.println(target.getProductQuantity());

        assertEquals("teh", target.getProductName());
        assertEquals(10, target.getProductQuantity());
    }

    @Test
    void testEditNonExistingProduct() {
        Product product1 = new Product();


    }

    // Unit Test Delete
    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("teh");
        product.setProductQuantity(10);
        productRepository.create(product);
        productRepository.delete(product.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonExistingProduct() {
        Product product = new Product();
        product.setProductId("aaaaa");
        product.setProductName("aqua");
        product.setProductQuantity(0);

        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext(), "Repository seharusnya tetap kosong setelah mencoba menghapus produk yang tidak ada");
    }

    @Test
    void testDeleteOneProduct() {
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setProductId("abc");
        product1.setProductName("teh");
        product1.setProductQuantity(10);
        product2.setProductId("def");
        product2.setProductName("kopi");
        product2.setProductQuantity(20);

        productRepository.create(product1);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());
        Iterator<Product> iterator = productRepository.findAll();

        assertTrue(iterator.hasNext());
        Product target = iterator.next();
        assertEquals("kopi", target.getProductName());

        Product targetNotExist = productRepository.findById("abc");
        assertNull(targetNotExist);

    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        product1.setProductId("abc");
        product1.setProductName("teh");
        product1.setProductQuantity(10);
        product2.setProductId("def");
        product2.setProductName("kopi");
        product2.setProductQuantity(20);
        product3.setProductId("ghi");
        product3.setProductName("jus");
        product3.setProductQuantity(50);
        productRepository.create(product1);
        productRepository.create(product2);
        productRepository.create(product3);
        Iterator<Product> products = productRepository.findAll();

        Product target1 = products.next();
        assertEquals("teh", target1.getProductName());
        assertEquals(10, target1.getProductQuantity());
        assertEquals("abc", target1.getProductId());

        Product target2 = products.next();
        assertEquals("kopi", target2.getProductName());
        assertEquals(20, target2.getProductQuantity());
        assertEquals("def", target2.getProductId());

        Product target3 = products.next();
        assertEquals("jus", target3.getProductName());
        assertEquals(50, target3.getProductQuantity());
        assertEquals("ghi", target3.getProductId());


    }
}
