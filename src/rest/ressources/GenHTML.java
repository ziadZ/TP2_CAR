package ressources;

import java.io.File;

import org.apache.commons.net.ftp.FTPFile;

public class GenHTML {

	public GenHTML() {
		
	}

	/**
	 * Method to generate the HTML content for the delete action
	 * 
	 * @param file
	 *            The file that have been deleted
	 * @return String The HTML code for delete action
	 */
	public String delete(File file) {
		return "<html><title>Delete</title><body><h1>" + file.toString()
				+ " Deleted</h1></body></html>";
	}

	/**
	 * Method to generate the HTML content for the connection
	 * 
	 * @return String The HTML code for the connection
	 */
	public String connect() {
		return "<html><title>Connect</title><body><h1>Connected!</h1></body></html>";
	}

	/**
	 * Method to generate the HTML content for the disconnection
	 * 
	 * @return String The HTML code for the disconnection
	 */
	public String disconnect() {
		return "<html><title>Dieconnect</title><body><h1>Disconnected!</h1></body></html>";
	}

	/**
	 * Return HTML content for the list of files
	 * 
	 * @param files
	 *            the list of Files
	 * @return String HTML content for the list of files
	 */
	public String fileList(FTPFile[] files) {
		String s = "<ul>";
		for (FTPFile f : files) {
			if (f.isFile())
				s += "<li>" + f.toString() + "</li>";
			else if (f.isDirectory())
				s += "<li><a href=http://localhost:8080/rest/api/ftpRest>"
						+ f.toString() + "</a> </li>";
		}

		s += "</ul>";
		
		return "<html> <title>Files List</title><body>" + s + "</body></html>";
	}

	/**
	 * Return HTML content to create a form
	 * 
	 * @return String HTML content for the form
	 */
	public String createForm() {
		return "<form method=\"post\" action=\"\" name=\"Upload\">";
	}


	public String notConnectedError() {
		return "<html> <title>Not logged !</title><body> You're not logged, please login before anything else</body></html>";
	}
	
	public String anotherError () {
		return "<html> <title> Oh daymn </title> Something else went wrong, can't figure out why. 404 ? </body> </html>";
	}

}
