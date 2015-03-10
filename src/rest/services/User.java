package services;

/**
 * 
 * @author Yassine BADACHE and Vincent BARILLARO
 *<br /><br />
 *         User class, used to define the user which will emit the request to
 *         the server
 * 
 */

public class User {
	// Name of the user
	public String name;
	// Password of the user
	public String password;
	// Read access of the user
	public boolean can_read;
	// Write access of the user
	public boolean can_write;

	/**
	 * User constructor
	 * <br /><br />
	 * @param name
	 *            : The name of the user
	 * @param password
	 *            : The password ot the user
	 * @param can_read
	 *            : Reading access of the user
	 * @param can_write
	 *            : Writing access of the user
	 */
	public User(String name, String password, boolean can_read,
			boolean can_write) {
		super();
		this.name = name;
		this.password = password;
		this.can_read = can_read;
		this.can_write = can_write;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getReadAccess() {
		return can_read;
	}

	public void setReadAccess(boolean can_read) {
		this.can_read = can_read;
	}

	public boolean getWriteAccess() {
		return can_write;
	}

	public void setWriteAccess(boolean can_write) {
		this.can_write = can_write;
	}

	/**
	 * Checks if the password passed as a parameter is valid (if he's equal to
	 * the password the current user has)
	 * 
	 * @param password
	 *            : The password to be checked
	 * @return If the password matches or not
	 */
	public boolean validPassword(String password) {
		return (this.password.equals(password));
	}

}
