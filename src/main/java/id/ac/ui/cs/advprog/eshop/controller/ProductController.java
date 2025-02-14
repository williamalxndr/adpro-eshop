package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {  // Menampilkan halaman create product
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {  // Menghandle form submission untuk create product
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {  // Menampilkan halaman yang berisi semua product
        List<Product> allProduct = service.findAll();
        model.addAttribute("products", allProduct);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable int id) {  // Menampilkan halaman untuk edit product
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@ModelAttribute Product product , Model model, @PathVariable int id, @RequestParam String updatedName, @RequestParam int updatedQuantity) {
        // Menghandle form submission untuk edit product
        product.setProductId(id);
        product.setProductName(updatedName);
        product.setProductQuantity(updatedQuantity);
        service.update(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductPage(Model model, @PathVariable int id) {  // Menghandle button delete di halaman list product
        Product product = service.findById(id);
        service.delete(product);
        return "redirect:/product/list";
    }
}
