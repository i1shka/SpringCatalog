package com.example.SpringCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    AddProduct addProduct;

    @GetMapping("add_product")
    public String addProduct(String name, String category, String price, Model model){
    try {
//        System.out.println("получено word = " + word);
        addProduct.add(new Product(name, category, Integer.parseInt(price)));
        model.addAttribute("message", "Товар добавлен");
        System.out.println("productList = " + addProduct.productList);
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
        model.addAttribute("errorMsg", e.getMessage());
    }
        return "add_product";
    }

    @GetMapping("catalog")
    public String catalog(Model model){
        model.addAttribute("list", addProduct.productList);
        System.out.println("productList = " + addProduct.productList);
        return "catalog";
    }

    @PostMapping("save")
    public String save(Model model){
        model.addAttribute("list", addProduct.productList);
        try {
            addProduct.save();
            model.addAttribute("message", "сохранено");
            System.out.println("Файл сохранен");
        } catch (IOException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "catalog";
    }

    @PostMapping("load")
    public String load(Model model){
        try {
            addProduct.load();
            model.addAttribute("message", "загружено");
            System.out.println("Файл сохранен");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("list", addProduct.productList);
        return "catalog";
    }
}
