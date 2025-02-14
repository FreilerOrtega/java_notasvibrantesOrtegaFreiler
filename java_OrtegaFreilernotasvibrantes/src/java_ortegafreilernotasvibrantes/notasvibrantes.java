/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java_ortegafreilernotasvibrantes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;

// Clase Cliente
class Cliente {
    int id;
    String nombre;
    String apellido;

    public Cliente(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + " " + apellido;
    }
}

// Clase Concierto
class Concierto {
    int id;
    String nombre;
    String artista;
    LocalDate fecha;
    String lugar;
    BigDecimal precioBase; 

    public Concierto(int id, String nombre, String artista, LocalDate fecha, String lugar, BigDecimal precioBase) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.precioBase = precioBase;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Artista: " + artista + ", Fecha: " + fecha + ", Lugar: " + lugar + ", Precio Base: $" + precioBase.toString() + " COP";
    }
}

// Clase Zona
class Zona {
    int id;
    String nombreZona;
    int capacidad;
    BigDecimal precioAdicional; 

    public Zona(int id, String nombreZona, int capacidad, BigDecimal precioAdicional) {
        this.id = id;
        this.nombreZona = nombreZona;
        this.capacidad = capacidad;
        this.precioAdicional = precioAdicional;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre Zona: " + nombreZona + ", Capacidad: " + capacidad + ", Precio Adicional: $" + precioAdicional.toString() + " COP";
    }
}

// Clase Ticket
class Ticket {
    int id;
    Cliente cliente;
    Concierto concierto;
    Zona zona;
    BigDecimal precioFinal; 
    LocalDate fechaCompra;

    public Ticket(int id, Cliente cliente, Concierto concierto, Zona zona) {
        this.id = id;
        this.cliente = cliente;
        this.concierto = concierto;
        this.zona = zona;
        this.precioFinal = concierto.precioBase.add(zona.precioAdicional); 
        this.fechaCompra = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Ticket ID: " + id + ", Cliente: [" + cliente + "], Concierto: [" + concierto + "], Zona: [" + zona + "], Precio Final: $" + precioFinal.toString() + " COP, Fecha Compra: " + fechaCompra;
    }
}

public class notasvibrantes {
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Concierto> conciertos = new ArrayList<>();
    static ArrayList<Zona> zonas = new ArrayList<>();
    static ArrayList<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarDatos();

        while (true) {
            System.out.println("\nBienvenido al sistema de gestión de tickets. Seleccione una opción:");
            System.out.println("1. Ver conciertos disponibles.");
            System.out.println("2. Registrar cliente.");
            System.out.println("3. Comprar ticket.");
            System.out.println("4. Ver tickets por cliente.");
            System.out.println("5. Cancelar ticket.");
            System.out.println("6. Salir.");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    listarConciertos();
                    break;
                case 2:
                    registrarCliente(scanner);
                    break;
                case 3:
                    comprarTicket(scanner);
                    break;
                case 4:
                    verTicketsPorCliente(scanner);
                    break;
                case 5:
                    cancelarTicket(scanner);
                    break;
                case 6:
                    System.out.println("Gracias por usar el sistema. ¡Adiós!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    static void inicializarDatos() {
        // Datos de prueba
        conciertos.add(new Concierto(1, "ay ombe ", "jorge celedon ", LocalDate.of(2025, 3, 15), "Estadio metropolitano", BigDecimal.valueOf(100000)));
        conciertos.add(new Concierto(2, "mor ", "feid ", LocalDate.of(2025, 4, 10), "atanasio girardot", BigDecimal.valueOf(120000)));

        zonas.add(new Zona(1, "VIP", 50, BigDecimal.valueOf(50000)));
        zonas.add(new Zona(2, "General", 200, BigDecimal.valueOf(0)));
        zonas.add(new Zona(3, "Platino", 30, BigDecimal.valueOf(20000)));
    }

    static void listarConciertos() {
        System.out.println("\nConciertos disponibles:");
        for (Concierto concierto : conciertos) {
            System.out.println(concierto);
        }
    }

    static void registrarCliente(Scanner scanner) {
        System.out.println("\nRegistro de cliente:");
        System.out.print("Ingrese ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        clientes.add(new Cliente(id, nombre, apellido));
        System.out.println("Cliente registrado exitosamente.");
    }

    static void comprarTicket(Scanner scanner) {
        System.out.println("\nCompra de ticket:");
        System.out.print("Ingrese ID del cliente: ");
        int idCliente = scanner.nextInt();
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        listarConciertos();
        System.out.print("Seleccione el ID del concierto: ");
        int idConcierto = scanner.nextInt();
        Concierto concierto = buscarConciertoPorId(idConcierto);
        if (concierto == null) {
            System.out.println("Concierto no encontrado.");
            return;
        }

        System.out.println("Zonas disponibles:");
        for (Zona zona : zonas) {
            System.out.println(zona);
        }
        System.out.print("Seleccione el ID de la zona: ");
        int idZona = scanner.nextInt();
        Zona zona = buscarZonaPorId(idZona);
        if (zona == null) {
            System.out.println("Zona no encontrada.");
            return;
        }

        Ticket ticket = new Ticket(tickets.size() + 1, cliente, concierto, zona);
        tickets.add(ticket);
        System.out.println("Ticket comprado exitosamente:");
        System.out.println(ticket);
    }

    static void verTicketsPorCliente(Scanner scanner) {
        System.out.print("\nIngrese ID del cliente: ");
        int idCliente = scanner.nextInt();
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Tickets del cliente:");
        for (Ticket ticket : tickets) {
            if (ticket.cliente.id == cliente.id) {
                System.out.println(ticket);
            }
        }
    }

    static void cancelarTicket(Scanner scanner) {
        System.out.print("\nIngrese ID del ticket a cancelar: ");
        int idTicket = scanner.nextInt();

        Ticket ticket = buscarTicketPorId(idTicket);
        if (ticket == null) {
            System.out.println("Ticket no encontrado.");
            return;
        }

        tickets.remove(ticket);
        System.out.println("Ticket cancelado exitosamente.");
    }

    static Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.id == id) {
                return cliente;
            }
        }
        return null;
    }

    static Concierto buscarConciertoPorId(int id) {
        for (Concierto concierto : conciertos) {
            if (concierto.id == id) {
                return concierto;
            }
        }
        return null;
    }

    static Zona buscarZonaPorId(int id) {
        for (Zona zona : zonas) {
            if (zona.id == id) {
                return zona;
            }
        }
        return null;
    }

    static Ticket buscarTicketPorId(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                return ticket;
            }
        }
        return null;
    }
}
