/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/12/24
 */
package com.babukuma.commons.websocket;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author babukuma
 */
class WebSocketThread extends Thread implements Thread.UncaughtExceptionHandler {
	private static final byte[] CRLF = { 0x0d, 0x0a };
	// Handler Factory
	private final WebSocketHandlerFactory factory;
	private final Socket socket;
	private WebSocketHandler handler;
	private final String contextPath;
	private String urlPath;
	private String origin = null;
	private String host = null;
	private InputStream in;
	private OutputStream out;

	WebSocketThread(final String contextPath, final ThreadGroup threadGroup,
			final WebSocketHandlerFactory factory, final Socket socket) {
		super(threadGroup, "");
		this.contextPath = contextPath;
		this.socket = socket;
		this.factory = factory;
		this.setUncaughtExceptionHandler(this);
	}

	boolean handshakeAndStart() throws IOException {
		in = socket.getInputStream();
		out = socket.getOutputStream();

		int b = 0;

		byte[] buf = new byte[256];

		do {
			int index = 0;

			while ((b = in.read()) != -1) {
				buf[index] = (byte) b;

				// CRLFまで
				if (index > 0 && buf[index - 1] == 0x0d && buf[index] == 0x0a) {
					index--;
					break;
				}

				index++;
			}
			if (WebSocketUtil.isHeaderProtocol(buf)) {
				urlPath = WebSocketUtil.getUrl(buf);

				if (isBlank(urlPath)) {
					return false;
				}
				if (!urlPath.startsWith(contextPath)) {
					return false;
				}

				urlPath = urlPath.substring(contextPath.length());
			} else if (WebSocketUtil.isHeaderUpgrade(buf)) {
				// Upgrade
				if (!"WebSocket".equals(WebSocketUtil.getHeader(buf))) {
					return false;
				}
			} else if (WebSocketUtil.isHeaderConnection(buf)) {
				// Connection
				if (!"Upgrade".equals(WebSocketUtil.getHeader(buf))) {
					return false;
				}
			} else if (WebSocketUtil.isHeaderOrigin(buf)) {
				// Origin
				origin = WebSocketUtil.getHeader(buf);
			} else if (WebSocketUtil.isHeaderHost(buf)) {
				// Host
				host = WebSocketUtil.getHeader(buf);
			}

		} while (!(buf[0] == 0x0d && buf[1] == 0x0a));

		// 必須チェック
		if (isBlank(urlPath) || isBlank(origin) || isBlank(host)) {
			return false;
		}

		// ハンドラ
		handler = factory.createHandler(urlPath);
		handler.setUrlPath(urlPath);
		handler.setOrigin(origin);
		handler.setHost(host);

		// レスポンス
		out.write("HTTP/1.1 101 Web Socket Protocol Handshake".getBytes());
		out.write(CRLF);
		out.write(("Upgrade: WebSocket").getBytes());
		out.write(CRLF);
		out.write(("Connection: Upgrade").getBytes());
		out.write(CRLF);
		out.write(("Server: Babukuma WebSocketServer").getBytes());
		out.write(CRLF);
		out.write(("WebSocket-Origin: " + origin).getBytes());
		out.write(CRLF);
		out.write(("WebSocket-Location: ws://" + host + contextPath + urlPath)
				.getBytes());
		out.write(CRLF);
		out.write(CRLF);
		out.flush();

		// Open
		handler.setOut(out);
		handler.setAlive(true);
		handler.onOpen();

		// Thread開始
		this.setName("ws://" + host + urlPath);
		this.start();

		return true;
	}

	@Override
	public void run() {
		int b = 0;

		try {
			while ((b = in.read()) == 0x00) {
				byte[] buf = new byte[256];
				int index = 0;
				while ((b = in.read()) != 0xFF) {
					buf[index++] = (byte) b;
				}

				handler.onMessage(ArrayUtils.subarray(buf, 0, index));
			}
		} catch (IOException e) {
			handler.onError(e);
		}

		handler.onClose();
	}

	public void uncaughtException(final Thread t, final Throwable e) {
		handler.onError(e);
	}
}
