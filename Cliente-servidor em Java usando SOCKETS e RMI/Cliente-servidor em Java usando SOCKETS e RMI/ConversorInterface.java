import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConversorInterface extends Remote {
    double[] converterParaPolares(double x, double y) throws RemoteException;
}
