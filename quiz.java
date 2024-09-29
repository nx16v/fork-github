import java.util.Scanner;

public class quiz {

    // Variables globales
    static String[] nombres; // Almacena los nombres de los productos
    static int[] cantidades; // Almacena las cantidades de cada producto
    static double[] precios; // Almacena los precios por unidad de cada producto
    static int totalProductos; // Número total de productos

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicialización del inventario con 5 productos 
        totalProductos = 10;
        nombres = new String[totalProductos];
        cantidades = new int[totalProductos];
        precios = new double[totalProductos];

        // Entrada de datos
        for (int i = 0; i < totalProductos; i++) {
            System.out.print("Ingrese el nombre del producto " + (i + 1) + ": ");
            nombres[i] = sc.nextLine();

            System.out.print("Ingrese la cantidad en inventario de " + nombres[i] + ": ");
            cantidades[i] = sc.nextInt();

            System.out.print("Ingrese el precio por unidad de " + nombres[i] + ": ");
            precios[i] = sc.nextDouble();
            sc.nextLine(); // Limpiar el buffer
        }

        // Lógica del cálculo del valor total del inventario 
        double valorTotalInventario = calcularValorTotalInventario();

        // Mostrar el reporte final
        generarReporteFinal(valorTotalInventario);

        // Opciones adicionales
        while (true) {
            System.out.println("\n¿Qué desea hacer?");
            System.out.println("1. Agregar un producto nuevo");
            System.out.println("2. Actualizar cantidad de un producto");
            System.out.println("3. Salir");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    agregarProducto(sc);
                    valorTotalInventario = calcularValorTotalInventario();
                    generarReporteFinal(valorTotalInventario);
                    break;
                case 2:
                    actualizarCantidad(sc);
                    valorTotalInventario = calcularValorTotalInventario();
                    generarReporteFinal(valorTotalInventario);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    sc.close(); // Cerrar el Scanner
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Función para calcular el valor total del inventario
    static double calcularValorTotalInventario() {
        double valorTotal = 0;
        for (int i = 0; i < totalProductos; i++) {
            valorTotal += cantidades[i] * precios[i];
        }
        return valorTotal;
    }

    // Función para generar el reporte final
    static void generarReporteFinal(double valorTotalInventario) {
        System.out.println("\nReporte Final:");
        for (int i = 0; i < totalProductos; i++) {
            double valorTotalProducto = cantidades[i] * precios[i];
            System.out.println("Producto: " + nombres[i] + " - Cantidad: " + cantidades[i] + " - Precio por unidad: " + precios[i] + " - Valor total: " + valorTotalProducto);
        }
        System.out.println("Valor total del inventario: " + valorTotalInventario);
    }

    // Función para agregar un nuevo producto
    static void agregarProducto(Scanner sc) {
        System.out.println("\n--- Agregar nuevo producto ---");

        // Aumentar el total de productos
        totalProductos++;
        
        // Crear nuevos arrays con un tamaño incrementado
        String[] nuevosNombres = new String[totalProductos];
        int[] nuevasCantidades = new int[totalProductos];
        double[] nuevosPrecios = new double[totalProductos];

        // Copiar los datos anteriores
        for (int i = 0; i < totalProductos - 1; i++) {
            nuevosNombres[i] = nombres[i];
            nuevasCantidades[i] = cantidades[i];
            nuevosPrecios[i] = precios[i];
        }

        // Agregar el nuevo producto
        System.out.print("Ingrese el nombre del nuevo producto: ");
        nuevosNombres[totalProductos - 1] = sc.nextLine(); // Cambiado a nextLine

        System.out.print("Ingrese la cantidad en inventario: ");
        nuevasCantidades[totalProductos - 1] = sc.nextInt();

        System.out.print("Ingrese el precio por unidad: ");
        nuevosPrecios[totalProductos - 1] = sc.nextDouble();
        sc.nextLine(); // Limpiar el buffer

        // Asignar los nuevos arrays a los arrays originales
        nombres = nuevosNombres;
        cantidades = nuevasCantidades;
        precios = nuevosPrecios;
    }

    // Función para actualizar la cantidad de un producto existente
    static void actualizarCantidad(Scanner sc) {
        System.out.println("\n--- Actualizar cantidad de producto ---");
        System.out.print("Ingrese el nombre del producto a actualizar: ");
        String producto = sc.nextLine(); 
        boolean encontrado = false;

        for (int i = 0; i < totalProductos; i++) {
            if (nombres[i].equalsIgnoreCase(producto)) {
                System.out.print("Ingrese la nueva cantidad para " + nombres[i] + ": ");
                cantidades[i] = sc.nextInt();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }
}
