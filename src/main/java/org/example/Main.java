package org.example;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Faker faker = new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String title=faker.name().title();
        String suffix=faker.name().suffix();
        String address=faker.address().streetAddress();
        String city=faker.address().cityName();
        String country=faker.address().country();
        System.out.println(firstName+lastName);

    }




}