class ArbolUsuario{
    private nodoArbolUsuario raiz;


    public ArbolUsuario(){
        raiz=null;
    }

    public nodoArbolUsuario getRaiz(){
        return raiz;
    }


    public void usuarioConMasTextos() {
        if(raiz!=null){
            nodoArbolUsuario actual = usuarioConMasTextosRec(this.raiz, this.raiz);
            System.out.println("El usuario "+ actual.getNick()+" es el Usuario que mas textos creo\n"+"Y creo "+actual.getCantTextos()+" textos");
        }else{
            System.out.println("no intentes pavadas");
        }
    }

    private nodoArbolUsuario usuarioConMasTextosRec(nodoArbolUsuario actual, nodoArbolUsuario maxNodo) {
        if (actual == null) {
            return maxNodo;
        }
        nodoArbolUsuario maxIzq = usuarioConMasTextosRec(actual.getAnterior(), maxNodo);
        if (maxIzq.getCantTextos() > maxNodo.getCantTextos()) {
            maxNodo = maxIzq;
        }
        if (actual.getCantTextos() > maxNodo.getCantTextos()) {
            maxNodo = actual;
        }
        nodoArbolUsuario maxDer = usuarioConMasTextosRec(actual.getSiguiente(), maxNodo);
        if (maxDer.getCantTextos() > maxNodo.getCantTextos()) {
            maxNodo = maxDer;
        }
        return maxNodo;
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

    public nodoArbolUsuario buscarUsuario(String nombre) {
        return buscarUsuarioRec(nombre, raiz);
    }

    private nodoArbolUsuario buscarUsuarioRec(String nombre, nodoArbolUsuario actual){
        if (actual == null) {
            return null;
        }
        int comparador = nombre.compareTo(actual.getNick());
        if (comparador < 0) {
            return buscarUsuarioRec(nombre, actual.getAnterior());
        } else if (comparador > 0) {
            return buscarUsuarioRec(nombre, actual.getSiguiente());
        } else {
            return actual;
        }
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
        if (actual == null) return false;
        nodoTexto recorrido = actual.getTextos();
        while(recorrido != null){
            if(texto.equals(recorrido.getPrimerTexto())){
                System.out.println("Error: El texto ya ha sido creado por este usuario.");
                return true; // Texto encontrado
            }
            recorrido = recorrido.getSiguienteTexto();
        }
        return false; // Texto no encontrado
    }
}
