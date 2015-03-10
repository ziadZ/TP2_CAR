package services;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;

import org.springframework.stereotype.Service;

/**
 * Service class which will contains all the methods which will be used to use
 * our REST gateway
 * 
 * @author Yassine BADACHE - Zairi Ziad
 *
 */

@Service
public class FTPService {

	private String host = "localhost";
	private FTPClient client;
	private int port = 1515;
	private User user = null;

	/**
	 * The class constructor
	 * 
	 * @param host
	 *            The host of the server on which we will connect
	 * @param port
	 *            The port on which we will connect
	 */
	public FTPService(final String host, final int port) {
		this.host = host;
		this.port = port;
		this.client = new FTPClient();
	}

	/**
	 * Login to the FTP server
	 * 
	 * @param user
	 *            The login of the user
	 * @param password
	 *            The password of the user
	 */
	public void login(String user, String password) {
		boolean logged = false;
		try {
			logged = this.client.login(user, password);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (logged) {
			System.out.println("Successfully logged");
		} else {
			System.out.println("Not logged");
		}
	}

	/**
	 * Connection
	 */
	public void connect() {
		try {
			this.client.connect(host, port);
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Successfully connected");
	}

	
	public FTPFile[] list() {
		FTPFile[] files = null;
		try {
			files = client.listFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}

}
