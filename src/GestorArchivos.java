import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.time.LocalDate;

class GestorArchivos{

    //guardar Textos
    public static void guardarTextos(nodoArbolUsuario raiz) {
        try(FileOutputStream ruta=new FileOutputStream("/work/ezeben-archtextos.ser");
            ObjectOutputStream os=new ObjectOutputStream(ruta)){
            PreOrderTexto(raiz, os);
        }catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void recorrerTextos(nodoArbolUsuario actual, ObjectOutputStream oos) throws IOException{
        nodoTexto textoActual= actual.getTextos();
        while(textoActual != null){
            TextosSerializables u = new TextosSerializables(actual.getNick(), textoActual.getFecha() ,textoActual.getPrimerTexto());
            oos.writeObject(u);
            System.out.println("guardando usuario: " + u.getNick() + " \n" + " Guardando fecha: " + u.getFecha() + "\n" + " Guardando texto: "+ u.getTexto());
            textoActual = textoActual.getSiguienteTexto();
        }
    }

    public static void PreOrderTexto(nodoArbolUsuario actual, ObjectOutputStream oos) throws IOException{
        if(actual != null) {
            recorrerTextos(actual, oos);
            PreOrderTexto(actual.getAnterior(), oos);
            PreOrderTexto(actual.getSiguiente(), oos);
        }
    }

    public static void LeerTexto(ArbolUsuario arbol){
        try (FileInputStream ruta = new FileInputStream("/work/ezeben-archtextos.ser");
             ObjectInputStream is = new ObjectInputStream(ruta)){
            while (true) {
                TextosSerializables t = (TextosSerializables) is.readObject();
                nodoArbolUsuario actual = arbol.buscarUsuario(t.getNick());
                if (actual != null) {
                    System.out.println("Lei el texto: " + t.toString());
                    nodoTexto textoActual = new nodoTexto(t.getTexto(), 0 , t.getFecha());
                    actual.insertarTextoOrdenadoFechaAsc(textoActual);
                    app.insertarTextoMasVisto(textoActual);

                } else {
                    System.out.println("Error: Usuario '" + t.getNick() + "' no encontrado para insertar texto.");
                }
            }
        } catch (EOFException e) {
        } catch (IOException i) {
            System.out.println("Error de lectura de archivo de textos: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }





    //guardar Usuarios
    public static void guardarUsuarios(nodoArbolUsuario raiz) {
        try(FileOutputStream ff=new FileOutputStream("/work/ezeben-archusuarios.ser");
            ObjectOutputStream os=new ObjectOutputStream(ff)){
            PreOrderRec(raiz, os);
        }catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void PreOrderRec(nodoArbolUsuario actual, ObjectOutputStream oos) throws IOException{
        if(actual != null) {
            UsuarioSerializables u = new UsuarioSerializables(actual.getNick(), actual.getPassword());
            oos.writeObject(u);
            System.out.println("guardando usuario: " + actual.getNick() + " \n" + "guardando password: " + actual.getPassword());
            PreOrderRec(actual.getAnterior(), oos);
            PreOrderRec(actual.getSiguiente(), oos);
        }
    }

    public static void LeerUsuarios(ArbolUsuario arbol){
        try (FileInputStream ruta = new FileInputStream("/work/ezeben-archusuarios.ser");
             ObjectInputStream is = new ObjectInputStream(ruta)){
            while (true) {
                UsuarioSerializables u = (UsuarioSerializables) is.readObject();
                arbol.insertarUsuarios(u.getNick(), u.getPassword());
                System.out.println("Lei el texto: " + u.toString());
            }
        } catch (EOFException e) {
        } catch (IOException i) {
            System.out.println("Error de lectura de archivo de usuarios: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }
}
