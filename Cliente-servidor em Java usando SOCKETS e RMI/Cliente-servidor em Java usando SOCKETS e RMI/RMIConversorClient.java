import javax.swing.*;
import java.rmi.*;

public class RMIConversorClient {
    public static void main(String[] args) {
        try {
            // Busca o objeto remoto pelo nome "Conversor" no servidor local usando RMI
            ConversorInterface conversor = (ConversorInterface) Naming.lookup("rmi://localhost/Conversor");
            
             // Solicita ao usuário as coordenadas cartesianas x e y via JOptionPane
            double x = Double.parseDouble(JOptionPane.showInputDialog("Digite a coordenada x:"));
            double y = Double.parseDouble(JOptionPane.showInputDialog("Digite a coordenada y:"));
            
            // Chama o método remoto para converter as coordenadas em polares
            double[] result = conversor.converterParaPolares(x, y);

            JOptionPane.showMessageDialog(null, "Coordenadas Polares:\n" +
                    "r = " + result[0] + "\nθ = " + result[1] + " radianos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
