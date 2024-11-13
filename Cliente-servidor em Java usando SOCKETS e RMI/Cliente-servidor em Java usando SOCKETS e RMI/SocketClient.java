import javax.swing.*;
import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
             // Solicita ao usuário as coordenadas cartesianas x e y via JOptionPane
            double x = Double.parseDouble(JOptionPane.showInputDialog("Digite a coordenada x:"));
            double y = Double.parseDouble(JOptionPane.showInputDialog("Digite a coordenada y:"));

            // Configura streams para enviar e receber dados do servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(x + "," + y);
            
            // Recebe a resposta do servidor (coordenadas polares)
            String response = in.readLine();
            String[] polarCoordinates = response.split(",");
            double r = Double.parseDouble(polarCoordinates[0]);
            double theta = Double.parseDouble(polarCoordinates[1]);

            JOptionPane.showMessageDialog(null, "Coordenadas Polares:\n" +
                    "r = " + r + "\nθ = " + theta + " radianos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
