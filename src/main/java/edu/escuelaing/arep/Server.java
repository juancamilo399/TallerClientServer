package edu.escuelaing.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class of the server to squared a number
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println("Enter the numbers to be squared");
        out.println("Type Bye to close the connection");
        out.println();
        String inputLine, outputLine;
        while ((inputLine = in .readLine()) != null) {
            if (inputLine.equals("Bye")) {
                out.println("Bye");
                break;
            }
            int number=Integer.parseInt(inputLine);
            outputLine = "Respuesta:" + number*number;
            out.println(outputLine);



        }
        out.close();
        in .close();
        clientSocket.close();
        serverSocket.close();
    }
}
