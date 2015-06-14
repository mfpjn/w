package at.mfpjn.workflow.routebuilder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * Created by Peter on 10-Jun-15.
 * Contains following camel components:
 * Properties / camel-core
 * File / camel-core
 * FTP / camel-ftp
 */
public class FtpRouteBuilder extends RouteBuilder {


    public void configure() throws Exception {

        // configure properties component for FTP credentials
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:credentialsFTP.properties");



        /*
        * Do a backup of posts to FTP server
        */
        from("file:{{directory.from}}?noop=true") // to just copy it
                .log("Uploading file ${file:name}")
                .to("{{server.address}}{{server.folder}}?password={{server.password}}&binary=false")
                .log("Uploaded file ${file:name} to FTP server complete.");

      //  Thread.sleep(5000);

        /*
        * Download files from FTP server
        */
    /*    from("{{server.address}}{{server.folder}}?password={{server.password}}&binary=false")
                .log("Downloading file ${file:name}")
                .to("file:{{directory.to}}")
                .log("Downloaded file ${file:name} from FTP server complete.");
    */
    }


}
