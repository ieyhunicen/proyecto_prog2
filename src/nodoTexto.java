import java.time.LocalDate;
class nodoTexto{
    private LocalDate fecha;
    private String texto;
    private int vistas;
    private nodoTexto siguiente;

    public nodoTexto(String texto, int vistas){
        this.texto=texto;
        this.vistas=vistas;
        this.fecha= LocalDate.now();
        siguiente = null;
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

}