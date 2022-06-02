package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.generics.BaseService;
import br.edu.utfpr.espolios.models.Inventario;
import br.edu.utfpr.espolios.models.Loot;
import br.edu.utfpr.espolios.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventarioService extends BaseService<Inventario> {

    private final InventarioRepository repository;

    @Override
    public InventarioRepository getRepository() {
        return repository;
    }

    public Inventario createInventario() {
        return repository.saveAndFlush(new Inventario());
    }

    public List<Loot> addLoot(Inventario inventario, Loot loot) {
        inventario.addLoot(loot);
        return repository.saveAndFlush(inventario).getLoots();
    }

    public List<Loot> removeLoot(Inventario inventario, Loot loot) {
        inventario.removeLoot(loot);
        return repository.saveAndFlush(inventario).getLoots();
    }

    public void log(Inventario inventario) {
        log.info("Inventario: " + inventario.getId());
        for (Loot loot : inventario.getLoots()) {
            log.info("Loot: " + loot.getId() + " - Valor: R$ " + String.format("%.2f", loot.getValorMonetario()));
        }
        log.info("Quantidade de loots: " + inventario.getLoots().size() + " - Valor monetario acumulado: R$" +
                String.format("%.2f", inventario.getLoots().stream().mapToDouble(Loot::getValorMonetario).sum()));
    }

    public void log() {
        List<Inventario> inventarios = getRepository().findAll();
        for (Inventario inventario : inventarios) {
            log(inventario);
        }
    }

}
