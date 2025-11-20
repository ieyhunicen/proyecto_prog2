class ListaTextosVistos{
    private nodoTextoVisto primero;


    public ListaTextosVistos(){
        primero = null;
    }


    public nodoTextoVisto getSiguienteTextoVisto(){
        return primero;
    }
    public void setSiguienteTextoVisto(nodoTextoVisto nuevo){
        this.primero = nuevo;
    }

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
