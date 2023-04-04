
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ServerB {
	private static final int PUERTO = 1500; 

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interface() {
        	
            @Override
            public String solicitudFirmar(String mensaje,String mensaje2) throws RemoteException {
                return null;
            }

            @Override
            public String Firmar(String mensaje,String mensaje2) throws RemoteException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String Integridad(String claveUsuario,String textoParaVerificar,String textoCifrado) throws RemoteException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
                try {

                    Hash hash = new Hash();
                    String respuesta1 = "";
                    String respuesta2 = "";

                    respuesta1 = hash.encriptar(textoParaVerificar, claveUsuario);

                    respuesta2 = hash.desencriptar(textoCifrado, claveUsuario);
                    
                    System.err.println(respuesta2);
                    System.err.println(respuesta1);
                    if (respuesta1.equals(textoCifrado)){
                        if (respuesta2.equals(textoParaVerificar)) {
                            return "INTEGRO";
                        }
                        return "INTEGRO";
                    }else return "NO INTEGRO";
                
                } catch (Exception e) {
                    return "NO INTEGRO";
                }
                
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
                Hash hash = new Hash(); 
                return hash.buscarClave(claveUsuario);
            };

            
        }, 3);

       Registry registry3 = LocateRegistry.createRegistry(PUERTO);
       System.out.println("Servidor A escuchando en el puerto " + String.valueOf(PUERTO));
       registry3.bind("solicitudServerB", remote); 
    }
}

