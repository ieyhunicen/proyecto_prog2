import java.time.LocalDate;
class nodoTexto{
    private LocalDate fecha;
    private String texto;
    private int vistas;
    private nodoTexto siguiente;
    private nodoTexto menosVisto;

    public nodoTexto(String texto, int vistas, LocalDate fecha){
        this.texto = texto;
        this.vistas= vistas;
        this.fecha = fecha;
        this.siguiente = null;
        this.menosVisto = null;
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
    public nodoTexto getMenosVisto(){
        return menosVisto;
    }
    public void setMenosVisto(nodoTexto menosVisto){
        this.menosVisto = menosVisto;
    }
    public void addVista(){
        this.vistas++;
    }
}