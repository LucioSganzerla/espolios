package br.edu.utfpr.espolios;

import br.edu.utfpr.espolios.models.Bau;
import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.Inventario;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.AbrirBauService;
import br.edu.utfpr.espolios.service.CRUD.*;
import br.edu.utfpr.espolios.service.ForjarChaveService;
import br.edu.utfpr.espolios.service.ReSortearService;
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

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ReSortearService reSortearService;

    public static void main(String[] args) {
        SpringApplication.run(EspoliosApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        // Inventario do usuario "Logado"
        Inventario inventario = null;

        log.info("Criando um inventario para o usuario");
        inventario = inventarioService.createInventario();
        log.info("Inventario criado com sucesso");

        log.info("Adicionando fragmentos de chave...");
        fragmentoChaveService.save(new FragmentoChave(15, Rarity.COMMON));
        fragmentoChaveService.save(new FragmentoChave(15, Rarity.RARE));
        fragmentoChaveService.save(new FragmentoChave(15, Rarity.EPIC));
        fragmentoChaveService.save(new FragmentoChave(15, Rarity.LEGENDARY));

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

        log.info("Gerando 5 baus de cada para o usu??rio...");
        bauService.save(new Bau(5, Rarity.COMMON));
        bauService.save(new Bau(5, Rarity.RARE));
        bauService.save(new Bau(5, Rarity.EPIC));
        bauService.save(new Bau(5, Rarity.LEGENDARY));

        log.info("Listando baus...");
        bauService.log();

        log.info("Abrindo 10 Baus Comuns");
        abrirBauService.abrirBau(inventario, Rarity.COMMON, 10);

        log.info("Abrindo 1 Bau Epico");
        abrirBauService.abrirBau(inventario, Rarity.EPIC, 1);

        log.info("Listando baus...");
        bauService.log();

        log.info("Listando chaves...");
        chaveService.log();

        log.info("Listando inventarios...");
        inventarioService.log();

        log.info("Abrindo todas as chaves do inventario {}", inventario.getId());
        abrirBauService.abrirBau(inventario, Rarity.COMMON, 2);
        log.info("Loots obtidos:");
        inventarioService.log(inventario);

        log.info("Abrindo todas as chaves possiveis do inventario {}", inventario.getId());
        abrirBauService.abrirPossiveis(inventario, Rarity.COMMON);
        abrirBauService.abrirPossiveis(inventario, Rarity.RARE);
        abrirBauService.abrirPossiveis(inventario, Rarity.EPIC);
        abrirBauService.abrirPossiveis(inventario, Rarity.LEGENDARY);

        log.info("Listando baus...");
        bauService.log();

        log.info("Listando chaves...");
        chaveService.log();

        log.info("Listando inventarios...");
        inventarioService.log();

        inventario = inventarioService.getRepository().findById(inventario.getId()).get();

        log.info("Trocando todos os loots...");
        while (inventario.getLoots().size() >= 3) {
            inventario = reSortearService.resortear(inventario, inventario.getLoots().get(0), inventario.getLoots().get(1), inventario.getLoots().get(2));
        }

        log.info("Listando inventarios...");
        inventarioService.log();
    }

}

