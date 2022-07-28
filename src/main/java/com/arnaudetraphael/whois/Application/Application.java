/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnaudetraphael.whois.Application;

import com.arnaudetraphael.whois.JNDI_DNS.JNDI_DNS;
import com.arnaudetraphael.whois.WebSocket.WebSockets_illustration;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import org.glassfish.tyrus.server.Server;

/**
 *
 * @author arnau
 */

public class Application {
    public static void main(String[] args) throws NamingException {
        
        Map<String, Object> user_properties = new HashMap<String, Object>();
        user_properties.put("Author", "arnaudetraph");

        Server server = new Server("localhost", 1963, "/arnaudetraph", user_properties /* or 'null' */,
                WebSockets_illustration.My_ServerEndpoint.class);
        try {
            server.start();
            // The Web page is launched from Java:
            // Desktop.getDesktop().browse(FileSystems.getDefault().getPath("web" +
            // File.separatorChar + "\\index.html").toUri());
             Desktop.getDesktop().browse(FileSystems.getDefault().getPath("src\\main\\java\\com\\arnaudetraphael\\whois\\WebSocket\\web\\index.html").toUri());
            //Desktop.getDesktop().browse(FileSystems.getDefault()
                    //.getPath("Whois" + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar
                    //        + "java" + File.separatorChar + "com" + File.separatorChar + "arnaudetraphael"
                    //        + File.separatorChar + "whois" + File.separatorChar + "WebSocket" + File.separatorChar
                    //        + "web" + File.separatorChar + "index.html")
                    //.toUri());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please press a key to stop the server...");
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }

    }
}
