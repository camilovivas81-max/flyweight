package co.sena.mercasena.flyweight;

/** Flyweight: estado intrínseco compartido. */
public final class TipoProducto {

    private final String categoria;
    private final String unidad;
    private int porcentajeIva;

    TipoProducto(String categoria, String unidad, int porcentajeIva) {
        this.categoria = categoria;
        this.unidad = unidad;
        this.porcentajeIva = porcentajeIva;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUnidad() {
        return unidad;
    }

    public int getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(int porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }
}
