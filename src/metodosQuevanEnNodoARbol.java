/*public boolean yaVioTexto(nodoTexto textoBuscado){
        nodoTextoVisto recorrido = this.TextosVistos;
        while(recorrido != null){
            if(recorrido.getTextoVisto() == textoBuscado){
                return true; // El texto ya fue visto
            }
            recorrido = recorrido.getSiguienteTextoVisto();
        }
        return false; // El texto no ha sido visto
    }

    public void insertarTextoVisto(nodoTextoVisto nuevoVisto) {
        if(this.TextosVistos == null){
            this.TextosVistos = nuevoVisto;
        }else{
            nuevoVisto.setSiguienteTextoVisto(this.TextosVistos);
            this.TextosVistos = nuevoVisto;
        }
    }

 */