import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BankClient {
    // Endereço do servidor 
    private static final String SERVER_ADDRESS = "localhost";
    // Porta do servidor
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        // Tentativa de conexão com o servidor
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado ao servidor.");
            // Solicita o número da conta ao usuário
            System.out.print("Digite o número da conta: ");
            String accountNumber = scanner.nextLine();

            // Solicita o tipo de operação (saque ou depósito)
            System.out.print("Digite a operação (saque/deposito): ");
            String operation = scanner.nextLine();

            // Solicita o valor da operação
            System.out.print("Digite o valor: ");
            double amount = scanner.nextDouble();

            // Envia os dados para o servidor
            out.writeUTF(accountNumber);
            out.writeUTF(operation);
            out.writeDouble(amount);
            out.flush();

            // Recebe a resposta do servidor e exibe ao usuário
            String response = in.readLine();
            System.out.println("Resposta do servidor: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
