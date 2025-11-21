import java.io.Serializable;
class VistasSerializables implements Serializable{
    public static final long serialVersionUID = 1L;
    private final String creador;
    private final String texto;
    private final String vidente;

    public VistasSerializables(String creador, String texto, String vidente){
        this.creador = creador;
        this.texto = texto;
        this.vidente=vidente;
    }
    public String getNickCreador(){
        return this.creador;
    }
    public String getVidente(){
        return this.vidente;
    }
    public String getTexto(){
        return this.texto;
    }
    @Override
    public String toString(){
        return "Escritor{ \n nick= '" + creador + "'\n Texto= '" + texto + "'\n 'Vidente= '"+ vidente+ "'}";
    }

}
