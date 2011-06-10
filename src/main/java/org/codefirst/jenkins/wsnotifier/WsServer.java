package org.codefirst.jenkins.wsnotifier;

import hudson.init.Initializer;
import hudson.init.InitMilestone;
import hudson.model.AbstractBuild;
import java.io.IOException;
import org.webbitserver.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class WsServer implements WebSocketHandler {
    @Initializer(before=InitMilestone.COMPLETED)
    public static void init() throws IOException {
        System.out.println("start websocket server");
        WebServer webServer = WebServers.createWebServer(8081)
            .add("/jenkins", new WsServer())
            .start();
    }

    static private CopyOnWriteArrayList<WebSocketConnection> connections = new CopyOnWriteArrayList<WebSocketConnection>();
    static public void send(AbstractBuild build){
        for(WebSocketConnection con : connections){
            con.send("hi");
        }
    }

    public void onOpen(WebSocketConnection connection) {
        System.out.println("on open");
        connections.add(connection);
    }

    public void onClose(WebSocketConnection connection) {
        System.out.println("on close");
        connections.remove(connection);

    }

    public void onMessage(WebSocketConnection connection, String message) {
    }
}
