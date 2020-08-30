package edu.escuelaing.arep;

import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Class of a Http Server
 * @author Juan Camilo Angel Hernandez
 */
public class HttpServer {

    /**
     * Return the port that the http server use
     * @return the port to use
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }

    /**
     * Run the http server
     * @throws IOException
     */
    public void run() throws IOException {
        while(true){
            ServerSocket serverSocket = null;
            int port = getPort();
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port:" + getPort());
                System.exit(1);
            }

            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();

            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("GET")){
                    String[] endpoint = inputLine.split("/");
                    String[] resource = endpoint[1].split(" ");
                    if(resource[0].contains("png")){
                        getImagen("/src/main/resources/static/"+resource[0],clientSocket.getOutputStream());
                    }
                    if(resource[0].contains("html")) {
                        getResource("/src/main/resources/static/"+resource[0],clientSocket.getOutputStream(),"html");
                    }
                    if(resource[0].contains("js")) {
                        getResource("/src/main/resources/static/"+resource[0],clientSocket.getOutputStream(),"json");
                    }
                }
                if (!in.ready()) {
                    break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }

    /**
     * Method to get a image to load
     * @param path The path of the image
     * @param outputStream The outputStream of the client socket
     */
    public static void getImagen(String path, OutputStream outputStream){
        try {
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + path));
            ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);
            ImageIO.write(image, "PNG", ArrBytes);
            out.writeBytes("HTTP/1.1 200 OK \r\n"
            + "Content-Type: image/png \r\n"
            + "\r\n");
            out.write(ArrBytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get a resource as html or js
     * @param path The path of the resource
     * @param outputStream The outputStream of the client socket
     * @param type The type of the resource
     */
    private static void getResource(String path, OutputStream outputStream,String type){
        try {
            BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir")+path));
            String out = "";
            String line;
            while ((line = in.readLine()) != null){
                out = out + line;
            }
            outputStream.write(("HTTP/1.1 201 OK\r\n"
                    + "Content-Type: text/"+type+";"
                    + "charset=\"UTF-8\" \r\n"
                    + "\r\n"
                    + out).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
