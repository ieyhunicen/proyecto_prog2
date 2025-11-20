class ArbolUsuario{
    private nodoArbolUsuario raiz;


    public ArbolUsuario(){
        raiz=null;
    }

    public nodoArbolUsuario getRaiz(){
        return raiz;
    }

    public void insertarUsuarios(String nick, String password) {
        this.raiz = insertarUsuariosRec(this.raiz, nick, password);
    }
    private nodoArbolUsuario insertarUsuariosRec(nodoArbolUsuario actual, String nick, String password) {
        if (actual == null) {
            return new nodoArbolUsuario(nick, password);
        }
        int comparador = nick.compareTo(actual.getNick());
        if (comparador < 0) {
            actual.setAnterior(insertarUsuariosRec(actual.getAnterior(), nick, password));
        } else if (comparador > 0) {
            actual.setSiguiente(insertarUsuariosRec(actual.getSiguiente(), nick, password));
        }
        return actual;
    }

    public boolean buscarNombre(String nombre){
        boolean nombreExistente= buscarNombreRec(nombre, raiz);
        return nombreExistente;
    }

    public nodoArbolUsuario logIn(String nombre, String clave) {
        nodoArbolUsuario usuarioExistente = logInRec(nombre, raiz, clave);
        return usuarioExistente;
    }

    private nodoArbolUsuario logInRec(String nombre, nodoArbolUsuario actual, String clave) {
        if (actual == null) {
            return null;
        }

        int comparador = nombre.compareTo(actual.getNick());
        if (comparador < 0) {
            return logInRec(nombre, actual.getAnterior(), clave);
        } else if (comparador > 0) {
            return logInRec(nombre, actual.getSiguiente(), clave);
        } else {
            if (actual.getPassword().equals(clave)) {
                return actual;
            } else {
                return null;
            }
        }
    }

    private boolean buscarNombreRec(String nombre, nodoArbolUsuario actual){
        if (actual == null) {
            return false;
        }
        int comparador = nombre.compareTo(actual.getNick());
        if (comparador < 0) {
            return buscarNombreRec(nombre, actual.getAnterior());
        } else if (comparador > 0) {
            return buscarNombreRec(nombre, actual.getSiguiente());
        } else {
            return true;
        }
    }

    public boolean verificarTextoExistente(String texto, nodoArbolUsuario actual){
        boolean TextoExistente = false;
        nodoTexto recorrido = actual.getTextos();
        while(!texto.equals(recorrido.getPrimerTexto())) && recorrido!=null){
            recorrido = recorrido.getSiguienteTexto();
        }
        if(texto.equals(recorrido.getPrimerTexto())){
            System.out.println("el texto ya estaba creado");
            return true;
        }
        return false;
    }
}