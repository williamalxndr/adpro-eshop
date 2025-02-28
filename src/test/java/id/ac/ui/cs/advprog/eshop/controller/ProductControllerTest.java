package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        var result = mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"))
                .andReturn();

        assertNotNull(result.getModelAndView());
        assertEquals("CreateProduct", result.getModelAndView().getViewName());
    }

    @Test
    void testCreateProductPost() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(new Product());

        var result = mockMvc.perform(post("/product/create")
                        .param("productName", "Test Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"))
                .andReturn();

        assertEquals("list", result.getResponse().getRedirectedUrl());
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product A");
        product1.setProductQuantity(5);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product B");
        product2.setProductQuantity(10);

        productList.add(product1);
        productList.add(product2);

        when(productService.findAll()).thenReturn(productList);

        var result = mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"))
                .andReturn();

        assertNotNull(result.getModelAndView());
        assertEquals("ProductList", result.getModelAndView().getViewName());
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Product A");
        product.setProductQuantity(5);

        when(productService.findById("1")).thenReturn(product);

        var result = mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"))
                .andReturn();

        assertNotNull(result.getModelAndView());
        assertEquals("EditProduct", result.getModelAndView().getViewName());
    }

    @Test
    void testEditProductPost() throws Exception {
        var result = mockMvc.perform(post("/product/edit/1")
                        .param("updatedName", "Updated Product")
                        .param("updatedQuantity", "15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"))
                .andReturn();

        assertEquals("/product/list", result.getResponse().getRedirectedUrl());
    }

    @Test
    void testDeleteProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Product A");
        product.setProductQuantity(5);

        doNothing().when(productService).deleteById(any(String.class));

        var result = mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"))
                .andReturn();

        assertEquals(302, result.getResponse().getStatus());
    }

    @Test
    void testCreateProductObject() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Tisu");
        product.setProductQuantity(20);

        assertEquals("1", product.getProductId());
        assertEquals("Tisu", product.getProductName());
        assertEquals(20, product.getProductQuantity());
    }
}
