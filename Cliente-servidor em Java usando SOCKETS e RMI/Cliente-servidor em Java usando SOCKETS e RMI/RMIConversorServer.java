import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIConversorServer extends UnicastRemoteObject implements ConversorInterface {
    // Construtor do servidor, que lança RemoteException
    public RMIConversorServer() throws RemoteException {
        super();// Chama o construtor da classe UnicastRemoteObject
    }
    
    // Implementação do método de conversão para coordenadas polares
    public double[] converterParaPolares(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        return new double[] { r, theta };
    }

    public static void main(String[] args) {
        try {
            // Cria o registro RMI dentro do servidor, na porta padrão 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            RMIConversorServer server = new RMIConversorServer();
            registry.rebind("Conversor", server);
            
            System.out.println("Servidor RMI de conversão iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
