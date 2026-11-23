package co.sena.mercasena.flyweight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Cliente: registra miles de ítems compartiendo tipos. */
public class Bodega {

    private final FabricaTiposProducto fabrica = new FabricaTiposProducto();
    private final List<ItemInventario> items = new ArrayList<>();

    public ItemInventario registrar(String sku, int cantidad, String ubicacion,
                                    String categoria, String unidad, int porcentajeIva) {
        // Corrección #3: validar y obtener el tipo antes de agregar el ítem a la bodega
        TipoProducto tipo = fabrica.obtener(categoria, unidad, porcentajeIva);
        ItemInventario item = new ItemInventario(sku, cantidad, ubicacion, tipo);
        items.add(item);
        return item;
    }

    public List<ItemInventario> listarItems() {
        return Collections.unmodifiableList(items);
    }

    public int totalItems() {
        return items.size();
    }

    public int tiposDistintos() {
        return fabrica.cantidadTipos();
    }
}