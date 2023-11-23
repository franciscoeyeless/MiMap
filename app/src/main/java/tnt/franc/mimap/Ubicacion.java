package tnt.franc.mimap;

public class Ubicacion {
    private String idUbi;
    private String nombre;
    private String Latitud;
    private String Longitud;



    public Ubicacion()
    {
        this.idUbi="";
        this.nombre="";
        this.Latitud="";
        this.Longitud="";

    }

    public Ubicacion( String idUbi, String nombre, String Latitud, String Longitud )
    {
        this.idUbi=idUbi;
        this.nombre=nombre;
        this.Latitud=Latitud;
        this.Longitud=Longitud;
    }

    public String getIdUbi() {
        return idUbi;
    }

    public void setIdUbi(String idUbi) {
        this.idUbi = idUbi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    @Override
    public String toString() {
        return "Ubicacion {" +
                "idUbi='" + idUbi + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Latitud='" + Latitud + '\'' +
                ", Longitud='" + Longitud + '\'' +
                '}';
    }

}