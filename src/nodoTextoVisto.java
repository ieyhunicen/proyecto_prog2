class nodoTextoVisto{

    private nodoTexto textoVisto;
    private nodoTextoVisto sigVisto;

    public nodoTextoVisto(){
        this.textoVisto=null;
        this.sigVisto=null;
    }

    public nodoTexto getTextoVisto(){
        return textoVisto;
    }
    public void setTexto(nodoTexto nuevo){
        this.textoVisto = nuevo;
    }
    public nodoTextoVisto getSiguienteTextoVisto(){
        return sigVisto;
    }
    public void setSiguienteTextoVisto(nodoTextoVisto nuevo){
        this.sigVisto = nuevo;
    }
    @Override
    public String toString(){
        return "Texto visto{textoVisto='" + textoVisto + "}";
    }


}
