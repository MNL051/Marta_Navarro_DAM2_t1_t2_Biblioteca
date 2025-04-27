public abstract class Libro {

    protected String autor, ISBN;
    protected int numPaginas;


    public Libro (){} //constructor vacío

    public Libro(String autor, int numPaginas, String ISBN){

        this.autor  = autor;
        this.numPaginas = numPaginas;
        this.ISBN = ISBN;

    }

    public String getAutor(){ //obtener el autor
        return autor;
    }

    public int getNumPaginas(){ //obtener el numPaginas
        return numPaginas;
    }

    public String getISBN(){ //obtener el ISBN

        return ISBN;
    }

    public abstract String getAtributos(); //metodo abstracto que fueza al resto de géneros a añadir sus propios atributos

     //@Override
    public String toString(){ //metodo para imprimir la información común + los atributos personales de cada libro
        return  "Autor: " + autor + ", Páginas: " + numPaginas + ", ISBN: " + ISBN + ", " + getAtributos();

    };

    //@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; //verifica si ambos objetos son el mismo
        if (obj == null || getClass() != obj.getClass()) return false; //si no son lo mismo no son iguales
        Libro libro = (Libro) obj; //convierte el obj en libro
        return ISBN.equalsIgnoreCase(libro.ISBN); //compara solo el ISBN
    }
}

class LibroComedia extends Libro{ //hereda de la clase abstracta de Libro

    private String tipoHumor;

    //constructor vacio
    public LibroComedia (){}

    public LibroComedia(String autor, int numPaginas, String ISBN, String tipoHumor){
        super (autor, numPaginas, ISBN); //super hace referencia al constructor de la clase padre
        this.tipoHumor = tipoHumor;
    }


    public String getAtributos() {
        //return "Comedia - Autor: " + autor + ", Páginas: " + paginas + ", ISBN: " + isbn + ", Humor: " + tipoHumor;
        return "Género: Comedia, Tipo de humor: " + tipoHumor;
    }
}

class LibroTerror extends Libro{ //hereda de la clase abstracta de Libro

    private double calificacion;

    //constructor vacio
    public LibroTerror(){}

    public LibroTerror(String autor, int numPaginas, String ISBN, double calificacion){
        super(autor, numPaginas, ISBN);
        this.calificacion = calificacion;
    }

    public String getAtributos() {
        return "Género: Terror, Calificación: " + calificacion;
    }
}

class LibroPoliciaca extends Libro{ //hereda de la clase abstracta de Libro

    private String trama, personajes;

    //constructor vacio
    public LibroPoliciaca(){}

    public LibroPoliciaca(String autor, int numPaginas, String ISBN, String trama ,String personajes){
        super(autor, numPaginas, ISBN);
        this.trama = trama;
        this.personajes = personajes;
    }

    public String getAtributos() {
        return "Género: Policiaca, Trama: " + trama + ", Personajes: " + personajes;
    }
}
