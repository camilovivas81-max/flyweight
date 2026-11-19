package co.sena.mercasena.flyweight;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Contexto: estado extrínseco + referencia al flyweight. */
public class ItemInventario {

    private final String sku;
    private final int cantidad;
    private final String ubicacion;
    private final TipoProducto tipo;

    public ItemInventario(String sku, int cantidad, String ubicacion, TipoProducto tipo) {
        this.sku = sku;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }

    public BigDecimal valorIva(BigDecimal precio) {
        return precio.multiply(BigDecimal.valueOf(tipo.getPorcentajeIva()))
                .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
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
