package mass.observation;

public class User {
	private static String username;
	private static String password;
	public static void setUsername(String username) {
		User.username = username;
	}
	public static String getUsername() {
		return username;
	}
	public static void setPassword(String password) {
		User.password = password;
	}
	public static String getPassword() {
		return password;
	}
}
