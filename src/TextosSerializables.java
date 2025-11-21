import java.time.LocalDate;
import java.io.Serializable;
class TextosSerializables implements Serializable{
    public static final long serialVersionUID = 1L;
    private final String nick;
    private final LocalDate fecha;
    private final String texto;

    public TextosSerializables(String nick, LocalDate fecha, String texto){
        this.nick = nick;
        this.fecha = fecha;
        this.texto = texto;
    }

    public String getNick(){
        return this.nick;
    }
    public LocalDate getFecha(){
        return this.fecha;
    }
    public String getTexto(){
        return this.texto;
    }
    @Override
    public String toString(){
        return "Usuario{ \n nick= '" + nick + "'\n Fecha= '" + fecha + "'\n Texto= '" + texto + "'}";
    }

}