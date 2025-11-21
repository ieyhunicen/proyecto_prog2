class nodoArbolUsuario{
    private String nick;
    private String password;
    private nodoTexto primerTexto;
    private nodoTextoVisto textosVistos;
    private nodoArbolUsuario anterior;
    private nodoArbolUsuario siguiente;

    public nodoArbolUsuario(String nick, String pass){
        this.nick = nick;
        this.password = pass;
        primerTexto = null;
        textosVistos = null;
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

    public nodoArbolUsuario getSiguiente(){
        return siguiente;
    }

    public void setSiguiente(nodoArbolUsuario nuevo){
        this.siguiente = nuevo;
    }

    public nodoArbolUsuario getAnterior(){
        return anterior;
    }

    public void setAnterior(nodoArbolUsuario nuevo){
        this.anterior = nuevo;
    }

    public nodoTextoVisto getTextosVistos(){
        return textosVistos;
    }
    public nodoTexto getTextos(){
        return primerTexto;
    }


    public int getCantTextos(){
        int cant=0;
        nodoTexto actual = primerTexto;
        while(actual!=null){
            cant++;
            actual=actual.getSiguienteTexto();
        }
        return cant;
    }

    public boolean yaVioTexto(nodoTexto textoBuscado){
        nodoTextoVisto recorrido = this.textosVistos;
        while(recorrido != null){
            if(recorrido.getTextoVisto() == textoBuscado){
                return true;
            }
            recorrido = recorrido.getSiguienteTextoVisto();
        }
        return false;
    }

    public void insertarTextoOrdenadoFechaAsc(nodoTexto nuevo){
        if(primerTexto==null){
            primerTexto = nuevo;
            return;
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

    public void insertarTextoVisto(nodoTextoVisto nuevoVisto) {
        if(this.textosVistos == null){
            this.textosVistos = nuevoVisto;
        }else{
            nodoTextoVisto actual = this.textosVistos;
            while(actual.getSiguienteTextoVisto() != null){
                actual = actual.getSiguienteTextoVisto();
            }
            actual.setSiguienteTextoVisto(nuevoVisto);
        }
    }
}