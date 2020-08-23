package edu.escuelaing.arep;

import java.net.MalformedURLException;
import java.net.URL;

public class URLReader {
    public static void main( String[] args )  {
        URL url = null;
        try {
            url = new URL("http://campusvirtual.escuelaing.edu.co:8080/moodle/mod/assign/view.php?id=34731#file");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("Protocol: "+ url.getProtocol());
        System.out.println("Host: "+ url.getHost());
        System.out.println("Path: "+ url.getPath());
        System.out.println("File: "+ url.getFile());
        System.out.println("Query: "+ url.getQuery());
        System.out.println("Ref: "+ url.getRef());
        System.out.println("Authority: "+ url.getAuthority());
        System.out.println("Port: "+ url.getPort());
    }
}

