package com.kozyrev.simbirtraineeship.classes.ClassesTask7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Shop {

    private List<Product> products = new ArrayList<>();
    private List<Client> banList = new LinkedList<>();

    private Manager manager = new Manager("Иванов", "Иван", "Иванович");
    private Client client1 = new Client("Василий", "Беспалов", "Александрович");
    private Client client2 = new Client("Петр", "Петров", "Петрович");

    public void init(){
        manager.addProduct(new Product("Товар1", 45), products);
        manager.addProduct(new Product("Товар2", 156), products);
        manager.addProduct(new Product("Товар3", 0), products);

        client1.getOrder().addProduct(products.get(0));
        client1.getOrder().addProduct(products.get(1));

        client2.getOrder().addProduct(products.get(1));

        client1.getOrder().setPaid(true);
        manager.setOrderSaled(client1.getOrder(), true);

        client2.getOrder().setPaid(false);
        manager.addClientInBanList(client2, banList);
    }
}
