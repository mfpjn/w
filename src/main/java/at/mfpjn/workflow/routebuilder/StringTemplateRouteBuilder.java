package at.mfpjn.workflow.routebuilder;

import org.apache.camel.builder.RouteBuilder;

public class StringTemplateRouteBuilder extends RouteBuilder {

	private String recipientEmail;

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public void configure() throws Exception {
		from("direct:emailConfirmation").to(
				"string-template:templates/email.tm").to(
				"smtps://smtp.gmail.com?username=andatu7@gmail.com&password=andatuASE&to="
						+ recipientEmail);
	}
}