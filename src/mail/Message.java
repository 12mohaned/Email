package mail;

public class Message {
private String subject;
private String content;
public Message(String subject, String content) {
	this.subject = subject;
	this.content = content;
}

public String toString() {
return "Message Subject: " +subject  +"\n" +
	"Message Content: "  + content;
}
public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}
}

