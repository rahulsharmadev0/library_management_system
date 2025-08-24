package com.library_management_system.app.ui.utils;

import com.library_management_system.app.ui.Route;
import com.library_management_system.app.ui.adapters.commands.ActionCommand;


@Route(title = "Feature Unavailable", description = "Indicates that a feature is not yet implemented")
public class FeatureUnavailableCommand extends ActionCommand {
    
    @Override
    protected void performAction() throws Exception {
        displayHeader("FEATURE NOT IMPLEMENTED");
        
        System.out.println("🚧 This feature is under construction!");
        System.out.println();
        System.out.println("📋 This functionality will be available in a future update.");
        System.out.println("💡 Please check back later or contact the development team.");
        System.out.println();
        System.out.println("🔄 For now, you can use the available features from the main menu.");
    }
}
