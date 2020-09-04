package authentication;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validator {
private Pattern pattern;
private Matcher matcher;
protected boolean validate_email(String email){
pattern = Pattern.compile("^(.+)@(.+)$");
matcher = pattern.matcher(email);
return matcher.matches();
}
protected boolean validate_password(String password) {
pattern = Pattern.compile("^(.+){8,}$");
matcher = pattern.matcher(password);
return matcher.matches();
}
}
