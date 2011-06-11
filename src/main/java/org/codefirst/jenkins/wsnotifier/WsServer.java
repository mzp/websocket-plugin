package org.codefirst.jenkins.wsnotifier;

import hudson.init.Initializer;
import hudson.init.InitMilestone;
import hudson.model.AbstractBuild;
import java.io.IOException;
import org.webbitserver.*;
import java.util.concurrent.CopyOnWriteArrayList;
import net.sf.json.JSONObject;


public class WsServer implements WebSocketHandler {
    private static WebServer webServer = null;

    public static void reset(int port) throws IOException {
        System.out.println("start websocket server");
        if(webServer){
            webServer.stop();
        }
        webServer = WebServers.createWebServer(port)
            .add("/jenkins", new WsServer())
            .start();
    }

    public static void reset() throws IOException {
        reset(8081);
    }

    static private CopyOnWriteArrayList<WebSocketConnection> connections = new CopyOnWriteArrayList<WebSocketConnection>();
    static public void send(AbstractBuild build){
        String json = new JSONObject()
            .element("project", build.getProject().getName())
            .element("number" , new Integer(build.getNumber()))
            .element("result" , build.getResult().toString())
            .toString();

        for(WebSocketConnection con : connections){
            con.send(json);
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
