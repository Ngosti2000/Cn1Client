package com.nganga.eventbus;


/**
 * 
 *  @author steve-nganga
 */
public abstract class EventBus {

	public EventBus(String url) {
	}

	public void autoReconnect(long timeout) {
	}

	public void reconnect() {
	}

	public void connect(int timeout) {
	}

	protected abstract void onFailure(Exception ex) {
	}

	protected abstract void onConnected() {
	}

	protected abstract void onClose(int statusCode, String reason) {
	}

	public void connect() {
	}

	public void registerHandler(String address, com.nganga.evenbus.impl.Handler result) {
	}

	public void ping() {
	}

	public void setPingInterval(int milliseconds) {
	}

	public int getPingInterval() {
	}

	public void send(String address, java.util.Map message) {
	}

	public void publish(String address, java.util.Map message) {
	}

	public void send(String address, String message, com.nganga.evenbus.impl.Handler reply) {
	}

	public void send(String address, java.util.Map message, com.nganga.evenbus.impl.Handler reply) {
	}

	public void send(String address, String message) {
	}

	public void publish(String address, String message) {
	}

	public java.util.List getRegisteredAddresses() {
	}

	public void unregisterHandler(String address) {
	}

	public void close() {
	}
}
