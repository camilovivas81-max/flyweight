package co.sena.mercasena.flyweight;

import java.util.HashMap;
import java.util.Map;

/** Fábrica de flyweights: reutiliza instancias de TipoProducto. */
public class FabricaTiposProducto {

    private final Map<String, TipoProducto> cache = new HashMap<>();

    public TipoProducto obtener(String categoria, String unidad, int porcentajeIva) {
        String clave = categoria + unidad + porcentajeIva;
        TipoProducto tipo = cache.get(clave);
        if (tipo == null) {
            tipo = new TipoProducto(categoria, unidad, porcentajeIva);
        }
        return tipo;
    }

    public int cantidadTipos() {
        return cache.size();
    }
}
