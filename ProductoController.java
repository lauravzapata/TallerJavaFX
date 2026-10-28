package co.edu.uniquindio.poo.empresajfx.controller;

import co.edu.uniquindio.poo.empresajfx.model.Empresa;
import co.edu.uniquindio.poo.empresajfx.model.Producto;

import java.util.Collection;

public class ProductoController {
    Empresa empresa;

    public ProductoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean crearProducto(Producto producto) {
        return empresa.agregarProducto(producto);
    }

    public Collection<Producto> obtenerListaProductos() {
        return empresa.getProductos();
    }

    public boolean eliminarProducto(String codigo) {
        return empresa.eliminarProducto(codigo);
    }

    public boolean actualizarProducto(String codigo, Producto producto) {
        return empresa.actualizarProducto(codigo, producto);
    }
}
