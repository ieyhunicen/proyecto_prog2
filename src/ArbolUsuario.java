class ArbolUsuario {
    private nodoArbolUsuario raiz;

    public ArbolUsuario() {
        raiz = null;
    }

    public nodoArbolUsuario getRaiz() {
        return raiz;
    }

    public nodoArbolUsuario buscarCreadorDeTexto(nodoTexto textoBuscado){
        return buscarCreadorDeTextoRec(this.raiz, textoBuscado);
    }

    private nodoArbolUsuario buscarCreadorDeTextoRec(nodoArbolUsuario actual, nodoTexto textoBuscado){
        if(actual == null){
            return null;
        }
        nodoTexto textoActual = actual.getTextos();
        while(textoActual != null) {
            if(textoActual == textoBuscado){
                return actual;
            }
            textoActual = textoActual.getSiguienteTexto();
        }
        nodoArbolUsuario creadorEnAnterior = buscarCreadorDeTextoRec(actual.getAnterior(), textoBuscado);
        if(creadorEnAnterior != null){
            return creadorEnAnterior;
        } else{
            return buscarCreadorDeTextoRec(actual.getSiguiente(), textoBuscado);
        }
    }

    public void usuarioConMasTextos() {
        if(raiz != null){
            nodoArbolUsuario actual = usuarioConMasTextosRec(this.raiz, this.raiz);
            System.out.println("El usuario " + actual.getNick() +
                    " es el Usuario que mas textos creo\nY creo " +
                    actual.getCantTextos() + " textos");
        }else{
            System.out.println("no intentes pavadas");
        }
    }

    private nodoArbolUsuario usuarioConMasTextosRec(nodoArbolUsuario actual, nodoArbolUsuario maxNodo){
        if(actual == null){
            return maxNodo;
        }
        nodoArbolUsuario maxIzq = usuarioConMasTextosRec(actual.getAnterior(), maxNodo);
        if(maxIzq.getCantTextos() >maxNodo.getCantTextos()){
            maxNodo = maxIzq;
        }
        if(actual.getCantTextos()>maxNodo.getCantTextos()){
            maxNodo = actual;
        }
        nodoArbolUsuario maxDer = usuarioConMasTextosRec(actual.getSiguiente(), maxNodo);
        if(maxDer.getCantTextos()>maxNodo.getCantTextos()){
            maxNodo = maxDer;
        }
        return maxNodo;
    }

    public void insertarUsuarios(String nick, String password){
        this.raiz = insertarUsuariosRec(this.raiz, nick, password);
    }

    private nodoArbolUsuario insertarUsuariosRec(nodoArbolUsuario actual, String nick, String password) {
        if(actual == null){
            return new nodoArbolUsuario(nick, password);
        }
        int comparador = nick.compareTo(actual.getNick());
        if(comparador < 0){
            actual.setAnterior(insertarUsuariosRec(actual.getAnterior(), nick, password));
        }
        else if(comparador > 0){
            actual.setSiguiente(insertarUsuariosRec(actual.getSiguiente(), nick, password));
        }
        return actual;
    }



    public nodoArbolUsuario buscarUsuario(String nombre){
        return buscarUsuarioRec(nombre, raiz);
    }

    private nodoArbolUsuario buscarUsuarioRec(String nombre, nodoArbolUsuario actual) {
        if(actual == null){
            return null;
        }
        int comparador = nombre.compareTo(actual.getNick());
        if(comparador < 0){
            return buscarUsuarioRec(nombre, actual.getAnterior());
        }
        if(comparador > 0){
            return buscarUsuarioRec(nombre, actual.getSiguiente());
        }else{
            return actual;
        }
    }

    public boolean buscarNombre(String nombre){
        return buscarNombreRec(nombre, raiz);
    }

    private boolean buscarNombreRec(String nombre, nodoArbolUsuario actual){
        if(actual == null){
            return false;
        }
        int comparador = nombre.compareTo(actual.getNick());
        if(comparador < 0){
            return buscarNombreRec(nombre, actual.getAnterior());
        }
        if(comparador > 0){
            return buscarNombreRec(nombre, actual.getSiguiente());
        }else{
            return true;
        }
    }

    public nodoArbolUsuario logIn(String nombre, String clave){
        return logInRec(nombre, raiz, clave);
    }

    private nodoArbolUsuario logInRec(String nombre, nodoArbolUsuario actual, String clave) {
        if(actual == null){
            return null;
        }
        int comparador = nombre.compareTo(actual.getNick());
        if(comparador < 0){
            return logInRec(nombre, actual.getAnterior(), clave);
        }
        if(comparador > 0){
            return logInRec(nombre, actual.getSiguiente(), clave);
        }
        else if(actual.getPassword().equals(clave)){
            return actual;
        }
        return null;
    }

    public boolean verificarTextoExistente(String texto, nodoArbolUsuario actual) {
        if(actual == null) return false;
        nodoTexto recorrido = actual.getTextos();
        while(recorrido != null) {
            if(texto.equals(recorrido.getPrimerTexto())) {
                System.out.println("Error: El texto ya ha sido creado por este usuario.");
                return true;
            }
            recorrido = recorrido.getSiguienteTexto();
        }
        return false;
    }

    public nodoTexto buscarTextoPorContenido(String textoBuscado) {
        return buscarTextoPorContenidoRec(this.raiz, textoBuscado);
    }

    private nodoTexto buscarTextoPorContenidoRec(nodoArbolUsuario actual, String textoBuscado) {
        if(actual == null){
            return null;
        }
        nodoTexto textoActual = actual.getTextos();
        while(textoActual != null){
            if(textoActual.getPrimerTexto().equals(textoBuscado)) {
                return textoActual;
            } else{
                textoActual = textoActual.getSiguienteTexto();
            }
        }
        nodoTexto resultado = buscarTextoPorContenidoRec(actual.getAnterior(), textoBuscado);
        if(resultado != null){
            return resultado;
        }
        nodoTexto resultadoDerecha = buscarTextoPorContenidoRec(actual.getSiguiente(), textoBuscado);
        return resultadoDerecha;
    }

    public void actualizarVistaDeUsuario(nodoArbolUsuario observador, nodoTexto textoVisto) {
        textoVisto.addVista();
        nodoTextoVisto n = new nodoTextoVisto();
        n.setTextoVisto(textoVisto);
        observador.insertarTextoVisto(n);
    }

    public boolean verificarTextoExistenteVisto(String texto, nodoArbolUsuario actual){
        if (actual == null){
            return false;
        }
        nodoTexto recorrido = actual.getTextos();
        while(recorrido != null){
            if(texto.equals(recorrido.getPrimerTexto())){
                return true;
            }
            recorrido = recorrido.getSiguienteTexto();
        }
        return false;
    }
    public void insertarTexto(nodoArbolUsuario usuario, nodoTexto nuevoTexto) {
        nodoTexto actual = usuario.getTextos();
        if(actual == null) {
            usuario.insertarTextoOrdenadoFechaAsc(nuevoTexto);
            return;
        }
        if(nuevoTexto.getFecha().isBefore(actual.getFecha())) {
            nuevoTexto.setSiguienteTexto(actual);
            usuario.insertarTextoOrdenadoFechaAsc(nuevoTexto);
            return;
        }
        while(actual.getSiguienteTexto() != null) {
            if(nuevoTexto.getFecha().isBefore(actual.getSiguienteTexto().getFecha())) {
                nuevoTexto.setSiguienteTexto(actual.getSiguienteTexto());
                actual.setSiguienteTexto(nuevoTexto);
                return;
            }
            actual = actual.getSiguienteTexto();
        }
        actual.setSiguienteTexto(nuevoTexto);
    }
}
