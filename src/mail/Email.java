package mail;
import java.util.ArrayList;
public class Email {
private ArrayList<Message>Inbox;
private ArrayList<Message>Draft;
private ArrayList<Message>Sentbox;
private String Email;
private String password;
public Email(String Email, String password) {
	this.Email = Email;
	this.password = password;
	Inbox = new ArrayList<Message>();
	Draft = new ArrayList<Message>();
	Sentbox = new ArrayList<Message>();
}

public void addtoInbox(Message inbox_message) {
	this.Inbox.add(inbox_message);
}

public void addtoDraft(Message draft_message) {
	this.Draft.add(draft_message);
}

public void addtoSentbox(Message sent_message) {
	this.Sentbox.add(sent_message);
}

public ArrayList<Message>viewInbox(){
	return Inbox;
}
public ArrayList<Message>viewDraft(){
	return Draft;
}
public ArrayList<Message>viewSentBox(){
	return Sentbox;
}

public boolean editDraft(int index, String Subject, String Content) {
	if(index -1 < 0  | index-1 >= Draft.size()) {
		return false;
	}
	if(Subject.length() > 0) {
		Draft.get(index-1).setSubject(Subject);
	}
	if(Content.length() > 0) {
		Draft.get(index-1).setContent(Content);
	}
	if(index < 0 && index >= Draft.size()) {
		return false;
	}
	return true;
}

public boolean deleteDraft(int index) {
if(index-1 >= 0 && index-1 <= Draft.size()-1) {
Draft.remove(index-1);
return true;
}
return false;
}

public boolean deleteInbox(int index) {
if( index-1 >= 0 &&  index-1 <= Inbox.size()-1) {
	Inbox.remove(index-1);
	return true;
}
return false;
}

public boolean deleteSentBox(int index) {
if( index-1 >= 0 &&  index-1 <= Sentbox.size()-1) {
	Sentbox.remove(index-1);
	return true;
}
return false;
}
public String getEmail() {
	return Email;
}

}
