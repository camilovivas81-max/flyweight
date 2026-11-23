package co.sena.mercasena.flyweight;

import java.util.Objects;

public class TipoProducto {

    private final String nombre;
    private final String categoria;
    private final String unidadMedida;
     int porcentajeIva;

    public TipoProducto(String nombre, String categoria, int unidadMedida) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tipo de producto no puede estar vacío.");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        if (unidadMedida == null || unidadMedida.isBlank()) {
            throw new IllegalArgumentException("La unidad de medida no puede estar vacía.");
        }
        this.nombre = nombre.trim();
        this.categoria = categoria.trim();
        this.unidadMedida = unidadMedida.trim();
    }

    // Recibe el estado extrínseco (precio, stock, pasillo) en el momento de renderizar/mostrar
    public String formatearDetalle(double precio, int cantidad, String ubicacion) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        // Corrección #1b: separación explícita con delimitadores claros en vez de concatenar sin espacio
        return String.format(
                "[%s | %s (%s)] - Precio: %.2f | Stock: %d | Ubicación: %s",
                nombre, categoria, unidadMedida, precio, cantidad, ubicacion
        );
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoProducto that)) return false;
        return nombre.equalsIgnoreCase(that.nombre)
                && categoria.equalsIgnoreCase(that.categoria)
                && unidadMedida.equalsIgnoreCase(that.unidadMedida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), categoria.toLowerCase(), unidadMedida.toLowerCase());
    }
}