package com.kozyrev.simbirtraineeship.classes.ClassesTask7;

public class Client extends Human {

    private Order order = new Order();

    public Client(String lastName, String firstName, String patronymic) {
        super(lastName, firstName, patronymic);
    }

    public Order getOrder() {
        return order;
    }
}
