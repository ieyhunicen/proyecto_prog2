class ListaTextosMasVistos{
    private nodoTextoVisto primero;


    public ListaTextosMasVistos(){
        primero = null;
    }


    public nodoTextoVisto getSiguienteTextoVisto(){
        return primero;
    }
    public void setSiguienteTextoVisto(nodoTextoVisto nuevo){
        this.primero = nuevo;
    }
    //manda el texto al final de la lista, ya que siempre van a comenzar en 0 vistas.
    public void insertarTextos(nodoTextoVisto nuevo){
        if(primero == null){
            primero = nuevo;
            nuevo.setSiguienteTextoVisto(primero);
        }

        nodoTextoVisto actual = primero;

        while(actual!=null){
            actual = actual.getSiguienteTextoVisto();
        }
        nuevo.setSiguienteTextoVisto(actual.getSiguienteTextoVisto());
        actual.setSiguienteTextoVisto(nuevo);

    }


}