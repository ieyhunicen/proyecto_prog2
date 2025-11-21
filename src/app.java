import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
class app {
    static Scanner sc = new Scanner(System.in);
    static ArbolUsuario arbol = new ArbolUsuario();
    static nodoTexto textosMasVistos =null;
    static nodoArbolUsuario usuarioLogueado = null;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        GestorArchivos.LeerUsuarios(arbol);
        GestorArchivos.LeerTexto(arbol);
        GestorArchivos.LeerVistas(arbol);
        int selectmenu = 0;
        boolean cerrar = false;
        while (!cerrar) {
            if (usuarioLogueado == null) {
                System.out.println("Envie un numero deacuerdo a lo que desee\n" +
                        "1-Identificarse\n" +
                        "2-Crear un usuario\n" +
                        "3-Devolver los datos del usuario que más textos creó\n" +
                        "4-Salir \n");

                selectmenu = sc.nextInt();
                sc.nextLine();
                cerrar = primerMenu(selectmenu);
            } else {
                System.out.println("Genial " + usuarioLogueado.getNick() + " Envie un numero deacuerdo a lo que desee\n" +
                        "1-Crear un texto.\n" +
                        "2-Visualizar un texto.\n" +
                        "3-Cambiar la password.\n" +
                        "4-Volver al menú anterior. \n");
                selectmenu = sc.nextInt();
                sc.nextLine();
                segundoMenu(selectmenu);
            }
        }
        System.out.println("Saliendo de Tex-Tok. ¡Hasta pronto!");
    }
    public static boolean primerMenu(int menu) throws IOException , ClassNotFoundException{ //poner try,catch en el gestor
        if (menu == 1) {
            String nick = "";
            String clave = "";
            nodoArbolUsuario tempNodo = null;
            System.out.println("Ingrese el nombre de Usuario: ");
            nick = sc.nextLine();
            if (nick.isEmpty()) {
                System.out.println("Error: El usuario no puede estar vacío.");
            } else {
                System.out.println("Ingrese la clave del Usuario: ");
                clave = sc.nextLine();
                if (clave.isEmpty()) {
                    System.out.println("Error: La clave no puede estar vacía.");
                } else {
                    tempNodo = arbol.logIn(nick, clave);
                    if (tempNodo != null) {
                        usuarioLogueado = tempNodo;
                        System.out.println("Bienvenido, " + usuarioLogueado.getNick() + ". Ingreso exitoso.");
                    } else {
                        System.out.println("Error: Nick o clave incorrectos.");
                    }
                }
            }

            return false;
        } else if (menu == 2) {
            String nick = "";
            String clave = "";
            boolean validacionCompleta = false;
            while (!validacionCompleta) {
                System.out.println("Ingrese el nombre de Usuario a crear: ");
                nick = sc.nextLine();
                if (nick.isEmpty()) {
                    System.out.println("Error: El usuario no puede estar vacío.");
                } else if (arbol.buscarNombre(nick)) {
                    System.out.println("Error: Ya existe un Usuario con ese nombre, por favor utilice otro.");
                } else {
                    System.out.println("Ingrese la contraseña del Usuario: ");
                    clave = sc.nextLine();
                    if (clave.isEmpty()) {
                        System.out.println("Error: La clave no puede estar vacía.");
                    } else{
                        arbol.insertarUsuarios(nick, clave);
                        System.out.println("Usuario '" + nick + "' creado exitosamente.");
                        usuarioLogueado = arbol.logIn(nick, clave);
                        validacionCompleta = true;
                    }
                }
            }
            return false;
        } else if (menu == 3) {
            arbol.usuarioConMasTextos();
            return false;
        }else if (menu == 4) {
            GestorArchivos.guardarUsuarios(arbol.getRaiz());
            GestorArchivos.guardarTextos(arbol.getRaiz());
            GestorArchivos.guardarVistas(arbol.getRaiz(),arbol);
            return true;
        }
        return true;
    }




    public static void segundoMenu(int menu) {
        if (menu == 1) {
            String texto = "";
            texto = solicitarTexto();
            if(texto.isEmpty()) {
                System.out.println("Error: No se puede crear un texto vacío.");
                return;
            }
            if(arbol.verificarTextoExistente(texto, usuarioLogueado)){
                return;
            }
            nodoTexto nuevo = new nodoTexto(texto, 0, LocalDate.now());
            usuarioLogueado.insertarTextoOrdenadoFechaAsc(nuevo);
            System.out.println("Texto creado exitosamente y agregado a la lista.");
            insertarTextoMasVisto(nuevo);
        } else if (menu == 2) {
            String seguir = "";
            boolean continuarVisualizando = true;
            while (continuarVisualizando) {
                boolean textoEncontrado = visualizarTexto();
                if (!textoEncontrado){
                    System.out.println("No hay más textos disponibles que no hayas visto o que no sean tuyos.");
                    continuarVisualizando = false;
                } else {
                    System.out.println("\n--- Opciones ---");
                    System.out.println("Presione '.' (punto) para ver el siguiente texto.");
                    System.out.println("Presione 'X' para volver al menú anterior.");
                    System.out.print("Esperando entrada: ");
                    seguir = sc.nextLine();
                    if(seguir.equals("x")){
                        continuarVisualizando = false;
                    }
                }
            }
        }else if (menu == 3){
            if(usuarioLogueado == null) {
                System.out.println("Error interno: No hay sesión activa.");
                return;
            }
            String intentoUno = "";
            String intentoDos = "";
            boolean passwordCambiada = false;
            while (!passwordCambiada) {
                System.out.println("Ingrese la NUEVA clave:");
                intentoUno = sc.nextLine();
                if (intentoUno.isEmpty()) {
                    System.out.println("La clave no puede estar vacía.");
                } else {
                    System.out.println("Por seguridad ingrese de nuevo la clave:");
                    intentoDos = sc.nextLine();

                    if (intentoUno.equals(intentoDos)) {
                        usuarioLogueado.setNuevaPassword(intentoUno);
                        System.out.println("Clave actualizada exitosamente.");
                        passwordCambiada = true;
                    } else{
                        System.out.println("Las claves ingresadas no coinciden.");
                    }
                }
            }

        }else if (menu == 4){
            usuarioLogueado = null;
            System.out.println("Sesión cerrada. Volviendo al menú principal.");
        } else {
            System.out.println("Ingresaste una opcion no valida");
        }
    }
    public static void insertarTextoMasVisto(nodoTexto nuevoTexto){
        if(textosMasVistos == null){
            textosMasVistos = nuevoTexto;
        }else{
            nodoTexto actual = textosMasVistos;

            while(actual.getMenosVisto() != null){
                actual = actual.getMenosVisto();
            }
            actual.setMenosVisto(nuevoTexto);
        }
    }
    public  static String solicitarTexto(){
        String texto = "";
        String linea;

        System.out.println("Escriba el texto (enter para terminar):");

        while(!(linea = sc.nextLine()).isEmpty()){  //usa concatenacion.
            texto += linea + "\n";
        }
        return texto;
    }
    public static boolean visualizarTexto(){
        nodoTexto actual = textosMasVistos;
        nodoTexto visualizarTexto = null;
        boolean encontrado = false;
        while(actual != null && !encontrado){
            nodoArbolUsuario creador = arbol.buscarCreadorDeTexto(actual);
            boolean esMiTexto = (creador != null && creador.getNick().equals(usuarioLogueado.getNick()));
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
            nuevoVisto.setTextoVisto(visualizarTexto);
            usuarioLogueado.insertarTextoVisto(nuevoVisto);

            System.out.println("Visualización completada. Vistas actuales: " + visualizarTexto.getVistas());
            return true;
        } else {
            return false;
        }
    }
    public static void reordenarListaMasVistos(nodoTexto nodoModificado){
        if(textosMasVistos == null || nodoModificado.getMenosVisto() == null){
            return;
        }
        if(nodoModificado.getVistas() <= nodoModificado.getMenosVisto().getVistas()){
            return;
        }
        nodoTexto anterior = null;
        nodoTexto actual = textosMasVistos;

        if (actual == nodoModificado) {
            textosMasVistos = nodoModificado.getMenosVisto();
        } else {
            while (actual != null && actual.getMenosVisto() != nodoModificado) {
                actual = actual.getMenosVisto();
            }
            if (actual != null) {
                anterior = actual;
                anterior.setMenosVisto(nodoModificado.getMenosVisto());
            } else {
                return;
            }
        }
        nodoModificado.setMenosVisto(null);
        nodoTexto puntero = textosMasVistos;
        nodoTexto nuevaPosicionAnterior = null;
        while(puntero != null && puntero.getVistas() >= nodoModificado.getVistas()){
            nuevaPosicionAnterior = puntero;
            puntero = puntero.getMenosVisto();
        }
        if(nuevaPosicionAnterior == null){
            nodoModificado.setMenosVisto(textosMasVistos);
            textosMasVistos = nodoModificado;
        } else{
            nodoModificado.setMenosVisto(nuevaPosicionAnterior.getMenosVisto());
            nuevaPosicionAnterior.setMenosVisto(nodoModificado);
        }
    }
}