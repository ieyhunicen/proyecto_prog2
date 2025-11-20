class NodoArbolUsuario{

    private String nick;
    private String password;
    private nodoTexto primerTexto;
    private nodoTextoVisto primerTextoVisto;
    private nodoArbolUsuario anterior;
    private nodoArbolUsuario siguiente;

    public NodoArbolUsuario(String nick, String pass){
        this.nick = nick;
        this.password = pass;
        primerTexto = null;
        primerTextoVisto = null;
        anterior = siguiente = null;
    }
    public String getNick(){
        return this.nick;
    }

    public void setNuevoNick(String nick){
        this.nick=nick;
    }

    public String getPassword(){
        return this.password;
    }

    public void setNuevaPassword(String password){
        this.password=password;
    }

    public NodoArbolUsuario getSiguiente(){
        return siguiente;
    }

    public void setSiguiente(NodoArbolUsuario nuevo){
        this.siguiente = nuevo;
    }

    public NodoArbolUsuario getAnterior(){
        return anterior;
    }

    public void setAnterior(nodoArbolUsuario nuevo){
        this.anterior = nuevo;
    }


    public nodoTexto getTextos(){
        return primerTexto;
    }


    public int cantidadTextos(){
        int cant=0;
        nodoTexto actual = primerTexto;
        while(actual!=null){
            cant++;
            actual=actual.getSiguienteTexto();
        }
        return cant;
    }

    public void insertarTextoOrdenadoFechaAsc(nodoTexto nuevo){
        if(primerTexto==null){
            primerTexto = nuevo;
        }
        if(nuevo.getFecha().isBefore(primerTexto.getFecha())){
            nuevo.setSiguienteTexto(primerTexto);
            primerTexto = nuevo;
        }

        nodoTexto actual = primerTexto;

        while(actual.getSiguienteTexto() != null && !nuevo.getFecha().isBefore(actual.getSiguienteTexto().getFecha())){
            actual = actual.getSiguienteTexto();
        }
        nuevo.setSiguienteTexto(actual.getSiguienteTexto());
        actual.setSiguienteTexto(nuevo);
    }


}