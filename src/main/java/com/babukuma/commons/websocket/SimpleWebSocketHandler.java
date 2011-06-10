/*
 * http://babukuma.com
 * author : babukuma
 * Date : 2009/12/24
 */
package com.babukuma.commons.websocket;

import java.io.UnsupportedEncodingException;

/**
 * @author babukuma
 */
public abstract class SimpleWebSocketHandler extends WebSocketHandler {
	@Override
	public void onMessage(final byte[] message) {
		try {
			onMessage(new String(message, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			onError(e);
		}
	}

	public void send(String message) {
		try {
			send(message.getBytes("UTF-8"));
		} catch (Exception e) {
			onError(e);
		}
	}

	/**
	 * @param message
	 *            received data
	 */
	public abstract void onMessage(final String message);
}
