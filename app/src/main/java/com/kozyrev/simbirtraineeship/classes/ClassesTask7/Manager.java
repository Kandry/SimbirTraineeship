package com.kozyrev.simbirtraineeship.classes.ClassesTask7;

import java.util.List;

public class Manager extends Human{

    public Manager(String lastName, String firstName, String patronymic) {
        super(lastName, firstName, patronymic);
    }

    public void addProduct(Product product, List<Product> products){
        products.add(product);
        System.out.println("Товаровед " + this.toString() + " добавил товар " + product.getProductName());
    }

    public void setOrderSaled(Order order, boolean isSaled){
        order.setSaled(isSaled);
        System.out.println("Товаровед " + this.toString() + " зарегистрировал продажу товаров " + order.getProducts().toString());
    }

    public void addClientInBanList(Client client, List<Client> banList){
        if (!client.getOrder().isPaid()) {
            banList.add(client);
            System.out.println("Товаровед " + this.toString() + " добавил клиента " + client.toString() + " в черный список");
        }
    }
}
