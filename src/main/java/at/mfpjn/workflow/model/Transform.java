package at.mfpjn.workflow.model;

import org.apache.camel.language.XPath;

public class Transform {

	public String tosql(@XPath("textposted") String text,						
						@XPath("medianame") String mediaName){
		StringBuilder sb = new StringBuilder();
		sb.append("insert into logging (Id, TextPosted) values (");
		sb.append("'").append(1).append("', ");
		sb.append("'").append(text).append("', ");
		sb.append("'").append("").append("', ");
		
		System.out.println(" **** toSql returning: " + sb.toString());

        return sb.toString();
	}
}
