package com.library_management_system.app.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;
import org.reflections.Reflections;

import com.library_management_system.app.ui.adapters.commands.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Route {
    String title();
    String description() default "";
    Class<? extends Command>[] children() default {}; // parent decides order

    // --- Metadata ---
    public static record RouteData(
            Class<? extends Command> type,
            String title,
            String description
    ) {}

    // --- Graph Structure ---
    public static class RouteGraph {
        public static final String ROOT = "ROOT";

        public RouteData getRoot() {
            return getChildren(ROOT).get(0);
        }

        // key = parent class name (or ROOT), value = ordered children
        private final Map<String, List<RouteData>> adjacency = new LinkedHashMap<>();

        void addEdge(String parentKey, RouteData child) {
            adjacency.computeIfAbsent(parentKey, k -> new ArrayList<>()).add(child);
        }

        public List<RouteData> getChildren(String parentKey) {
            return adjacency.getOrDefault(parentKey, List.of());
        }

        public List<RouteData> getChildren(Class<?> parentType) {
            return getChildren(parentType.getName());
        }

        public Set<String> getAllNodes() {
            Set<String> all = new LinkedHashSet<>();
            all.add(ROOT);
            all.addAll(adjacency.keySet());
            return Collections.unmodifiableSet(all);
        }
    }

    // --- Registry Builder ---
    public static class RouteRegistry {
        private static final RouteGraph graph = new RouteGraph();
        private static final Map<Class<?>, RouteData> classToDataMap = new HashMap<>();

        public static RouteGraph getGraph() {
            return graph;
        }
        
        public static RouteData getRouteData(Class<?> clazz) {
            return classToDataMap.get(clazz);
        }

        static {
            // Scan the package
            Reflections ref = new Reflections("com.library_management_system.app.ui");
            Set<Class<?>> routeClasses = ref.getTypesAnnotatedWith(Route.class);

            // 1., create RouteData for each annotated class
            Map<Class<?>, RouteData> dataMap = new HashMap<>();
            for (Class<?> clazz : routeClasses) {
                Route ann = clazz.getAnnotation(Route.class);
                RouteData data = new RouteData(
                        (Class<? extends Command>) clazz,
                        ann.title(),
                        ann.description()
                );
                dataMap.put(clazz, data);
                classToDataMap.put(clazz, data); // Store in the accessible map
            }

            // 2. build edges based on children[] declared in each parent (connect parent → children edges)
            for (Class<?> clazz : routeClasses) {
                Route ann = clazz.getAnnotation(Route.class);
                String parentKey = clazz.getName();

                for (Class<? extends Command> childType : ann.children()) {
                    RouteData child = dataMap.get(childType);
                    if (child == null) {
                        throw new IllegalStateException(
                                "Child " + childType.getName() + " of parent "
                                + clazz.getName() + " is not annotated with @Route"
                        );
                    }
                    graph.addEdge(parentKey, child);
                }
            }

            // 3. Finally, any class not declared as someone’s child = root-level route.
            Set<Class<?>> allChildren = new HashSet<>();
            routeClasses.forEach(c -> {
                Route ann = c.getAnnotation(Route.class);
                allChildren.addAll(Arrays.asList(ann.children()));
            });

            for (Class<?> clazz : routeClasses) {
                if (!allChildren.contains(clazz)) {
                    graph.addEdge(RouteGraph.ROOT, dataMap.get(clazz));
                }
            }
        }
    }
}
