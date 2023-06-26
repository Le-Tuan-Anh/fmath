import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Fmath {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        // read and write string from client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != "thanks") {
            String[] tokens = inputLine.split(",");
            if (tokens.length != 3) {
                out.println("Invalid input");
                continue;
            }
            CalculatorClassloader loader = new CalculatorClassloader();
            Class<?> clazz = loader.loadClass("Calculator");
            Object calculator = clazz.getConstructor().newInstance();
            String result = (String) clazz.getMethod("cal", String.class, int.class, int.class).invoke(calculator,
                    tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
            out.println(result);
        }

        in.close();
        out.close();
        stop();
    }

    public void stop() throws Exception {
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws Exception {
        Fmath server = new Fmath();
        server.start(55555);
    }
}
