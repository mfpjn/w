package at.mfpjn.workflow.routebuilder;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Peter on 10-Jun-15.
 */
public class FtpRouteBuilder extends RouteBuilder {


    public void configure() throws Exception {

        // configure properties component for credentials.properties
       // PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
      //  pc.setLocation("classpath:credentials.properties");


        ///workflow.6f.sk/web/
        //workflow
        //workflow
        // &disconnect=true

        from("file:reports/ftpSent")
                .log("Uploading file ${file:name}")
                .to("ftp://workflow@srv60.endora.cz:21/workflow?password=workflow&binary=false")
                .log("Uploaded file ${file:name} to FTP server complete.");


        // ftp://workflow@srv60.endora.cz:21/?password=workflow
        /*
        * Download files from FTP server
        */
        Thread.sleep(10000);

        from("ftp://workflow@srv60.endora.cz:21/workflow?password=workflow&binary=false")
                .to("file:reports/ftpReceived")
                .log("Downloaded file ${file:name} from FTP server complete.");
    }


}
