package co.sena.mercasena.flyweight;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Contexto: estado extrínseco + referencia al flyweight. */
public class ItemInventario {

    private final String sku;
    private final int cantidad;
    private final String ubicacion;
    private final TipoProducto tipo;

    public ItemInventario(String sku, int cantidad, String ubicacion, TipoProducto tipo) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("El SKU no puede estar vacío.");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.sku = sku.trim();
        this.cantidad = cantidad;
        this.ubicacion = (ubicacion == null || ubicacion.isBlank()) ? "Sin asignar" : ubicacion.trim();
        this.tipo = Objects.requireNonNull(tipo, "El tipo de producto no puede ser nulo.");
    }

    public BigDecimal valorIva(BigDecimal precio) {
        Objects.requireNonNull(precio, "El precio no puede ser nulo.");
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        // Corrección #2: escala de 2 decimales en el cálculo y redondeo HALF_UP
        return precio.multiply(BigDecimal.valueOf(tipo.getPorcentajeIva()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public String getSku() {
        return sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public TipoProducto getTipo() {
        return tipo;
    }
}