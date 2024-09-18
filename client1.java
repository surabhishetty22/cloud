import java.io.*;
import java.net.*;
import javax.crypto.SecretKey;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            SecretKey key = AESEncUtil.getAESKey();
            String message = "Hello Server...";
            String encryptedMessage = AESEncUtil.encrypt(message, key);
            System.out.println("Encrypted Message: " + encryptedMessage);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(encryptedMessage);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Server Response: " + in.readLine());

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
