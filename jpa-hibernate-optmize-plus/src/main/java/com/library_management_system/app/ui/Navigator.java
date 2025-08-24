package com.library_management_system.app.ui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

import com.library_management_system.app.ui.utils.MenuViewCommand;
import com.library_management_system.app.ui.Route.RouteData;
import com.library_management_system.app.ui.Route.RouteGraph;
import com.library_management_system.app.ui.Route.RouteRegistry;
import com.library_management_system.app.ui.adapters.commands.Command;

@NoArgsConstructor
public class Navigator {
    private ArrayDeque<RouteData> navigationStack = new ArrayDeque<>();
    private static Navigator INSTANCE = new Navigator();
    private boolean isRunning = true;

    public static Navigator get() {
        return INSTANCE;
    }

    // ==================== Navigation Stack ====================
    public void navigateTo(RouteData route) {
        if (route != null)
            navigationStack.push(route);
    }

    public void goBack() {
        if (!navigationStack.isEmpty())
            navigationStack.pop();
        if (navigationStack.isEmpty())
            exit();
    }

    public RouteData getCurrentRoute() {
        return navigationStack.peek();
    }

    public boolean isAtRoot() {
        return navigationStack.size() <= 1;
    }

    public void exit() {
        isRunning = false;
        System.out.println("\n🔃 Exiting Library Management System...");
        System.out.println("👋 Thank you for using our system!");
    }

    public boolean isRunning() {
        return isRunning;
    }

    // ==================== Start ====================
    public void start() {
        // Initialize root from uiCommandTree
        RouteData rootRoute = RouteRegistry.getGraph().getRoot();

        if (rootRoute == null) {
            System.err.println("❌ Root route not found or failed to initialize.");
            return;
        }
        navigateTo(rootRoute);

        while (isRunning && !navigationStack.isEmpty()) {
            try {
                RouteData currentRoute = getCurrentRoute();
                Command currentCommand = createCommandInstance(currentRoute);
                
                if (currentCommand != null) {
                    currentCommand.run();
                    if (!(currentCommand instanceof MenuViewCommand)) {
                        goBack();
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                System.err.println("❌ An error occurred: " + e.getMessage());
                e.printStackTrace(); // Add for debugging
                if (isAtRoot())
                    exit();
                else
                    goBack();
            }
        }
    }
    
    private Command createCommandInstance(RouteData route) throws Exception {
        Class<? extends Command> commandClass = route.type();
        
        // Check if it's a MenuViewCommand that needs options
        if (MenuViewCommand.class.isAssignableFrom(commandClass)) {
            List<String> childrenNames = getChildrenClassNames(commandClass);
            String[] options = childrenNames.toArray(new String[0]);
            return commandClass.getConstructor(String[].class).newInstance((Object) options);
        } else {
            // Regular command with no-args constructor
            return commandClass.getDeclaredConstructor().newInstance();
        }
    }

    public void reset() {
        navigationStack.clear();
        isRunning = true;
    }

    // ==================== Reflection-based Registry ====================
    
    public RouteData getRoute(String className) {
        // Find route by class name
        RouteGraph graph = RouteRegistry.getGraph();
        
        // Search through all nodes in the graph
        for (String parentKey : graph.getAllNodes()) {
            List<RouteData> children = graph.getChildren(parentKey);
            for (RouteData child : children) {
                if (child.type().getSimpleName().equals(className) || 
                    child.type().getName().equals(className)) {
                    return child;
                }
            }
        }
        return null;
    }
    
    public String getTitle(String className) {
        RouteData route = getRoute(className);
        return route != null ? route.title() : className;
    }
    
    public List<String> getChildrenClassNames(Class<? extends Command> parentClass) {
        RouteGraph graph = RouteRegistry.getGraph();
        List<RouteData> children = graph.getChildren(parentClass);
        List<String> classNames = new ArrayList<>();
        
        for (RouteData child : children) {
            classNames.add(child.type().getSimpleName());
        }
        
        return classNames;
    }
    
    public List<String> getRootClassNames() {
        RouteGraph graph = RouteRegistry.getGraph();
        List<RouteData> rootChildren = graph.getChildren(RouteGraph.ROOT);
        List<String> classNames = new ArrayList<>();
        
        for (RouteData child : rootChildren) {
            classNames.add(child.type().getSimpleName());
        }
        
        return classNames;
    }

}
