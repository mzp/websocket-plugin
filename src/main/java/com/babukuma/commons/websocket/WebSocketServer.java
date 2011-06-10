/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/12/24
 */
package com.babukuma.commons.websocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @author babukuma
 */
public class WebSocketServer implements Runnable {
	private Logger log = Logger.getLogger(this.getClass().getName());

	// サーバのコンテキスト
	private final String contextPath;
	// Handler Factory
	private final WebSocketHandlerFactory factory;
	// サーバポート
	private final int port;

	private ServerSocket serverSocket;

	private ThreadGroup threadGroup;

	public WebSocketServer(final int port, final String contextPath,
			final WebSocketHandlerFactory factory) {
		if (port > 0) {
			this.port = port;
		} else {
			this.port = 8088;
		}

		this.contextPath = contextPath;
		this.factory = factory;
	}

	/**
	 * サーバ起動
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		serverSocket = new ServerSocket(port);

		Thread server = new Thread(this);
		threadGroup = new ThreadGroup("Babukuma WebSocket");
		server.start();
	}

	/**
	 * サーバ停止
	 * 
	 * @throws IOException
	 */
	public void stop() throws IOException {
		serverSocket.close();
		serverSocket = null;

		threadGroup.destroy();
		threadGroup = null;
	}

	public void run() {
		try {
			do {
				System.out.println("accept");
				Socket socket = serverSocket.accept();
				System.out.println("accepted");

				WebSocketThread thread = new WebSocketThread(contextPath,
						threadGroup, factory, socket);
				if (!thread.handshakeAndStart()) {
					socket.close();
					thread = null;
				}
			} while (serverSocket != null && !serverSocket.isClosed());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
