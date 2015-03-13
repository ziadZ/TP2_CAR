package ressources;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Main component of the REST gateway, which will hold all the verbs PUT, GET,
 * DELETE and other methods which will allow us to download, delete and upload
 * files from our gateway.
 * 
 * Each method will create a new FTPClient which will connect at the beginning
 * of the treatment and disconnect at the end of it.
 * 
 * @author Yassine Badache, Ziad Zairi
 * 
 */
@Path("/ftpRest")
public class FTPRestService {

	// Host on which we will interact with our gateway -here 'localhost'-
	protected String host = "localhost";
	// Port number on which we will connect to the server
	protected int port = 3377;
	// Active user, used to prevent anyone not logged to use the REST gateway
	protected User currentUser = new User(null, null);
	// HTML Page generator, used to send visual responses to the user
	protected GenHTML pageGenerator = new GenHTML();

	/*
	 * ***************************************************************
	 * ****************************************************** LOGINS *
	 * ***************************************************************
	 */

	/**
	 * Logs in the user. It is a basic function which will be overridden to be
	 * able to type a user and password, and identify ourselves
	 */
	@GET
	@Path("/login")
	@Produces("text/html")
	public String login() {
		FTPClient client = new FTPClient();
		boolean connection;

		try {
			client.connect(this.host, this.port);
			connection = client.login("test", "test");

			if (connection) {
				this.currentUser.setName("test");
				this.currentUser.setPassword("test");
			}

			client.disconnect();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.pageGenerator.connect();
	}

	/**
	 * Logout. It sets the active user's data to NULL
	 */
	@GET
	@Path("/logout")
	@Produces("text/html")
	public String logout() {
		this.currentUser.setName(null);
		this.currentUser.setPassword(null);

		return this.pageGenerator.disconnect();
	}

	/*
	 * ***************************************************************
	 * ****************************************************** DELETE *
	 * ***************************************************************
	 */

	/**
	 * Delete a file and return an HTML page. The HTML page which will confirm
	 * or not if the download was a success.
	 */
	@DELETE
	@Path("/delete/{file}")
	@Produces("text/html")
	public String deleteFile(@PathParam("file") final File file) {
		FTPClient client = new FTPClient();
		try {
			client.connect("localhost", 3377);
			if (client.login(this.currentUser.getName(),
					this.currentUser.getPassword())) {

			}

			else
				return this.pageGenerator.notConnectedError();
		} catch (IOException ex) {
		}

		return this.pageGenerator.delete(file);
	}

	/* ***************************************************************
	 * ****************************************************** DOWNLOAD
	 * ***************************************************************
	 */

	/**
	 * Deletes a file and return an HTML page. The HTML page which will confirm
	 * or not if the download was a success.
	 */
	@GET
	@Path("/get/{file}")
	@Produces("text/html")
	public String getFile(@PathParam("file") final File file) {
		FTPClient client = new FTPClient();
		try {
			client.connect("localhost", 3377);
			if (client.login(this.currentUser.getName(),
					this.currentUser.getPassword())) {

			}

			else
				return this.pageGenerator.notConnectedError();
		} catch (IOException ex) {
		}

		return this.pageGenerator.notConnectedError();
	}

	/* ***************************************************************
	 * ****************************************************** UPLOAD *
	 * ***************************************************************
	 */

	/**
	 * 
	 */
	@PUT
	@Path("/put/{file}")
	@Produces("text/html")
	public String putFile(@PathParam("file") final File file) {
		FTPClient client = new FTPClient();
		try {
			client.connect("localhost", 3377);
			if (client.login(this.currentUser.getName(),
					this.currentUser.getPassword())) {

			}

			else
				return this.pageGenerator.notConnectedError();
		} catch (IOException ex) {
		}

		return this.pageGenerator.notConnectedError();
	}

	/* ***************************************************************
	 * ****************************************************** LIST ***
	 * ***************************************************************
	 */
	
	/**
	 * List the files. Basically, it prints the directory and files
	 * containted in the root "/" of the computer
	 */
	@GET
	@Produces("text/html")
	@Path("/list")
	public String getFiles() {
		FTPFile[] files = null;
		FTPClient client = new FTPClient();
		try {
			client.connect(this.host, this.port);
			if (client.login(this.currentUser.getName(),
					this.currentUser.getPassword())) {

				files = client.listFiles();
			}

			else
				return this.pageGenerator.notConnectedError();

		} catch (IOException ex) {
		}

		return this.pageGenerator.fileList(files);
	}
	
	/**
	 * Get the list of files of the directory
	 * 
	 * @param dir The directory to be printed
	 * @return List, the list of files contained into the directory
	 */
	@GET
	@Produces("text/html")
	@Path("/list/{dir: .*}")
	public String getFiles(@PathParam ("dir") final String dir) {
		FTPFile[] files = null;
		FTPClient client = new FTPClient();
		try {
			client.connect(this.host, this.port);
			if (client.login(this.currentUser.getName(),
					this.currentUser.getPassword())) {

				files = client.listFiles(dir);
			}

			else
				return this.pageGenerator.notConnectedError();

		} catch (IOException ex) {
		}

		return this.pageGenerator.fileList(files);
	}
	
	

	/**
	 * Method which will get the files and return a JSon application in order to
	 * list them.
	 */
	@GET
	@Path("/delete/JSon/{dir: .*}")
	@Produces("application/json")
	public FTPFile[] getFilesJson(@PathParam("dir") String dir) {
		FTPClient client = new FTPClient();
		try {
			client.connect("localhost", 3377);
			if (client.login(this.currentUser.getName(),
					this.currentUser.getPassword())) {
				client.cwd(dir);
				return client.listFiles();
			}

		} catch (IOException ex) {
		}

		return (new FTPFile[0]);
	}

}