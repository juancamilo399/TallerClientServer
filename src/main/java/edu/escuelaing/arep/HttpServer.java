package edu.escuelaing.arep;

import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class HttpServer {

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }

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


    public static void getImagen(String tipo, OutputStream outputStream){
        try {
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + tipo));
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

    private static void getResource(String ruta, OutputStream outputStream,String type){
        try {
            BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir")+ruta));
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
