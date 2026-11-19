package co.sena.mercasena.flyweight;

import java.util.ArrayList;
import java.util.List;

/** Cliente: registra miles de ítems compartiendo tipos. */
public class Bodega {

    private final FabricaTiposProducto fabrica = new FabricaTiposProducto();
    private final List<ItemInventario> items = new ArrayList<>();

    public ItemInventario registrar(String sku, int cantidad, String ubicacion,
                                    String categoria, String unidad, int porcentajeIva) {
        ItemInventario item = new ItemInventario(sku, cantidad, ubicacion,
                fabrica.obtener(categoria, unidad, porcentajeIva));
        items.add(item);
        return item;
    }

    public int totalItems() {
        return items.size();
    }

    public int tiposDistintos() {
        return fabrica.cantidadTipos();
    }
}
