package utilities;

public class TokenManager {
	
	private static String token;

    public static void setToken(String adminToken) {
        token = adminToken;
    }

    public static String getToken() {
        return token;
    }
	 

}
