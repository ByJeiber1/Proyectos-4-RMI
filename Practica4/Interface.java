import java.io.UnsupportedEncodingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface Interface extends Remote {
    String solicitudFirmar(String identidadUsuario,String textoFirmar) throws RemoteException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;
    String Firmar(String identidadUsuario, String textoFirmar) throws RemoteException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;
    String solicitudIntegridad(String claveUsuario,String textoParaVerificar,String textoCifrado) throws RemoteException;
    String Integridad(String claveUsuario,String textoParaVerificar,String textoCifrado) throws RemoteException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;
    String solicitudAutenticar(String claveUsuario) throws RemoteException;
    String Autenticar(String claveUsuario) throws RemoteException;
}

