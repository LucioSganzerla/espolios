package br.edu.utfpr.espolios;

import br.edu.utfpr.espolios.models.Bau;
import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.AbrirBauService;
import br.edu.utfpr.espolios.service.CRUD.BauService;
import br.edu.utfpr.espolios.service.CRUD.ChaveService;
import br.edu.utfpr.espolios.service.CRUD.FragmentoChaveService;
import br.edu.utfpr.espolios.service.ForjarChaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class EspoliosApplication {

    @Autowired
    private FragmentoChaveService fragmentoChaveService;

    @Autowired
    private ChaveService chaveService;

    @Autowired
    private ForjarChaveService forjarChaveService;

    @Autowired
    private BauService bauService;

    @Autowired
    private AbrirBauService abrirBauService;

    public static void main(String[] args) {
        SpringApplication.run(EspoliosApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        log.info("Adicionando fragmentos de chave...");
        fragmentoChaveService.save(new FragmentoChave(12, Rarity.COMMON));
        fragmentoChaveService.save(new FragmentoChave(9, Rarity.RARE));
        fragmentoChaveService.save(new FragmentoChave(6, Rarity.EPIC));
        fragmentoChaveService.save(new FragmentoChave(3, Rarity.LEGENDARY));

        log.info("Listando fragmentos de chave...");
        fragmentoChaveService.log();

        log.info("Adicionando mais 3 fragmentos de chave as chaves comum...");
        fragmentoChaveService.increment(Rarity.COMMON, 3);

        log.info("Listando fragmentos de chave...");
        fragmentoChaveService.log();

        log.info("Removendo os 3 fragmentos de chave comum...");
        fragmentoChaveService.decrement(Rarity.COMMON, 3);

        log.info("Listando fragmentos de chave...");
        fragmentoChaveService.log();

        log.info("Forjando chaves do tipo comum...");
        forjarChaveService.forjarChave(Rarity.COMMON, 1);

        log.info("Listando chaves...");
        chaveService.log();

        log.info("Forjando chaves do tipo raro...");
        forjarChaveService.forjarChave(Rarity.RARE, 1);

        log.info("Listando chaves...");
        chaveService.log();

        log.info("Forjando todas as chaves...");
        forjarChaveService.forjarTodasChaves(Rarity.COMMON);
        forjarChaveService.forjarTodasChaves(Rarity.RARE);
        forjarChaveService.forjarTodasChaves(Rarity.EPIC);
        forjarChaveService.forjarTodasChaves(Rarity.LEGENDARY);

        log.info("Listando chaves...");
        chaveService.log();

        log.info("Listando fragmentos de chave...");
        fragmentoChaveService.log();

        log.info("Gerando 5 baus de cada para o usuário...");
        bauService.save(new Bau(5, Rarity.COMMON));
        bauService.save(new Bau(5, Rarity.RARE));
        bauService.save(new Bau(5, Rarity.EPIC));
        bauService.save(new Bau(5, Rarity.LEGENDARY));

        log.info("Listando baus...");
        bauService.log();

        log.info("Abrindo baus...");
        abrirBauService.abrirBau(Rarity.COMMON, 10);
        abrirBauService.abrirBau(Rarity.COMMON, 2);
        abrirBauService.abrirBau(Rarity.RARE, 3);
        abrirBauService.abrirBau(Rarity.EPIC, 2);
        abrirBauService.abrirBau(Rarity.LEGENDARY, 1);

        log.info("Listando baus...");
        bauService.log();

        log.info("Listando chaves...");
        chaveService.log();

    }

}
