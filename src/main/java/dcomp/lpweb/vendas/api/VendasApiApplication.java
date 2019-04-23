package dcomp.lpweb.vendas.api;

import dcomp.lpweb.vendas.api.model.Categoria;
import dcomp.lpweb.vendas.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class VendasApiApplication {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(VendasApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner() {


        return args -> {

            List<Categoria> categorias = Arrays.asList(new Categoria("Informática"),
                                                       new Categoria("Eletrônica") );


            categoriaRepository.saveAll(categorias );


        };
    }

}
