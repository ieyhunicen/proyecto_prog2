/*public static boolean visualizarTexto() {
        nodoTexto actual = textosMasVistos;
        nodoTexto visualizarTexto = null;
        boolean encontrado = false;     //para poder salir del bucle mas tarde

        while(actual != null && !encontrado){
            boolean esMiTexto = arbol.verificarTextoExistenteVisto(actual.getPrimerTexto(), usuarioLogueado);
            if(!esMiTexto){

                boolean yaVio = usuarioLogueado.yaVioTexto(actual);

                if(!yaVio){
                    visualizarTexto = actual;
                    encontrado = true;
                }
            }
            if(!encontrado){
                actual = actual.getMenosVisto();
            }

        }
        if(visualizarTexto != null){
            System.out.println("\n=============================================");
            System.out.println("Leyendo texto (Vistas antes: " + visualizarTexto.getVistas() + ")");
            System.out.println("---------------------------------------------");
            System.out.println(visualizarTexto.getPrimerTexto());
            System.out.println("---------------------------------------------");

            visualizarTexto.addVista();
            reordenarListaMasVistos(visualizarTexto);

            nodoTextoVisto nuevoVisto = new nodoTextoVisto();
            nuevoVisto.setTexto(visualizarTexto);
            usuarioLogueado.insertarTextoVisto(nuevoVisto);

            System.out.println("Visualización completada. Vistas actuales: " + visualizarTexto.getVistas());
            return true;
        } else {
            return false;
        }
    }


    public static void reordenarListaMasVistos(nodoTexto nodoModificado) {
        if(textosMasVistos == null || nodoModificado.getMenosVisto() == null){    // Si la lista tiene 0 o 1 elemento, no hay nada que reordenar.
            return;
        }
        if(nodoModificado.getVistas() <= nodoModificado.getMenosVisto().getVistas()){ // El reordenamiento solo es necesario si el nodo tiene más vistas que el siguiente.
            return;
        }

        nodoTexto anterior = null;
        nodoTexto actual = textosMasVistos;

        if(actual!= nodoModificado){
            while(actual != null && actual.getMenosVisto() != nodoModificado){
                actual = actual.getMenosVisto();
            }
            anterior = actual;

            if(anterior == null) return;

            anterior.setMenosVisto(nodoModificado.getMenosVisto());
        }
        nodoModificado.setMenosVisto(null); // Limpiar su siguiente temporalmente



        nodoTexto puntero = textosMasVistos;
        nodoTexto nuevaPosicionAnterior = null;

        // Buscar el punto donde el nodoModificado se debe insertar (justo antes del primer nodo con menos vistas)
        while(puntero != null && puntero.getVistas() >= nodoModificado.getVistas()) {
            // Si el puntero es el nodo modificado, lo ignoramos, ya que estamos buscando su nueva posición.
            if(puntero == nodoModificado){
                puntero = puntero.getMenosVisto();
                continue;
            }
        nuevaPosicionAnterior = puntero;
        puntero = puntero.getMenosVisto();
        }
        //inserto
        if(nuevaPosicionAnterior == null){
            nodoModificado.setMenosVisto(textosMasVistos);  //El nodo se mueve al inicio (es el nuevo textosMasVistos)
            textosMasVistos = nodoModificado;
        }else{
            nodoModificado.setMenosVisto(nuevaPosicionAnterior.getMenosVisto());    //El nodo se inserta entre dos nodos
            nuevaPosicionAnterior.setMenosVisto(nodoModificado);
        }
    }
}
*/
