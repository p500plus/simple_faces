package security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public class Sha1PasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence arg0) {
		return DigestUtils.sha1Hex(arg0.toString());
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return encode(arg0).equalsIgnoreCase(arg1);
	}

}
