import java.util.ArrayList;


public class Biblioteca {

    private String nombre, director;
    private ArrayList<Catalogo> catalogos;

    //cnstructor vacío
    public Biblioteca(){}

    //constructor
    public Biblioteca(String nombre, String director){
        this.nombre = nombre;
        this.director = director;
        this.catalogos = new ArrayList<>(); //se crea la lista de catálogos
    }

    public void agregarCatalogo(Catalogo catalogo) { //agrega un catálogo nuevo a la lista de catálogos
        catalogos.add(catalogo);
    }

    public Catalogo buscarCatalogo(int id){ //busca el catálogo por el id del mismo

        for (Catalogo catalogo : catalogos){
            if (catalogo.getId()== id){ //compara el id, en caso que sea el mismo lo devuelve
                return catalogo;
            }
        }
        return null; //en caso contrario devuelve vació

    }

    public void mostrarCatalogos(){
        if(catalogos.isEmpty()){ //imprime un mensaje si el catálogo esta vacío
            System.out.println("No hay catálogos guardados actualmente");
        }
        else{
            System.out.println("Catalogos actuales: ");
            for(Catalogo catalogo :catalogos){ //muestra los catálogos guardados
                System.out.println("ID: " + catalogo.getId() + ", Libros: " + catalogo.numLibros());
            }
        }
    }
}

