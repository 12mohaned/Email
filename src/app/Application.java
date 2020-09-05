package app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import authentication.Authentication;
import mail.Message;
public class Application {
private static Application application; 
static {
try {
	application = new Application();
} 
catch (Exception e) {
	throw new ExceptionInInitializerError(e);
		    }
       }
BufferedReader br;
Authentication authentication;
String email;
String password;
int input;
boolean is_authenticated = false;
String messageName;
String messageContent;
int message_no ;

public Application() throws NumberFormatException, IOException {
	 br = new BufferedReader(new InputStreamReader(System.in));
	 System.out.println("Welcome to our Email Application");
	 while(input != 3) {
		   System.out.println("if you want to signup : 1\n"+"if you want to login : 2\n" +"if you want to quit : 3");
		   input = Integer.parseInt(br.readLine());
		   if(input == 3) {
			   break;
		   }
		   if(input < 1 & input > 2) {
				System.out.println("Please Enter a relevant keyword");
			}else {
			System.out.println("Enter your Email");
			email = br.readLine();
			System.out.println("Enter your Password (should be atleast 9 characters)");
			password = br.readLine();
			authentication = new Authentication(email, password);
			if(checkAuthentication(authentication, email, password, input)) {
				System.out.println("Welcome to your Email " + authentication.getEmail().getEmail());
				break;
			}else {
			System.out.println("Email or password is wrong");
			}
			}
			
		}
	 if(! is_authenticated) {
		 System.out.println("Bye !");
	 }else {
		System.out.println("Enter the following commands");
		System.out.println("if you want to view draft type: 4\n"+
		"if you want to view inbox type: 5\n" +
		"if you want to view sentbox type: 6\n"+
		"if you want to save draft type: 7\n" +
		"if you want to delete email type: 8\n"+
		"if you want to send email type: 9\n"+
		"if you want to edit draft type: 0\n"+
		"if you want to delete draft type: -1\n"+
		"if you want to quit : 3"); 
	 }
while(is_authenticated & input != 3) {
	input = Integer.parseInt(br.readLine());
	if(input == 3) {
		break;
	}
	if(input == 4 |input == 5 | input == 6){
		System.out.println(viewMessages(input));
	}else {
		if(input == 8 | input == - 1){
			 if(!delete_messages(input)) {
				 System.out.println("you're trying to delete a message that doesn't exists !");
			 }else {
				 System.out.println("Message is Deleted");
			 }
		}
		else{
			if(input == 7 |input == 0) {
				if(!handleDraftMessages(input)) {
					 System.out.println("you're trying to edit a message that doesn't exists !");
				}else {
					if(input == 0) {
					 System.out.println("Message is Edited");
					}else {
						 System.out.println("Message is Drafted");

					}
				}
		}else {
			if(input == 9) {	
				if(! sendEmail()) {
				System.out.println("Message is sent");
				}else {
				System.out.println("Message is drafted");
				}
			}else {
				System.out.println("Please Enter a relevant keyword");
			}
		}
		}
	}
}
}

/*
 * Delete the Messages in the Draft or Sentbox or Inbox based on the user choice
 */
public boolean delete_messages(int input) throws NumberFormatException, IOException {
	int message_no;
	System.out.println("type the position of the message you wish to edit, i.e : if you want to edit the message number 11 in the list type 11");
	if(input == 8) {
		System.out.println("if you want to delete from inbox type 1\n"+
				"if you want to delete from sentbox type 2");
		message_no = Integer.parseInt(br.readLine());
		if(message_no == 2 | message_no == 1 ) {
			if(message_no == 1) {
				System.out.println(authentication.getEmail().viewInbox());
				message_no = Integer.parseInt(br.readLine());
				return authentication.getEmail().deleteInbox(message_no);
			}else {
				System.out.println(authentication.getEmail().viewSentBox());
				message_no = Integer.parseInt(br.readLine());
				return authentication.getEmail().deleteSentBox(message_no);
			}
			
		}
	}else {
		System.out.println(authentication.getEmail().viewDraft());
		message_no = Integer.parseInt(br.readLine());
		return authentication.getEmail().deleteDraft(message_no);
	}
	return true;
}

/*
 * Return the Messages in the Email whether it's in Draft or Inbox or SentBox
 */
public ArrayList<Message> viewMessages(int input){
ArrayList<Message> Messages = new ArrayList<Message>();
if(input == 4) {
	Messages = authentication.getEmail().viewDraft();
}else{
	if(input == 5) {
		Messages = authentication.getEmail().viewInbox();
}	else {
		Messages = authentication.getEmail().viewSentBox();
}
}	
return Messages;
}

/*
 * Perform operations on the Messages in The draft(Deleting a message or Editing a message)
 */
public boolean handleDraftMessages(int input) throws NumberFormatException, IOException {
	if(input == 0) {
		System.out.println("type the position of the message you wish to edit, i.e : if you want to edit the message number 11 in the list type 11");
		message_no = Integer.parseInt(br.readLine());
		}
	if(input == 7 ) {
		System.out.println("Enter the Subject");
		messageName = br.readLine();
		System.out.println("Enter the content");
		messageContent = br.readLine();
		authentication.getEmail().addtoDraft(new Message(messageName, messageContent));
	}else {
		System.out.println(authentication.getEmail().viewDraft());
		System.out.println("Change the Subject to:");
		messageName = br.readLine();
		System.out.println("Change the content to:");
		messageContent = br.readLine();
		return authentication.getEmail().editDraft(message_no, messageName, messageContent);
	}
return true;
}

/*
 * Save the Message in the Sentbox after the user send it
 */
public boolean sendEmail() throws IOException {
	boolean is_drafted = false;
	System.out.println("Enter the Subject :");
	messageName = br.readLine();
	System.out.println("Enter the content :");
	messageContent = br.readLine();
	System.out.println("if you want to draft the message click -1 else click 9");
	input = Integer.parseInt(br.readLine());
	if(input ==9) {
	authentication.getEmail().addtoSentbox(new Message(messageName, messageContent));
	}else {
		if(input == -1) {
			authentication.getEmail().addtoDraft(new Message(messageName, messageContent));
			is_drafted = true;
		}
	}
return is_drafted;
}

/*
 * Check the validity of the account and the passowrd the user provides 
 */
public boolean checkAuthentication(Authentication authentication, String email, String password, int input) {
	if(authentication.login(email, password) != null & input == 1 
			   | authentication.signup(email, password) != null & input == 2) {
				is_authenticated = true;
	}else {
		is_authenticated = false;

	}
return is_authenticated;
}
public static Application getInstance() {
return application;
}
}
