/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/12/24
 */
package com.babukuma.commons.websocket;

import java.io.OutputStream;

/**
 * @author babukuma
 * 
 */
public abstract class WebSocketHandler {
	private OutputStream __out;
	private boolean __alive;
	private String __host;
	private String __origin;
	private String __urlPath;

	void setOut(OutputStream out) {
		this.__out = out;
	}

	void setHost(String host) {
		this.__host = host;
	}

	void setOrigin(String origin) {
		this.__origin = origin;
	}

	void setUrlPath(String urlPath) {
		this.__urlPath = urlPath;
	}

	String getHost() {
		return this.__host;
	}

	String getOrigin() {
		return this.__origin;
	}

	String getUrlPath() {
		return this.__urlPath;
	}

	public boolean isAlive() {
		return __alive;
	}

	void setAlive(boolean alive) {
		this.__alive = alive;
	}

	/**
	 * byte配列メッセージを送信する。
	 * 
	 * @param message
	 */
	public void send(byte[] message) {
		try {
			__out.write(0x00);
			__out.write(message);
			__out.write(0xFF);
		} catch (Exception e) {
			onError(e);
		}
	}

	/**
	 * Connection open event
	 */
	public abstract void onOpen();

	/**
	 * メッセージを受け取った時のイベント
	 * 
	 * @param message
	 *            received data
	 */
	public abstract void onMessage(final byte[] message);

	/**
	 * Connection close event
	 */
	public abstract void onClose();

	/**
	 * Error
	 * 
	 * @param e
	 */
	public abstract void onError(final Throwable e);
}
