package com.smartbear;

import com.eviware.soapui.impl.WorkspaceImpl;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.actions.iface.tools.support.SwingToolHost;
import com.eviware.soapui.support.UISupport;
import com.eviware.x.form.XFormFactory;
import com.eviware.x.impl.swing.SwingFormFactory;
import com.smartbear.swagger.SwaggerImporter;
import com.smartbear.swagger.SwaggerUtils;

import java.net.URL;

public class StandaloneLauncher {
    public static void main(String[] args) {
        importSwagger("https://developers.lotadata.com/swagger/spec/events_firehose.json");

        System.exit(1);
    }

    private static void prepareEnvironment() {
        UISupport.setToolHost(new SwingToolHost());
        XFormFactory.Factory.instance = new SwingFormFactory();
    }

    private static void importSwagger(String path) {
        try {
            System.out.println("import starting ....");
            WorkspaceImpl workspace = new WorkspaceImpl("C:\\Users\\Vjacheslav.Vytjagov\\default-soapui-workspace.xml", null);
            WsdlProject project = new WsdlProject(workspace);

            String expUrl = new URL("https://developers.lotadata.com/swagger/spec/events_firehose.json").toURI().toURL().toString();
            SwaggerImporter imp =  SwaggerUtils.createSwaggerImporter(expUrl, project);
            imp.importSwagger(expUrl);

            System.out.println("import finished");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
