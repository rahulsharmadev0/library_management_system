# Library Management System – Architecture Analysis (2025-08-23 09:48)

## 1) Project Overview
This is a console-based Library Management System built with Java, JPA (Jakarta Persistence), and a custom CLI navigation framework. The project separates concerns into domain entities, repositories, and UI/menu command layers. It uses a simple Navigator with an AppRoute enum to wire menus and actions.

Primary goals of the app:
- Manage Book inventory (CRUD, search, sort)
- Manage Members (CRUD, search)
- Placeholders for issuing/returning books

The build uses Maven; persistence.xml is provided under both src/main/java and src/main/resources (duplicated); a docker-compose.yml and init.sql exist for DB setup.


## 2) Current Tree Structure (source of truth: provided listing)

- LICENSE
- Makefile
- README.md
- docker-compose.yml
- init.sql
- mvnw.cmd
- pom.xml
- src/
  - main/
    - java/
      - com/
        - library_management_system/
          - app/
            - LmsApplication.java
            - META-INF/
              - persistence.xml  ← duplicate of resources
            - domain/
              - entities/
                - Book.java
                - BookIssue.java
                - Member.java
                - Option.java
              - repositories/
                - BookRepository.java
                - JpaRepository.java
                - MemberRepository.java
            - ui/
              - AppRoute.java
              - MainMenu.java
              - Navigator.java
              - book_inventory_management/
                - AddBookCommand.java
                - BookInventoryMenu.java
                - DeleteBookCommand.java
                - ListAllBookIssuesCommand.java
                - ListAllBooksCommand.java
                - SearchBookCommand.java
                - SearchBookMenu.java
                - SortBookCommand.java
                - SortBookMenu.java
                - UpdateBookCommand.java
              - book_issuing_returning/
                - BookIssuingMenu.java
              - member_management/
                - DeleteMemberCommand.java
                - ListAllMembersCommand.java
                - MemberManagementMenu.java
                - RegisterNewMemberCommand.java
                - SearchMemberCommand.java
                - SearchMemberMenu.java
                - UpdateMemberCommand.java
              - utils/
                - FeatureUnavailableCommand.java
                - TableFormatter.java
                - TableFormatterDemo.java
                - commands/
                  - ActionCommand.java
                  - Command.java
                  - MenuViewCommand.java
    - resources/
      - META-INF/
        - persistence.xml
      - application.properties
- target/ … (compiled outputs)

Note: target/classes show legacy/alternative package names (e.g., app/ui/actions vs app/ui/book_inventory_management), likely from prior refactors; source is the ground truth.


## 3) Architecture and Layering

- Presentation Layer (CLI):
  - MenuViewCommand provides reusable menu UI handling (display, input, navigation).
  - ActionCommand provides a base for concrete actions (input helpers, confirmations, headers, exceptions-to-message handling).
  - Navigator + AppRoute drive a stack-based navigation system.
  - Concrete menus/actions under ui/book_inventory_management, ui/member_management, and a placeholder for issuing/returning.

- Domain Layer:
  - Entities: Book, Member, BookIssue (annotated with @Entity, lombok for boilerplate). Entities embed static TableFormatter definitions to render tabular output.
  - Option record used for menu options (title, description, route).

- Data Access Layer:
  - JpaRepository centralizes EntityManagerFactory and provides execute/executeWithTransaction helpers using lambdas.
  - BookRepository and MemberRepository implement CRUD and simple queries using JPA.

- Configuration:
  - persistence.xml exists in two locations (java and resources). application.properties also present.

- DevOps:
  - docker-compose.yml and init.sql included to provision database.


## 4) Data Flow (typical operation)
1. CLI starts at LmsApplication.main.
2. Navigator.initialize(); navigator.start(AppRoute.MainMenu).
3. MenuViewCommand displays options, gathers input.
4. Selecting an action either:
   - Navigates to another MenuViewCommand; or
   - Executes an ActionCommand (repository calls within try/catch) then returns to the current menu.
5. Repositories execute JPA operations inside unit-of-work helpers.


## 5) Strengths
- Clear separation between menu/view concerns and actionable commands via base classes.
- Navigator stack abstraction for back and exit flows.
- Repositories encapsulate JPA calls with transaction helpers.
- TableFormatter utility provides a consistent CLI table output.
- Use of records (Option) and enums (AppRoute) keeps wiring centralized and type-safe.


## 6) Issues, Risks, and Technical Debt
1) Duplicated persistence.xml
- Found under src/main/java/com/.../META-INF and src/main/resources/META-INF.
- JPA standard expects META-INF/persistence.xml under resources; duplication risks config drift and confusion.

2) Scanner lifecycle and multiple instances
- ActionCommand and MenuViewCommand both create Scanner instances on System.in. Multiple scanners on System.in can cause input contention and premature stream closure issues.

3) Error handling & UX loops
- MenuViewCommand.recursive execute() on error or invalid input can grow the call stack. While unlikely to overflow in normal use, iterative re-display would be safer.
- ActionCommand.execute swallows exceptions into a generic message and waitForEnter; no logging or rethrow for diagnostics.

4) Repository singletons
- BookRepository.getInstance() throws checked SQLException in signature but never uses JDBC; repository uses JPA only. The singleton adds statefulness without benefit. Prefer stateless access or DI; remove SQLException throws and singleton pattern unless justified.

5) Tight coupling entity -> TableFormatter
- Entities embed static presentation formatting (TableFormatter). This couples domain to UI, complicating reuse or alternative UIs (REST/GUI). Better to move table formatting to a presenter/adapter layer.

6) AppRoute.get().execute() called directly from MenuViewCommand
- Actions are executed synchronously inside the menu; exceptions are handled at multiple layers (ActionCommand and Navigator). Consider a consistent error handling strategy and unified logging.

7) Naming/package inconsistencies
- Source packages are under app/ui/book_inventory_management, but target/classes show app/ui/actions/* paths. This mismatch suggests prior refactoring; ensure packages and class paths are consistent to avoid confusion.

8) Persistence and configuration
- EntityManagerFactory is a static field initialized with Persistence.createEntityManagerFactory("lms-unit"). No shutdown hook. In long-running or test environments, EMF should be closed gracefully.
- Lack of migrations framework (init.sql is present, but no automated schema management). Consider Flyway or Liquibase.

9) Validation and constraints
- Entities lack validation annotations (e.g., @NotNull, @Size) and application-level validation. Inputs are minimally validated in CLI.

10) Testing and CI
- No tests present. No CI configuration. Increases regression risk.

11) Logging
- Uses System.out/err with emojis. Good UX for CLI, but consider a logging facade (SLF4J) for diagnostics; keep friendly messages in UI layer.

12) Navigator edge cases
- Navigator.goBack() pops and exits on empty stack. MenuViewCommand uses navigator.isAtRoot() to decide Go Back/Exit. Ensure consistent behavior when actions push new menus.


## 7) Recommendations and Improvements

A) Quick Wins (low effort, high value)
- Remove duplicate persistence.xml under src/main/java/.../META-INF; keep only under src/main/resources/META-INF. Update docs accordingly.
- Consolidate Scanner usage: create a single shared Scanner (e.g., in ActionCommand) and have MenuViewCommand reuse it; avoid multiple instances. Ensure it is never closed explicitly (let JVM close System.in).
- Normalize package names and remove stale compiled artifacts (clean target) to reflect current structure.
- Improve input loop to avoid recursion in MenuViewCommand.performAction(): use a while loop to re-prompt on invalid input.
- Adjust BookRepository.getInstance() and signatures: remove SQLException from repository methods that don’t throw it; or consistently handle/translate exceptions to unchecked.

B) Structural Improvements
- Decouple UI formatting from domain entities: move TableFormatter definitions to a presenter class (e.g., BookPresenter.tableFormat()). Keep entities pure.
- Introduce a simple Service layer between UI and repositories for business logic (e.g., issue/return workflows, validation). This will help keep commands thin and testable.
- Introduce validation (Jakarta Validation) with @NotNull/@Size in entities or DTOs, and validate inputs at service boundaries.
- Add logging via SLF4J + Logback for non-UI diagnostics; keep emojis in UI layer only.

C) Persistence & DevOps
- Add a graceful shutdown hook to close EntityManagerFactory on application exit.
- Adopt schema migrations (Flyway or Liquibase) instead of ad-hoc init.sql. If docker-compose spins up DB, integrate migrations on start.
- Externalize DB configuration to application.properties only; ensure persistence.xml aligns with it (or migrate to Spring Data JPA if planning larger changes).

D) Testing and Quality
- Add unit tests for repositories using H2 in-memory DB configured via persistence.xml test profile.
- Add tests for Navigator and MenuViewCommand (simulate input via InputStream) for edge cases.
- Set up CI (GitHub Actions) to run mvn -B -q -DskipTests=false test on PRs.

E) UX Enhancements
- Improve menu rendering: show Back/Exit options explicitly for root and non-root cases; ensure numbering consistency (e.g., list options then 0) Back, 9) Exit).
- Provide better feedback after actions (success/failure) and show affected entities in a table.


## 8) Suggested Refactoring Steps (prioritized roadmap)
1. Configuration hygiene (✓ quick win)
   - Remove duplicate persistence.xml under src/main/java/app/META-INF.
   - Ensure resources/META-INF/persistence.xml is the only active config.
2. Input handling (quick win)
   - Use a single Scanner instance (in ActionCommand); remove MenuViewCommand’s scanner field and reuse parent.
   - Replace recursive menu re-entry with a loop.
3. Repositories cleanup
   - Remove unnecessary checked exceptions (SQLException) from JPA-only repos.
   - Consider removing singleton pattern; use static factory or DI later.
4. Decouple domain from presentation
   - Move TableFormatter definitions into presenter/adapters (BookView, MemberView, BookIssueView) in a UI-specific package.
5. Add tests
   - Start with repository tests using H2; add Navigator/menu tests.
6. Logging & shutdown
   - Introduce SLF4J; add shutdown hook to close EMF.
7. Service layer for business logic
   - Introduce services for issuing/returning books; implement actual BookIssuingMenu actions.


## 9) Potential Tickets
- DEV-1: Remove duplicate persistence.xml and document correct location.
- DEV-2: Refactor Scanner usage to a single shared instance; iterative menu loop.
- DEV-3: Clean repository APIs (remove SQLException, singleton), add EMF shutdown hook.
- DEV-4: Move TableFormatter out of entities into UI presenters.
- DEV-5: Add Flyway migrations and CI workflow.
- DEV-6: Add unit tests for repositories and Navigator.
- DEV-7: Implement book issuing/returning features with a service layer.


## 10) Conclusion
The project has a solid foundation with clear separation of concerns in the CLI and repository layers. Addressing configuration duplication, input handling, and decoupling UI from domain will improve maintainability. Introducing tests, logging, and a service layer will make the system more robust and ready for future features like full issuing/returning workflows.