import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ServerA {
	private static final int PUERTO = 1401; 

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interface() {
        Scanner sc = new Scanner(System.in);

            @Override
            public String solicitudFirmar(String mensaje,String textoFirmar) throws RemoteException {
                return null;
            }

            @Override
            public String Firmar(String identidadUsuario,String textoFirmar) throws RemoteException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

                Hash hash = new Hash();
                String hashGenerado= hash.generarClave();

                try {
                    String ruta = "C:/Users/jeibe/Documents/GitHub/Proyecto SD Diciembre 2022/Salida.txt";
                    
                    File file = new File(ruta);
                
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);

                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(hashGenerado  +"\n"+ identidadUsuario+"\n");

                    bw.close();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                return hashGenerado+"\nTexto Cifrado:" + hash.encriptar(textoFirmar, hashGenerado); 
            }

            @Override
            public String Integridad(String claveUsuario,String textoParaVerificar,String textoCifrado) throws RemoteException {
                return null;
            }

            @Override
            public String solicitudIntegridad(String claveUsuario, String textoParaVerificar, String textoCifrado)
                    throws RemoteException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String solicitudAutenticar(String claveUsuario) throws RemoteException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String Autenticar(String claveUsuario) throws RemoteException {
                // TODO Auto-generated method stub
                return null;
            }

            

            
        }, 2);

       Registry registry2 = LocateRegistry.createRegistry(PUERTO);
       System.out.println("Servidor A escuchando en el puerto " + String.valueOf(PUERTO));
       registry2.bind("solicitudServerA", remote); 
    }
}

