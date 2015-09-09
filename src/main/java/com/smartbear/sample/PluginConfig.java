package com.smartbear.sample;

import com.eviware.soapui.plugins.PluginAdapter;
import com.eviware.soapui.plugins.PluginConfiguration;
import com.eviware.soapui.support.UISupport;

@PluginConfiguration(groupId = "com.smartbear.plugins", name = "Sample Plugin", version = "1.0",
        autoDetect = true, description = "Sample Plugin", infoUrl = "" )
public class PluginConfig extends PluginAdapter {
        public PluginConfig(){
                super();

                UISupport.addResourceClassLoader(getClass().getClassLoader());
        }
}