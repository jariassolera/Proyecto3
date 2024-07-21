/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package avance1_grupo3;

/**
 *
 * @author stevenaraya
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    private static Usuario usuarioAutenticado;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Crear un usuario administrador para iniciar sesión
        Usuario admin = new Usuario("admin", "pass123");
        
        // Autenticación de usuario
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if (Usuario.iniciarSesion(admin, nombreUsuario, contrasena)) {
            usuarioAutenticado = admin;
            System.out.println("¡Bienvenido, " + usuarioAutenticado.getNombreUsuario() + "!");
            
            // Menú principal
            int opcion;
            do {
                System.out.println("\n--- Menú Principal ---");
                System.out.println("1. Registrar Cliente");
                System.out.println("2. Registrar Producto");
                System.out.println("3. Crear Factura");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        registrarCliente(scanner);
                        break;
                    case 2:
                        registrarProducto(scanner);
                        break;
                    case 3:
                        crearFactura(scanner);
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 4);
        } else {
            System.out.println("Credenciales incorrectas. Acceso denegado.");
        }

        scanner.close();
    }

    private static void registrarCliente(Scanner scanner) {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección del cliente: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, direccion, telefono);
        clientes.add(cliente);
        System.out.println("Cliente registrado exitosamente.");
    }

    private static void registrarProducto(Scanner scanner) {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Cantidad del producto: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = new Producto(nombre, precio, cantidad);
        productos.add(producto);
        System.out.println("Producto registrado exitosamente.");
    }

    private static void crearFactura(Scanner scanner) {
        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        Cliente cliente = buscarCliente(nombreCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Factura factura = new Factura(cliente);
        String continuar = null;
        do {
            System.out.print("Nombre del producto: ");
            String nombreProducto = scanner.nextLine();
            Producto producto = buscarProducto(nombreProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            factura.agregarProducto(producto, cantidad);
            System.out.print("¿Desea agregar otro producto a la factura? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("Factura creada exitosamente.");
        System.out.println(factura);
        factura.generarFacturaFisica();
    }

    private static Cliente buscarCliente(String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    private static Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }
}

