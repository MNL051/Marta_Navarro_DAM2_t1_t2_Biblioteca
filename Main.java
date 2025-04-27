import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca("Biblioteca central", "DirectorDeEjemplo");
        boolean continuar = true;

        while (continuar) {
            try {
                //Menú funcional
                System.out.println("\nMenú");
                System.out.println("1.Crear nuevo catálogo");
                System.out.println("2.Agregar libro a un catálogo");
                System.out.println("3.Buscar libro en un catálogo");
                System.out.println("4.Mostrar libros de un catálogo");
                System.out.println("5.Eliminar libro de un catálogo");
                System.out.println("6.Mostrar todos los catálogos");
                System.out.println("7.Salir");
                System.out.println("Escoja una ocpion: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {

                    case 1: //crea un nuevo catalogo
                        System.out.print("Ingrese el ID del catálogo nuevo: ");
                        int idCatalogo = scanner.nextInt();
                        scanner.nextLine();
                        Catalogo nuevoCatalogo = new Catalogo(idCatalogo);
                        biblioteca.agregarCatalogo(nuevoCatalogo); //el catalogo se añade a la biblioteca
                        System.out.println("Catálogo creado con éxito");
                        break;

                    case 2: //agregar un libro a un catálogo
                        biblioteca.mostrarCatalogos(); //muestra el id de los catálogos disponibles
                        System.out.print("Ingrese el ID del catálogo para agregar un libro: ");
                        int idLibroAgregar = scanner.nextInt(); //pide el id del catalogo
                        scanner.nextLine();
                        Catalogo catalogoAgregar = biblioteca.buscarCatalogo(idLibroAgregar);//busca el catalogo por el Id

                        if (catalogoAgregar != null) {

                            if (catalogoAgregar.numLibros() >= 4) { //comprueba que no se pase del limite de libros
                                throw new IllegalStateException("El catálogo está lleno");

                            }
                            System.out.print("Seleccione el tipo de libro (1.Terror, 2.Comedia, 3.Policiaca): ");
                            int tipo = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Autor: ");

                            String autor = scanner.nextLine();
                            System.out.print("Número de páginas: ");

                            int numPaginas = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("ISBN: ");
                            String ISBN = scanner.nextLine();

                            Libro nuevoLibro = null; //crea el libro segun el genero seleccionado
                            if (tipo == 1) { //De terror
                                System.out.print("Calificación: ");
                                double calificacion = scanner.nextDouble();
                                scanner.nextLine();
                                nuevoLibro = new LibroTerror(autor, numPaginas, ISBN, calificacion);

                            } else if (tipo == 2) { //De comedia
                                System.out.print("Tipo de humor: ");
                                String tipoHumor = scanner.nextLine();
                                nuevoLibro = new LibroComedia(autor, numPaginas, ISBN, tipoHumor);

                            } else if (tipo == 3) { //Policiaca
                                System.out.print("Trama (Misterio o Intriga): ");
                                String trama = scanner.nextLine();
                                System.out.print("Personajes principales: ");
                                String personajes = scanner.nextLine();
                                nuevoLibro = new LibroPoliciaca(autor, numPaginas, ISBN, trama, personajes);

                            } else {
                                System.out.println("Tipo de libro no válido");
                                break;
                            }
                            catalogoAgregar.agregarLibro(nuevoLibro); //agrega el libro al catalogo
                        } else {
                            System.out.println("Error, el catálogo no existe");
                        }
                        break;

                    case 3: //buscar libro por ISBN
                        biblioteca.mostrarCatalogos();
                        System.out.print("Ingrese el ID del catálogo para buscar el libro: ");
                        int idLibroBuscar = scanner.nextInt();
                        scanner.nextLine();
                        Catalogo catalogoBuscar = biblioteca.buscarCatalogo(idLibroBuscar);

                        if (catalogoBuscar != null) {
                            System.out.print("Ingrese el ISBN a buscar: ");
                            String isbnBuscar = scanner.nextLine(); //
                            Libro encontrado = catalogoBuscar.buscarLibro(isbnBuscar);
                            if (encontrado != null) {
                                System.out.println("Libro encontrado: " + encontrado);
                            }
                            else {
                                System.out.println("Libro no encontrado");
                            }
                        }
                        else {
                            System.out.println("Error, el catálogo no existe");
                        }
                        break;

                    case 4: //mostrar todos los catalogos
                        biblioteca.mostrarCatalogos();
                        System.out.print("Ingrese el ID del catálogo a mostrar: ");
                        int idMostrar = scanner.nextInt();
                        scanner.nextLine();
                        Catalogo catalogoMostrar = biblioteca.buscarCatalogo(idMostrar);

                        if (catalogoMostrar != null) { //si el catalogo no esta vacio muestra los que hay
                            catalogoMostrar.mostrarCatalogo();
                        }
                        else {
                            System.out.println("Error, el catálogo no existe");
                        }
                        break;

                    case 5: //eliminar un libro por ISBN
                        biblioteca.mostrarCatalogos();
                        System.out.print("Ingrese el ID del catálogo para eliminar un libro: ");
                        int idLibroEliminar = scanner.nextInt();
                        scanner.nextLine();
                        Catalogo catalogoEliminar = biblioteca.buscarCatalogo(idLibroEliminar);

                        if (catalogoEliminar != null) {
                            System.out.print("Ingrese el ISBN del libro a eliminar: ");
                            String isbnEliminar = scanner.nextLine();
                            catalogoEliminar.eliminarLibro(isbnEliminar);
                        }
                        else {
                            System.out.println("Catálogo no encontrado");
                        }
                        break;

                    case 6: //mostrar todos los catálogos
                        biblioteca.mostrarCatalogos();
                        break;

                    case 7: //salir
                        System.out.println("Saliendo del menu");
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción no válida");
                }


                //excepciones

            } catch (InputMismatchException e) { //captura si se ingresa un dato diferente al del menú
                System.out.println("Error: Entrada inválida. Introduzca un número.");
                scanner.nextLine();
            } catch (IllegalStateException | NoSuchElementException e) { //captura si no hay más espacio para un libro
                System.out.println("Error: " + e.getMessage());
            } catch (
                    Exception e) { //Captura cualquier otra excepción no prevista para evitar que el programa se cierre de forma abrupta
                System.out.println("Error inesperado: " + e.getMessage());
            }

        }
        scanner.close();
    }
}

