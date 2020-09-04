package authentication;
import mail.Email;
public class Authentication {
private Validator validator;
private Email email;
public Authentication(String username, String password) {
	this.email = new Email(username, password);
	validator = new Validator();
}

public Email signup(String email, String password) {
if (validator.validate_email(email) && validator.validate_password(password)) {
	return this.email;
	}
return null;
}

public Email login(String email, String password) {
	if (validator.validate_email(email) && validator.validate_password(password)) {
	return this.email;
	}
	return null;
}

public Email getEmail() {
	return this.email;
}
}
