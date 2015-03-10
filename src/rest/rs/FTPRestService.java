package rs;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTPClient;

import services.User;

@Path("/ftpRest")
public class FTPRestService {

	private User user;

	@GET
	@Produces("text/html")
	public String sayHello() {
		return "<h1>REST - FTP</h1>";
	}

	/**
	 * Get the list of files of the directory
	 * 
	 * @param name
	 * @return List, the list of files
	 */
	@GET
	@Path("/list/{name}")
	public String getFiles(@PathParam("name") File name) {
		String[] listfiles;
		String list = "";
		listfiles = name.list();
		for (int i = 0; i < listfiles.length; i++) {
			list = list + " " + listfiles[i];
		}
		return list;
	}

	@GET
	@Path("/connect")
	public void connect() {
		FTPClient client = new FTPClient();
		boolean successful_login = false;

		try {
			client.connect("localhost", 3377);
			successful_login = client.login("test", "test");

			if (successful_login) {
				this.user.setName("test");
				this.user.setPassword("test");
			}

			else
				System.out.println("Login failed");

			client.disconnect();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Path("/{file}")
	@DELETE
	public Response deleteFile(@PathParam("file") final File file) {
		FTPClient client = new FTPClient();

		try {
			client.connect("localhost", 3377);

			if (client.login(this.user.getName(), this.user.getPassword())) {
				file.delete();
				client.disconnect();
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Response.ok().build();
	}
}