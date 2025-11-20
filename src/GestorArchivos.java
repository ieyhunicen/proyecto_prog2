import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;


class gestorArchivos{

    public static void guardarUsuarios(NodoArbolUsuario raiz) throws IOException {
        System.out.println("guardando usuarios");
        FileOutputStream ff=new FileOutputStream("/work/inaeze-archusuarios.ser");
        ObjectOutputStream os=new ObjectOutputStream(ff);
        PreOrderRec(raiz, os);
        os.flush();     ///poner try catch y volar flush y close.
        os.close();
    }

    public static void PreOrderRec(NodoArbolUsuario actual, ObjectOutputStream oos) throws IOException {
        if(actual != null) {
            UsuarioSerializables u = new UsuarioSerializables(actual.getNick(), actual.getPassword());
            oos.writeObject(u);
            System.out.println("guardando usuario " + actual.getNick());
            PreOrderRec(actual.getAnterior(), oos);
            PreOrderRec(actual.getSiguiente(), oos);
        }
    }

    public static void LeerArchivo() throws IOException, ClassNotFoundException {           //generar el arbol de usuarios.


        try (FileInputStream ff = new FileInputStream("usuario.ser");
             ObjectInputStream is = new ObjectInputStream(ff)){
            while (true) {
                UsuarioSerializables u = (UsuarioSerializables) is.readObject();
                System.out.println(String.format("lei el usuario '%s' con clave '%s'", u.getNick(), u.getPassword()));
            }
        } catch (EOFException e) {

        }
    }

}








