package com.smartbear.sample;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.impl.rest.RestService;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.PathUtils;
import com.eviware.soapui.plugins.ActionConfiguration;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.action.support.AbstractSoapUIAction;
import com.smartbear.ActionGroups;
import com.smartbear.soapui.raml.RamlImporter;
import com.smartbear.swagger.SwaggerImporter;
import com.smartbear.swagger.SwaggerUtils;

import java.io.File;

@ActionConfiguration(actionGroup = ActionGroups.OPEN_PROJECT_ACTIONS, separatorBefore = true)
public class ImportRamlApiAction extends AbstractSoapUIAction<WsdlProject>{
    public ImportRamlApiAction() {
        super("Import direct from Raml", "Import direct from Raml");
    }

    @Override
    public void perform(WsdlProject wsdlProject, Object o) {
        String urlString = "D:\\Echo-API.raml";
        urlString = UISupport.getDialogs().prompt("Input URL", "Input URL to Raml definition", urlString);
        try {
            String expUrl = PathUtils.expandPath(urlString, wsdlProject);

            // if this is a file - convert it to a file URL
            if (new File(expUrl).exists())
                expUrl = new File(expUrl).toURI().toURL().toString();

            RamlImporter importer = new RamlImporter(wsdlProject);
            importer.setCreateSampleRequests(true);
            RestService restService = importer.importRaml(expUrl);
        } catch (Throwable e) {
            SoapUI.logError(e);
            UISupport.showErrorMessage(e.getMessage());
        }
    }
}
