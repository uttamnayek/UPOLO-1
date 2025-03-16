package in.uttam.passwordEncryDecry;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class PasswordEncryDecry {
	private final int key = 7;

	public String passwordEncrypt(String password) {
		System.out.println("recive at encryption");
		System.out.println("password : " + password);
		String encrypt = "";
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			c -= key;
			encrypt += c;
		}
		System.out.println("encryption : " + encrypt);
		return encrypt;
	}

	public String passwordDecrypt(String password) {
		String decrypt = "";
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			c += key;
			decrypt += c;
		}
		return decrypt;
	}
}
