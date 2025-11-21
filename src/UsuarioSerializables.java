import java.io.Serializable;
class UsuarioSerializables implements Serializable{
    public static final long serialVersionUID = 1L;
    private final String nick;
    private final String password;

    public UsuarioSerializables(String nick, String password){
        this.nick = nick;
        this.password = password;
    }
    public String getNick(){
        return this.nick;
    }
    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString(){
        return "Usuario{nick='" + nick + "', password='" + password + "'}";
    }

}