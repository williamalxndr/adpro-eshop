package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BaseController<T> {
    String createPage(Model model);
    String createPost(T item, Model model);
    String listPage(Model model);
    String editPage(@PathVariable String id, Model model);
    String editPost(@ModelAttribute T item, Model model);
    String delete(@RequestParam("id") String id);
}
