import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public void run() throws IOException
    {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true){
            try{
                System.out.println("Server is listening on port "+port);
                Socket acceptedConection = socket.accept();
                System.out.println("Connection accepted from client "+acceptedConection.getRemoteSocketAddress());//print client ip address
                PrintWriter toClient = new PrintWriter(acceptedConection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConection.getInputStream()));
                toClient.println("Hello from the Server");
                toClient.close();
                fromClient.close();
                acceptedConection.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        try{
            Server server = new Server();
            server.run();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}