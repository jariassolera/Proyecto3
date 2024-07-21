/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avance1_grupo3;

/**
 *
 * @author stevenaraya
 */
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private Cliente cliente;
    private List<Producto> productos;
    private double total;

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getCantidad() >= cantidad) {
            producto.setCantidad(producto.getCantidad() - cantidad);
            productos.add(new Producto(producto.getNombre(), producto.getPrecio(), cantidad));
            total += producto.getPrecio() * cantidad;
        } else {
            System.out.println("Cantidad insuficiente en el inventario para el producto: " + producto.getNombre());
        }
    }

    public void generarFacturaFisica() {
        // Implementar la lógica para generar un archivo PDF con el desglose de la factura
        System.out.println("Factura física generada para el cliente: " + cliente.getNombre());
    }

    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "cliente=" + cliente +
                ", productos=" + productos +
                ", total=" + total +
                '}';
    }
}
