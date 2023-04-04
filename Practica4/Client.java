import java.io.UnsupportedEncodingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Client {
	private static final String IP = "localhost"; 
	private static final int PUERTO = 1300; 
	
    public static void main(String[] args) throws RemoteException, NotBoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interface interfaz = (Interface) registry.lookup("solicitudProxy"); 
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);
        Scanner sc6 = new Scanner(System.in);
        Scanner sc7 = new Scanner(System.in);
        while (true) {
            // User Menu
            System.out.println("\nCliente de Firmas electronicas...");
            System.out.println("...se puede realizar las siguientes operaciones desde un Servidor Remoto");
            System.out.println("1.FIRMAR\n2.INTEGRIDAD\n3.AUNTENTICAR\n4.Salir");
            int opt = sc.nextInt();
            System.out.println("Su solicitud es:");
            if (opt == 4) {
                break;
            }
            
            switch (opt) {
            case 1:
                System.out.println("--> FIRMAR");
                break;
            case 2:
                System.out.println("--> INTEGRIDAD");
                break;
            case 3:
                System.out.println("--> AUNTENTICAR");
                break;
            }
            

           
  
            switch (opt) {
            case 1:

                System.out.println("Identidad de usuario de la clave");
                String identidadUsuario = sc2.nextLine();
                System.out.println("Indique el texto a firmar");
                String textoFirmar = sc3.nextLine();
                String claveHASH = interfaz.solicitudFirmar(identidadUsuario,textoFirmar);
                System.out.println("Usuario:"+identidadUsuario+"\nClave:"+claveHASH);
                
                break;
            case 2:

                System.out.println("Clave del usuario");
                String claveUsuario = sc4.nextLine();
                System.out.println("Texto para verificar");
                String textoParaVerificar = sc5.nextLine();
                System.out.println("Texto Cifrado");
                String textoCifrado = sc6.nextLine();
                System.out.println(interfaz.solicitudIntegridad(claveUsuario,textoParaVerificar,textoCifrado));
                
                break;
            case 3:
                System.out.println("Clave del usuario");
                String claveUsuarioAuntenticar = sc7.nextLine();
                System.out.println(interfaz.solicitudAutenticar(claveUsuarioAuntenticar));

                break;
            }
        }
    }
}
