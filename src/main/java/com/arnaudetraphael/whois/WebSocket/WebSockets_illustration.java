/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnaudetraphael.whois.WebSocket;


import javax.naming.NamingException;

import java.io.IOException;
import java.util.Vector;
/**
 *
 * @author arnau
 */
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.arnaudetraphael.whois.JNDI_DNS.JNDI_DNS;

//https://tyrus-project.github.io/documentation/1.12/user-guide.html#getting-started
public class WebSockets_illustration {

    /**
     * Danger : il faut que le constructeur de 'My_ServerEndpoint' soit bien
     * accessible par le serveur WebSockets. Ne pas oublier 'static'!
     */
    @ServerEndpoint(value = "/WebSockets_illustration")
    public static class My_ServerEndpoint {

        @OnClose
        public void onClose(Session session, CloseReason close_reason) {
            System.out.println("onClose: " + close_reason.getReasonPhrase());
        }

        @OnError
        public void onError(Session session, Throwable throwable) throws IOException {
            System.out.println("onError: " + throwable.getMessage());
            session.getBasicRemote().sendText(throwable.getMessage());
        }

        @OnMessage
        public void onMessage(Session session, String message) throws NamingException, IOException {
            System.out.println("Message from JavaScript: " + message);
            new JNDI_DNS();
            JNDI_DNS.setAdresse(message);
            String aux = "";
            session.getBasicRemote().sendText("Informations sur le nom de domaine : "+ message);
            Vector<String> v = JNDI_DNS.getDnsRecords(JNDI_DNS.getAttrIDs(), JNDI_DNS.getDc(), JNDI_DNS.getAdresse());
            for (int i = 0 ; i<v.size();i++){
                System.out.println(v.get(i));
                aux = (String) v.get(i);
                session.getBasicRemote().sendText(aux);
            }
        }

        @OnOpen
        public void onOpen(Session session, EndpointConfig ec) throws java.io.IOException, NamingException {
            System.out.println("OnOpen... " + ec.getUserProperties().get("Author"));
           
        }

        
    }

    
}

