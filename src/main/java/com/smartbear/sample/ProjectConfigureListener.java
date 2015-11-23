package com.smartbear.sample;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.model.iface.Interface;
import com.eviware.soapui.model.project.Project;
import com.eviware.soapui.model.support.ProjectListenerAdapter;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.plugins.ListenerConfiguration;
import com.eviware.soapui.support.UISupport;

@ListenerConfiguration()
public class ProjectConfigureListener extends ProjectListenerAdapter {
    static {
        SoapUI.log("-----------------------");
        SoapUI.log("ProjectConfigureListener static");
        SoapUI.log("-----------------------");
    }

    public ProjectConfigureListener() {
        SoapUI.log("-----------------------");
        SoapUI.log("ProjectConfigureListener ctor");
        SoapUI.log("-----------------------");
    }

    @Override
    public void afterLoad(Project project) {
        SoapUI.log("afterLoad");
        super.afterLoad(project);
    }

    @Override
    public void testSuiteAdded(TestSuite testSuite) {
        SoapUI.log("testSuiteAdded");
        super.testSuiteAdded(testSuite);
    }

    @Override
    public void beforeSave(Project project) {
        SoapUI.log("beforeSave");
        super.beforeSave(project);
    }

    @Override
    public void interfaceAdded(Interface iface) {
        UISupport.showInfoMessage("interfaceAdded = " + iface.getName());
        SoapUI.log("interfaceAdded");
        super.interfaceAdded(iface);
    }
}
