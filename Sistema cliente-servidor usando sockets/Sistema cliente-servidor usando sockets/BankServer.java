import java.io.*;
import java.net.*;
import java.util.*;

public class BankServer {
    // Porta onde o servidor irá escutar as conexões
    private static final int PORT = 12345;
    // Nome do arquivo que armazena as contas e saldos
    private static final String ACCOUNTS_FILE = "contas.txt";
    // Mapa para armazenar o número da conta e seu saldo
    private static Map<String, Double> accounts = new HashMap<>();

    public static void main(String[] args) {
        // Carrega as contas do arquivo ao iniciar o servidor
        loadAccounts();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor rodando na porta " + PORT);
            // Loop para aceitar conexões de clientes continuamente
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    // Manipula a conexão com o cliente
                    handleClient(clientSocket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar as contas do arquivo
    private static void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            // Lê cada linha do arquivo
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" "); // Divide a linha em partes
                String accountNumber = parts[0]; // Primeiro elemento é o número da conta
                double balance = Double.parseDouble(parts[1]); // Segundo elemento é o saldo
                // Armazena no mapa
                accounts.put(accountNumber, balance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para manipular a conexão com o cliente
    private static void handleClient(Socket clientSocket) {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
             
            // Lê os dados enviados pelo cliente
            String accountNumber = in.readUTF();
            String operation = in.readUTF();
            double amount = in.readDouble();
            
            // Processa a transação e envia a resposta
            String response = processTransaction(accountNumber, operation, amount);
            out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para processar a transação
    private static String processTransaction(String accountNumber, String operation, double amount) {
        // Verifica se a conta existe
        if (!accounts.containsKey(accountNumber)) {
            return "Conta não encontrada."; // Resposta se a conta não for encontrada
        }

        // Obtém o saldo atual da conta
        double currentBalance = accounts.get(accountNumber);
        switch (operation.toLowerCase()) { // Verifica a operação solicitada
            case "deposito":
                currentBalance += amount; // Adiciona o valor ao saldo
                accounts.put(accountNumber, currentBalance); // Atualiza o saldo
                return "Depósito realizado. Saldo atual: " + currentBalance;

            case "saque":
                // Verifica se há saldo suficiente para o saque
                if (currentBalance >= amount) {
                    currentBalance -= amount; // Subtrai o valor do saldo
                    accounts.put(accountNumber, currentBalance); // Atualiza o saldo
                    return "Saque realizado. Saldo atual: " + currentBalance;
                } else {
                    return "Saldo insuficiente para saque."; // Mensagem de erro para saldo insuficiente
                }

            default:
                return "Operação inválida."; // Mensagem para operações não reconhecidas
        }
    }
}
