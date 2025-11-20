
import java.util.Scanner;
import java.io.IOException;
class app {
    static Scanner sc = new Scanner(System.in);
    static arbolUsuario arbol = new arbolUsuario();
    static ListaTextosVistos Lista = new ListaTextosVistos();
    static nodoArbolUsuario usuarioLogueado = null;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
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
        //mandarlo a archivos
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
        } else if (menu == 3) { // Opción 3: Devolver el usuario que más textos creó
            gestorArchivos.LeerArchivo();
            return false;
        }else if (menu == 4) {
            gestorArchivos.guardarUsuarios(arbol.getRaiz());

            return false;
        }
        return true;
    }




    public static void segundoMenu(int menu) {
        if (menu == 1) {
            String texto="";
            texto = solicitarTexto();
            boolean textoExistente = arbol.verificarTextoExistente(texto, usuarioLogueado);
            if(textoExistente == false){
                nodoTexto nuevo = new nodoTexto(texto, 0);
                nodoTextoVisto nuevoTexto = new nodoTextoVisto();
                nuevoTexto.setTextoVisto(nuevo);
                usuarioLogueado.insertarTextoOrdenadoFechaAsc(nuevo);
                Lista.insertarTextos(nuevoTexto);
            }

        } else if (menu == 2) {
            System.out.println("Funcionalidad 'Visualizar texto' pendiente de implementar.");
        } else if (menu == 3) {

            if (usuarioLogueado == null) {
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
                    } else {
                        System.out.println("Las claves ingresadas no coinciden.");
                    }
                }
            }

        } else if (menu == 4) {
            usuarioLogueado = null;
            System.out.println("Sesión cerrada. Volviendo al menú principal.");
        } else {
            System.out.println("Ingresaste una opcion no valida");
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
}