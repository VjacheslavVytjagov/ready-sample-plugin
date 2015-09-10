package com.smartbear.sample;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.impl.rest.RestService;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.plugins.ActionConfiguration;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.action.support.AbstractSoapUIAction;
import com.smartbear.ActionGroups;
import com.smartbear.swagger.SwaggerImporter;
import com.smartbear.swagger.SwaggerUtils;

@ActionConfiguration(actionGroup = ActionGroups.OPEN_PROJECT_ACTIONS, separatorBefore = true)
public class ImportApiAction extends AbstractSoapUIAction<WsdlProject>{
    public ImportApiAction() {
        super("Import direct from Swagger", "Import direct from Swagger");
    }

    @Override
    public void perform(WsdlProject wsdlProject, Object o) {
        String urlString = "https://developers.lotadata.com/swagger/spec/events_firehose.json";
        urlString = UISupport.getDialogs().prompt("Input URL", "Input URL to Swagger definition", urlString);
        try {
            SwaggerImporter importer = SwaggerUtils.createSwaggerImporter(urlString, wsdlProject);
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(SwaggerUtils.class.getClassLoader());
            SoapUI.log("Importing Swagger from [" + urlString + "]");
            try {
                importer.importSwagger(urlString);
            } finally {
                Thread.currentThread().setContextClassLoader(contextClassLoader);
            }
        } catch (Throwable e) {
            SoapUI.logError(e);
            UISupport.showErrorMessage(e.getMessage());
        }
    }
}
