package com.example.SpringCatalog;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddProduct {
    List<Product> productList=new ArrayList<>();

    public void add(Product p){
        productList.add(p);
    }


    public void save() throws IOException {
        String fname = "product_list.txt";
        List<String> lines = productList.stream().map(y -> y.getName()+";"+y.getCategory()+";"+y.getPrice()).toList();
        Path file = Paths.get(fname);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void load() throws Exception {
        String fname = "product_list.txt";
        Path file = Paths.get(fname);
        List<String> lines =Files.readAllLines(file);
        lines.stream().forEach(z->{
            String[] mas = z.split(";");
            add(new Product(mas[0], mas[1], Integer.parseInt(mas[2])));});
    }
}



