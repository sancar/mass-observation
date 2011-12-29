package mass.observation;

/** 
 * A class to keep basic user info.
 * 
 *  @author Oguz Demir
 *  @version 1.0
 *  
 */

public class User {
	private static String username;
	private static String password;
	
	
	/**
	 * Sets the username of this user.
	 * @param username Username of this user.
	 */
	public static void setUsername(String username) {
		User.username = username;
	}
	
	/**
	 * Gets the username of this user.
	 * @return Username of this user.
	 */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * Sets the password of this user.
	 * @param password Password of this user.
	 */
	public static void setPassword(String password) {
		User.password = password;
	}
	
	/**
	 * Gets the password of this user.
	 * @return Password of this user.
	 */
	public static String getPassword() {
		return password;
	}
}
