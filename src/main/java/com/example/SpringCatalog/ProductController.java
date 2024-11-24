package com.example.SpringCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

//    Добавление товара
    @GetMapping("add_product")
    public String addProduct(String id, String name, String category, String price, String description, Model model){
    try {
        productService.add(new Product(id, name, category, Integer.parseInt(price), description));
        model.addAttribute("message", "Товар добавлен");
        System.out.println("productList = " + productService.productList);
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
        model.addAttribute("errorMsg", e.getMessage());
    }
        return "/add_product";
    }

//    Вывод списка товаров в каталог
    @GetMapping("catalog")
    public String catalog(Model model){
        model.addAttribute("list", productService.productList);
        return "catalog";
    }

//    переход на страницу товара из католога
    @GetMapping("product")
    public String product(String id, Model model){
        Product p=productService.getById(id);
        if (p!=null) {
        model.addAttribute("prod", p);
        return "product";}
        else {
            model.addAttribute("list", productService.productList);
            model.addAttribute("message", "Товар не найден");
            return "catalog";
        }
    }

//    Удаление товара
    @GetMapping("/delete")
    public String delete(String id, Model model){
        try {
            productService.deleteById(id);
            model.addAttribute("message", "Товар удален");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("list", productService.productList);
        return ("/catalog");
    }

//    Переход на страницу редактирования из карточки товара
    @GetMapping("/edit")
    public String edit(String id, Model model){
        model.addAttribute("prod", productService.getById(id));
        return ("/edit_product");
    }

//    Редактирование товара
    @GetMapping("/edit_prod")
    public String edit_prod(String id, String name, String category, String price, String description, Model model){
        try {
            productService.edit_prod(new Product(id, name, category, Integer.parseInt(price), description));
            model.addAttribute("message", "Товар изменен");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("prod", productService.getById(id));
        return ("/product");
    }

//    Сохранение каталога в файл
    @PostMapping("save")
    public String save(Model model){
        model.addAttribute("list", productService.productList);
        try {
            productService.save();
            model.addAttribute("message", "сохранено");
            System.out.println("Файл сохранен");
        } catch (IOException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "catalog";
    }

//    Загрузка каталога из файла
    @PostMapping("/load")
    public String load(Model model){
        model.addAttribute("list", productService.productList);
        try {
            productService.load();
            model.addAttribute("message", "загружено");
            System.out.println("Файл загружен");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return ("/catalog");
    }
}
