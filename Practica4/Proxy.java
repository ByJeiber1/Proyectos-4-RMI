import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
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

public class Proxy {
	private static final int PUERTO = 1300; 
    private static final String IP = "localhost"; 
	private static final int PUERTO2 = 1401; 
    private static final int PUERTO3 = 1500; 
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        Registry registry2 = LocateRegistry.getRegistry(IP, PUERTO2);
        Interface interfaz2 = (Interface) registry2.lookup("solicitudServerA"); 
        Registry registry3 = LocateRegistry.getRegistry(IP, PUERTO3);
        Interface interfaz3 = (Interface) registry3.lookup("solicitudServerB");
        
        Remote remote = UnicastRemoteObject.exportObject(new Interface() {
        	
            

      
            @Override
            public String solicitudFirmar(String identidadUsurio,String textoFirmar) throws RemoteException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
                return interfaz2.Firmar(identidadUsurio,textoFirmar);
            }

            @Override
            public String Firmar(String mensaje,String textoFirmar) throws RemoteException {
                return null;
            }

            @Override
            public String Integridad(String claveUsuario,String textoParaVerificar,String textoCifrado) throws RemoteException {
                
                
                return null;
            }

            @Override
            public String solicitudIntegridad(String claveUsuario, String textoParaVerificar, String textoCifrado) throws RemoteException {
                String respuesta="";
                try {
                    respuesta = interfaz3.Integridad(claveUsuario, textoParaVerificar, textoCifrado);
                } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
                        | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return respuesta;
            }

            @Override
            public String solicitudAutenticar(String claveUsuario) throws RemoteException {
                return interfaz3.Autenticar(claveUsuario);
            }

            @Override
            public String Autenticar(String claveUsuario) throws RemoteException {
                // TODO Auto-generated method stub
                return null;
            };

            
        }, 1);

       Registry registry = LocateRegistry.createRegistry(PUERTO);
       System.out.println("Proxy escuchando en el puerto " + String.valueOf(PUERTO));
       registry.bind("solicitudProxy", remote); 
    }
}

