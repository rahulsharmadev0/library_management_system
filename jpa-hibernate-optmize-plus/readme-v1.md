# Navigation Strategy and Routing (readme-v1)

This document explains the navigation strategy and routing model implemented in this project. It focuses on how menus and actions are registered, resolved, and executed at runtime using a reflection-based approach with minimal configuration.

## Goals
- Treat UI navigation as data: a single immutable registry (uiCommandTree) fully defines the menu hierarchy.
- Eliminate classpath scanning and custom registries. The Route annotation carries metadata only.
- Support a clean, stack-based navigation model (navigate, goBack, exit) with consistent behavior across menus and actions.
- Minimize boilerplate when adding new commands or menus.

## Core Concepts

### 1) uiCommandTree: the registry
- Type: `Map<String, Object>` built as a LinkedHashMap to preserve order.
- Keys: Fully Qualified Class Names (FQCNs) of Command implementations.
- Values:
  - `null` means this key represents an executable Action command (leaf).
  - another `Map<String, Object>` means this key represents a Menu with children (the child keys are option targets).

Example (excerpt from Navigator.uiCommandTree):

```
MainMenu -> {
  BookInventoryMenu -> {
    AddBookCommand -> null,
    SearchBookMenu -> {
      SearchBookCommand$ByTitle -> null,
      SearchBookCommand$ByAuthor -> null
    },
    ListAllBooksCommand -> null
  },
  MemberManagementMenu -> { ... }
}
```
Note: In Java, nested classes use `$` in their binary name (e.g., `SearchBookCommand$ByTitle`). The registry must use these exact names.

Semantics:
- The first (top-most) key is considered the root menu and is used to start the application.
- A menu's options array is automatically derived from its children map keys.

### 2) Route annotation: metadata only
`@Route(title = ..., description = ...)` provides user-facing text. It does not register or discover classes. The registry (uiCommandTree) is the single source of truth.

```
@Retention(RUNTIME)
@Target(TYPE)
public @interface Route {
  String title();
  String description() default "";
  static record RouteData(Command route, String title, String description) {}
}
```

- The title is used wherever a class is shown in a menu.
- Description is informational (currently not printed in the CLI, but available).

### 3) Reflection-based instantiation rules
Navigator reflects and instantiates commands based on the node value in the registry:
- If node is `null` (Action):
  - Expect a public no-arg constructor.
- If node is a `Map` (Menu):
  - Prefer a constructor that accepts `String[]` options. Navigator passes the children keys (FQCNs) as the `options` array.
  - Fallback: If such a constructor does not exist, try a no-arg constructor.

These rules let menus know their children without any manual wiring.

### 4) Navigator responsibilities
- Holds a stack of `RouteData` entries representing the navigation path.
- Initializes the root by taking the first key in `uiCommandTree` and building a `RouteData` for it.
- Exposes methods:
  - `start()`: initializes root, runs a loop until exit; executes the current command. If the command is not a menu, Navigator auto `goBack()` after execution.
  - `navigateTo(RouteData)`: push a new route onto the stack.
  - `goBack()`: pop the current route; if stack becomes empty, `exit()`.
  - `getRoute(String className)`: find className in the registry, build and return `RouteData`.
  - `getTitle(String className)`: read `@Route.title()` or fall back to simple class name.
  - `isAtRoot()`, `exit()`, `getCurrentRoute()`.

Internals used for route resolution:
- `findNode(Map<?, ?> tree, String targetKey)`: recursive lookup to find a node anywhere in the tree.
- `buildRouteData(String, Object)`: apply instantiation rules and attach `title/description` to the created command instance.

### 5) MenuViewCommand behavior
- Base class for all menus; extends `ActionCommand`.
- Displays a header (its own title) and the list of options.
- Calculates available numeric choices:
  - `1..N` for menu options (N = options.length)
  - When not at root: adds `Go Back` and `Exit`
  - When at root: adds only `Exit`
- On user selection:
  - If within `1..N`, it resolves the selected class via `Navigator.getRoute(className)`.
    - If the resolved route is a menu, `Navigator.navigateTo(...)`.
    - If it is an action, the action is executed immediately, then the current menu re-runs.
  - If choosing `Go Back`, call `Navigator.goBack()`.
  - If choosing `Exit`, call `Navigator.exit()`.

This provides a consistent user experience for all menus and encapsulates the menu loop logic.

## How to Add New Routes

1) Implement your command class:
- For an action:
  - Implement `Command` (usually extend `ActionCommand`).
  - Provide a public no-arg constructor.
  - Annotate with `@Route(title = "...", description = "...")`.

- For a menu:
  - Extend `MenuViewCommand`.
  - Provide a public constructor: `public MyMenu(String[] options) { super("MY MENU TITLE", options); }`
  - Annotate with `@Route(title = "...", description = "...")`.

2) Register the class in `Navigator.uiCommandTree`:
- Use the fully-qualified class name as the key.
- If it’s an action, set value to `null`.
- If it’s a menu, set value to a new `LinkedHashMap<>()` and add its children keys (again as FQCNs) in the order you want them displayed.

3) If the class is a nested type, remember to use `$` in the name:
- Example: `com.example.SearchCommand$ByTitle`.

4) Optional: Confirm the title
- Ensure `@Route` is present for a nicer display; otherwise the simple class name is shown.

## Error Handling and Edge Cases

- Class not found:
  - If a class name in `uiCommandTree` is invalid, Navigator logs an error in `buildRouteData` and returns `null`. MenuViewCommand will show an error and re-run.
- Constructor mismatch:
  - For menus, if the `String[]` constructor is missing, Navigator falls back to a no-arg constructor, so the menu will still work (but won’t have dynamic options unless handled internally).
- Empty menu children:
  - A menu can have an empty map; it will display only navigation entries (Back/Exit as applicable).
- Root initialization:
  - The root is the first key of `uiCommandTree`. Ensure it points to a menu for best UX; pointing it to an action will execute once and then exit.
- Back/Exit rules:
  - `goBack()` at root triggers `exit()`.
  - Non-menu actions automatically cause Navigator to `goBack()` after execution.

## Rationale and Benefits

- Single source of truth: The entire UI hierarchy is declarative in one structure.
- No runtime scanning: Faster startup and fewer dependencies on classpath scanning libraries.
- Reflection with safe defaults: Predictable instantiation with reasonable fallbacks.
- Extensible: Adding functionality is a matter of writing a class and updating the registry.
- Testable: You can unit-test `Navigator.getRoute()` and `getTitle()` independently by injecting or inspecting `uiCommandTree`.

## Known Limitations / Future Enhancements

- Validation tooling: A build-time check could verify that all registry keys resolve to loadable classes and compatible constructors.
- Better titles for nested classes: Provide helper utilities to compute friendly names (e.g., strip enclosing class).
- i18n: Route titles could support localization.
- Pluggable registries: Allow loading `uiCommandTree` from configuration files (JSON/YAML) for dynamic menus without code changes.
- Enhanced logging: Add a proper SLF4J binding for richer logs if desired.

## Quick Reference

- `Navigator.start()` picks the first key in `uiCommandTree` as root and starts the loop.
- `Navigator.getRoute(fqcn)` resolves and instantiates a route, returning `RouteData`.
- `MenuViewCommand` displays options derived from the child keys of the current menu’s node.
- `@Route` is metadata only; registry drives routing.

This strategy keeps the navigation system simple, explicit, and flexible while minimizing coupling and boilerplate.
