package br.edu.utfpr.espolios;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.ShardType;
import br.edu.utfpr.espolios.service.CRUD.FragmentoChaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EspoliosApplication {

    @Autowired
    private FragmentoChaveService fragmentoChaveService;

    public static void main(String[] args) {
        SpringApplication.run(EspoliosApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("Adicionando fragmentos de chave...");
        fragmentoChaveService.save(new FragmentoChave(12, ShardType.COMMON));
        fragmentoChaveService.save(new FragmentoChave(9, ShardType.RARE));
        fragmentoChaveService.save(new FragmentoChave(6, ShardType.EPIC));
        fragmentoChaveService.save(new FragmentoChave(3, ShardType.LEGENDARY));

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();

        System.out.println("Adicionando mais 3 fragmentos de chave as chaves comum...");
        fragmentoChaveService.increment(ShardType.COMMON, 3);

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();

        System.out.println("Removendo os 3 fragmentos de chave comum...");
        fragmentoChaveService.decrement(ShardType.COMMON, 3);

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();

    }

}
