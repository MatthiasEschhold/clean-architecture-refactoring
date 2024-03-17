package de.arkem.clean.arc.refactoring.demo;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataCrudRepository;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
