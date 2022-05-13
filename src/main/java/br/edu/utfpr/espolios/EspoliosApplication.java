package br.edu.utfpr.espolios;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.CRUD.ChaveService;
import br.edu.utfpr.espolios.service.CRUD.FragmentoChaveService;
import br.edu.utfpr.espolios.service.ForjarChaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EspoliosApplication {

    @Autowired
    private FragmentoChaveService fragmentoChaveService;

    @Autowired
    private ChaveService chaveService;

    @Autowired
    private ForjarChaveService forjarChaveService;

    public static void main(String[] args) {
        SpringApplication.run(EspoliosApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("Adicionando fragmentos de chave...");
        fragmentoChaveService.save(new FragmentoChave(12, Rarity.COMMON));
        fragmentoChaveService.save(new FragmentoChave(9, Rarity.RARE));
        fragmentoChaveService.save(new FragmentoChave(6, Rarity.EPIC));
        fragmentoChaveService.save(new FragmentoChave(3, Rarity.LEGENDARY));

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();

        System.out.println("Adicionando mais 3 fragmentos de chave as chaves comum...");
        fragmentoChaveService.increment(Rarity.COMMON, 3);

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();

        System.out.println("Removendo os 3 fragmentos de chave comum...");
        fragmentoChaveService.decrement(Rarity.COMMON, 3);

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();

        System.out.println("Forjando chaves do tipo comum...");
        forjarChaveService.forjarChave(Rarity.COMMON, 1);

        System.out.println("Listando chaves...");
        chaveService.logKeys();

        System.out.println("Forjando chaves do tipo raro...");
        forjarChaveService.forjarChave(Rarity.RARE, 1);

        System.out.println("Listando chaves...");
        chaveService.logKeys();

        System.out.println("Forjando todas as chaves...");
        forjarChaveService.forjarTodasChaves(Rarity.COMMON);
        forjarChaveService.forjarTodasChaves(Rarity.RARE);
        forjarChaveService.forjarTodasChaves(Rarity.EPIC);
        forjarChaveService.forjarTodasChaves(Rarity.LEGENDARY);

        System.out.println("Listando chaves...");
        chaveService.logKeys();

        System.out.println("Listando fragmentos de chave...");
        fragmentoChaveService.logKeys();


    }

}
