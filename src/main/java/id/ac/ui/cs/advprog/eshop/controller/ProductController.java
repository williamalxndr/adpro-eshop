package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController implements BaseController<Product> {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping("/create")
    public String createPage(Model model) {  // Menampilkan halaman create product
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Product product, Model model) {  // Menghandle form submission untuk create product
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {  // Menampilkan halaman yang berisi semua product
        List<Product> allProduct = service.findAll();
        model.addAttribute("products", allProduct);
        return "ProductList";
    }

    @GetMapping("/edit/{productId}")
    public String editPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Product product, Model model) {
        service.update(product.getProductId(), product);
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) {  // Menghandle button delete di halaman list product
        service.deleteById(id);
        return "redirect:list";
    }
}

@Controller
@RequestMapping("/car")
class CarController implements BaseController<Car> {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/create")
    public String createPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        List<Car> allCar = carService.findAll();
        model.addAttribute("cars", allCar);
        return "CarList";
    }

    @GetMapping("/edit/{carId}")
    public String editPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Car car, Model model) {
        carService.update(car.getCarId(), car);

        return "redirect:list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("carId") String carId) {
        carService.deleteById(carId);
        return "redirect:list";
    }
}