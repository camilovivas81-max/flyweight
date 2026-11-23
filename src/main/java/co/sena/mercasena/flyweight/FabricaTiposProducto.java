package co.sena.mercasena.flyweight;

import java.util.HashMap;
import java.util.Map;

/** Fábrica de flyweights: reutiliza instancias de TipoProducto. */
public class FabricaTiposProducto {

    private final Map<String, TipoProducto> cache = new HashMap<>();

    public TipoProducto obtener(String categoria, String unidad, int porcentajeIva) {
        validarDatosTipo(categoria, unidad, porcentajeIva);

        String clave = construirClave(categoria, unidad, porcentajeIva);
        TipoProducto tipo = cache.get(clave);
        if (tipo == null) {
            tipo = new TipoProducto(categoria.trim(), unidad.trim(), porcentajeIva);
            // Corrección #1: guardar el flyweight en el caché para reutilizarlo
            cache.put(clave, tipo);
        }
        return tipo;
    }

    private String construirClave(String categoria, String unidad, int porcentajeIva) {
        // Corrección #1: separador explícito y normalización para evitar colisiones
        return categoria.trim().toLowerCase() + "|" + unidad.trim().toLowerCase() + "|" + porcentajeIva;
    }

    private void validarDatosTipo(String categoria, String unidad, int porcentajeIva) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        if (unidad == null || unidad.isBlank()) {
            throw new IllegalArgumentException("La unidad de medida no puede estar vacía.");
        }
        if (porcentajeIva < 0 || porcentajeIva > 100) {
            throw new IllegalArgumentException("El porcentaje de IVA debe estar entre 0 y 100.");
        }
    }

    public int cantidadTipos() {
        return cache.size();
    }
}