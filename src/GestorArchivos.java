import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;


class GestorArchivos{

    public static void guardarUsuarios(nodoArbolUsuario raiz) {
        try(FileOutputStream ff=new FileOutputStream("/work/inaeze-archusuarios.ser");
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
            System.out.println("guardando usuario: " + actual.getNick() + " \n"  + "guardando password: " + actual.getPassword());
            PreOrderRec(actual.getAnterior(), oos);
            PreOrderRec(actual.getSiguiente(), oos);
        }
    }

    public static void LeerArchivo(ArbolUsuario arbol){           //generar el arbol de usuarios.
        try (FileInputStream ff = new FileInputStream("/work/inaeze-archusuarios.ser");
             ObjectInputStream is = new ObjectInputStream(ff)){
            while (true) {
                UsuarioSerializables u = (UsuarioSerializables) is.readObject();
                arbol.insertarUsuarios(u.getNick(), u.getPassword());
                System.out.println(String.format("lei el usuario '%s' con passsword '%s'", u.getNick(), u.getPassword()));
            }
        } catch (EOFException e) {

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

}







