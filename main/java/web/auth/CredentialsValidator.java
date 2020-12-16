package web.auth;
 
public interface CredentialsValidator {
	Boolean validateCredentials(String username, String password);
}
