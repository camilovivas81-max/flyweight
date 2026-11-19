package co.sena.mercasena.flyweight;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FlyweightTest {

    @Test
    void mismoTipoEsElMismoObjeto() {
        FabricaTiposProducto f = new FabricaTiposProducto();
        assertSame(f.obtener("Granos", "kg", 5), f.obtener("Granos", "kg", 5));
        assertEquals(1, f.cantidadTipos());
    }

    @Test
    void tiposDiferentesSonObjetosDiferentes() {
        FabricaTiposProducto f = new FabricaTiposProducto();
        assertNotSame(f.obtener("Granos", "kg", 5), f.obtener("Granos", "kg", 19));
    }

    @Test
    void milItemsCompartenDosTipos() {
        Bodega b = new Bodega();
        for (int i = 0; i < 1000; i++) {
            String categoria = i % 2 == 0 ? "Granos" : "Aseo";
            b.registrar("SKU-" + i, 10, "Estante " + (i % 20), categoria, "und", 19);
        }
        assertEquals(1000, b.totalItems());
        assertEquals(2, b.tiposDistintos());
    }

    @Test
    void calculaElIva() {
        Bodega b = new Bodega();
        ItemInventario item = b.registrar("SKU-1", 1, "A1", "Aseo", "und", 19);
        assertEquals(0, new BigDecimal("1900").compareTo(item.valorIva(new BigDecimal("10000"))));
    }
}
