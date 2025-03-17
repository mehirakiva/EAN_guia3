import java.util.Scanner;

class Paciente {
    int id;
    String nombre;
    int edad;
    String clinica;

    Paciente(int id, String nombre, int edad, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.clinica = clinica;
    }
}

class Nodo {
    Paciente paciente;
    Nodo siguiente;

    Nodo(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }
}

class CentralDePacientes {
    Nodo cabeza;

    CentralDePacientes() {
        this.cabeza = null;
    }

    public void agregarPaciente(int id, String nombre, int edad, String clinica) {
        Paciente paciente = new Paciente(id, nombre, edad, clinica);
        Nodo nuevoNodo = new Nodo(paciente);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
        System.out.println("Paciente agregado exitosamente.");
    }

    public void buscarPaciente(int id) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.paciente.id == id) {
                System.out.println("Paciente encontrado: ");
                System.out.println("ID: " + temp.paciente.id);
                System.out.println("Nombre: " + temp.paciente.nombre);
                System.out.println("Edad: " + temp.paciente.edad);
                System.out.println("Clínica: " + temp.paciente.clinica);
                return;
            }
            temp = temp.siguiente;
        }
        System.out.println("Paciente no encontrado.");
    }

    public void eliminarPaciente(int id) {
        if (cabeza == null) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        if (cabeza.paciente.id == id) {
            cabeza = cabeza.siguiente;
            System.out.println("Paciente eliminado exitosamente.");
            return;
        }

        Nodo temp = cabeza;
        while (temp.siguiente != null && temp.siguiente.paciente.id != id) {
            temp = temp.siguiente;
        }

        if (temp.siguiente == null) {
            System.out.println("Paciente no encontrado.");
        } else {
            temp.siguiente = temp.siguiente.siguiente;
            System.out.println("Paciente eliminado exitosamente.");
        }
    }

    public void mostrarPacientes() {
        if (cabeza == null) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Nodo temp = cabeza;
        System.out.println("Lista de pacientes:");
        while (temp != null) {
            System.out.println("ID: " + temp.paciente.id);
            System.out.println("Nombre: " + temp.paciente.nombre);
            System.out.println("Edad: " + temp.paciente.edad);
            System.out.println("Clínica: " + temp.paciente.clinica);
            System.out.println("-----------------------------");
            temp = temp.siguiente;
        }
    }
}

public class Desarrollo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CentralDePacientes sistema = new CentralDePacientes();

        System.out.println("UNIVERSIDAD EAN Estructuras de datos lineales enlazadas");

        System.out.println("========================================================");
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar un paciente");
            System.out.println("2. Buscar un paciente por ID");
            System.out.println("3. Eliminar un paciente por ID");
            System.out.println("4. Mostrar todos los pacientes");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del paciente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nombre del paciente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese edad del paciente: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese clínica del paciente: ");
                    String clinica = scanner.nextLine();
                    sistema.agregarPaciente(id, nombre, edad, clinica);
                    break;

                case 2:
                    System.out.print("Ingrese ID del paciente a buscar: ");
                    int idBuscar = scanner.nextInt();
                    sistema.buscarPaciente(idBuscar);
                    break;

                case 3:
                    System.out.print("Ingrese ID del paciente a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    sistema.eliminarPaciente(idEliminar);
                    break;

                case 4:
                    sistema.mostrarPacientes();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
