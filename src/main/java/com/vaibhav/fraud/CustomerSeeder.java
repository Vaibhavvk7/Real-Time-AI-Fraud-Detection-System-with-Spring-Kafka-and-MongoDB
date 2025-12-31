package com.vaibhav.fraud;

import com.vaibhav.fraud.model.Customer;
import com.vaibhav.fraud.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerSeeder implements CommandLineRunner {

    private final CustomerRepository repo;

    public CustomerSeeder(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
	System.out.println("CustomerSeeder running...");
    	System.out.println("Current customer count = " + repo.count());
        if (repo.count() > 0) {
            return; // already seeded
        }

        Customer c1 = new Customer();
        c1.setCustomerId("user-1");
        c1.setName("Alice");
        c1.setTypicalMerchants(List.of("GroceryMart", "CoffeeShop"));
        c1.setAvgAmount(25);

        Customer c2 = new Customer();
        c2.setCustomerId("user-2");
        c2.setName("Bob");
        c2.setTypicalMerchants(List.of("GasStation", "Restaurant"));
        c2.setAvgAmount(40);

        Customer c3 = new Customer();
        c3.setCustomerId("user-3");
        c3.setName("Charlie");
        c3.setTypicalMerchants(List.of("ElectronicsStore"));
        c3.setAvgAmount(200);

        repo.saveAll(List.of(c1, c2, c3));

        System.out.println("👤 Seeded customer profiles");
    }
}

