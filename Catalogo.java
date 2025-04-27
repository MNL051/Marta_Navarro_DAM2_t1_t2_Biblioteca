import java.util.ArrayList;

public class Catalogo {

    private int id;
    private ArrayList<Libro> libros;
    private final int topeLibros = 4; //final al ser una variable que no busco cambiar

    //constructor vacio
    public Catalogo(){}

    //constructor
    public Catalogo(int id){
        this.id = id;
        this.libros = new ArrayList<>();
    }

    public int getId(){ //obtenner el id del catalogo
        return id;
    }

    public boolean agregarLibro(Libro libro){
        if(libros.size() >= topeLibros){
            throw new IllegalStateException("No se pueden añadir más libros, catálogo lleno");
            //excepcion que indica que no hay más espacio para más libros

        }
        if (buscarLibro(libro.getISBN()) != null) { //para verificar si el libro ya está en el catálogo
            System.out.println("El libro ya está en el catálogo");
            return false;
        }
        libros.add(libro); //agrega el libro si se comprueba que no esta
        return true;

    }

    public Libro buscarLibro(String ISBN){ //busca por ISBN el libro

        for (Libro libro : libros){
            if (libro.getISBN().equalsIgnoreCase(ISBN)){
                return libro;
            }
        }
        return null;

    }

    public boolean eliminarLibro(String ISBN){ //borra el libro por su ISBN
        Libro libroABorrar = buscarLibro(ISBN); //se busca primero el libro
        if(libroABorrar != null){ //comprueba si el está o no
            libros.remove(libroABorrar);
            System.out.println("Libro borrado");
            return true;
        }
        else{
            System.out.println("No se encontro el libro");
            return false;
        }

    }

    public void mostrarCatalogo(){ //
        System.out.println("Catálogo ID: " + id);
        if (libros.isEmpty()) { //comprueba si no hay ningun libro guardado
            System.out.println("El catálogo está vacío");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public int numLibros(){ //devuelve el numero total de libros
        return libros.size();
    }
}
