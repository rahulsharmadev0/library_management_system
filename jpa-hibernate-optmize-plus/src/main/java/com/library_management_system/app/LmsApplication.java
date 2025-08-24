package com.library_management_system.app;

import com.library_management_system.app.ui.Navigator;

public class LmsApplication {
	    public static void main(String[] args) {
            System.out.println("📚 Welcome to Library Management System");
            System.out.println("═".repeat(50));
            Navigator.get().start();
    }

}
