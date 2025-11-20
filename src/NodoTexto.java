import java.time.LocalDate;
class NodoTexto{

    private LocalDate fecha;
    private String texto;
    private int vistas;
    private nodoTexto siguiente;


    public NodoTexto(String texto, int vistas){
        this.texto=texto;
        this.vistas=vistas;
        this.fecha= LocalDate.now();
        this.siguiente=null;
    }
    public void setSiguienteTexto(nodoTexto sig){
        this.siguiente=sig;
    }
    public nodoTexto getSiguienteTexto(){
        return siguiente;
    }
    public int getVistas(){
        return vistas;
    }
    public String getPrimerTexto(){
        return texto;
    }
    public LocalDate getFecha(){
        return fecha;
    }


    @Override
    public String toString(){
        return "Texto{texto='" + texto + "', vistas=" + vistas + ", fecha=" + fecha + "}";
    }
}
