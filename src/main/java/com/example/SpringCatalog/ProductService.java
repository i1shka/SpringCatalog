package com.example.SpringCatalog;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    List<Product> productList=new ArrayList<>();

    public void add(Product p){
        productList.add(p);
    }

    public Product getById(String id){
        return productList.stream().filter(x->x.id.equals(id)).findFirst().orElse(null);
    }

    public void deleteById(String id) {
            productList.removeIf(i -> i.getId().equals(id));
    }

    public void edit_prod(Product p) {
        for (Product i:productList) {
            if (i.getId().equals(p.id)) {
                productList.set(productList.indexOf(i),p);
            }
        }
    }

    public void save() throws IOException {
        String fname = "product_list.txt";
        List<String> lines = productList.stream().map(y -> y.getId()+";"+y.getName()+";"+y.getCategory()+";"+y.getPrice()+";"+y.getDescription()).toList();
        Path file = Paths.get(fname);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void load() throws Exception {
        String fname = "product_list.txt";
        Path file = Paths.get(fname);
        List<String> lines =Files.readAllLines(file);
        lines.stream().forEach(z->{
            String[] mas = z.split(";");
            add(new Product(mas[0], mas[1], mas[2], Integer.parseInt(mas[3]), mas[4]));});
    }
}



