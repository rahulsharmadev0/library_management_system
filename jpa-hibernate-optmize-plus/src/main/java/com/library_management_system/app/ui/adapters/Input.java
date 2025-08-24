package com.library_management_system.app.ui.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility class for collecting user input
 */
public class Input {
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter a valid value.";
    private static Input instance;

    private final List<Object> inputList;
    private final Scanner scanner;

    private Input() {
        this.inputList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void waitForEnter() {
        System.out.print("\n📱 Press Enter to continue...");
        instance.scanner.nextLine();
    }

    public Input takeConfirmation(String prompt) {
        return takeConfirmation(prompt, null);
    }

    public Input takeConfirmation(String prompt, Boolean defaultValue) {
        return take(
                prompt + " [y/n]",
                raw -> {
                    String val = raw.trim().toLowerCase();
                    if (val.equals("y") || val.equals("yes")) return true;
                    if (val.equals("n") || val.equals("no")) return false;
                    throw new IllegalArgumentException("Invalid confirmation input: " + raw);
                },
                v -> true, // no extra validation needed
                defaultValue
        );
    }


    /**
     * Retrieves the singleton instance of the Input class. This method ensures that
     * only one instance of Input exists throughout the application. If the instance
     * does not already exist, it is created. Before returning the instance, the method
     * clears the input list to avoid data conflicts during nested or repeated execution.
     *
     * @return The singleton instance of the Input class.
     */
    public static Input get() {
        if (instance == null) {
            instance = new Input();
        }

        // Clear previous inputs to prevent data leakage during repeated or nested Input usage
        instance.inputList.clear();
        return instance;
    }

    public Input takeString(String prompt) {
        return take(prompt, s->s, null, null);
    }

    public Input takeString(String prompt, Predicate<String> validator) {
        return take(prompt, s->s, validator, null);
    }

    public Input takeInt(String prompt) {
        return take(prompt, Integer::parseInt, null, null);
    }
    public Input takeInt(String prompt, Predicate<Integer> validator) {
        return take(prompt, Integer::parseInt, validator, null);
    }

    public Input takeDouble(String prompt) {
        return take(prompt, Double::parseDouble, null, null);
    }
    public Input takeDouble(String prompt, Predicate<Double> validator) {
        return take(prompt, Double::parseDouble, validator, null);
    }

    public <T> Input take(
            String prompt,
            Function<String, T> parser,
            Predicate<T> validator,
            T defaultValue) {

        while (true) {
            System.out.print("👉 " + prompt + (defaultValue != null ? " [default=" + defaultValue + "]" : "") + ": ");
            String raw = scanner.nextLine().trim();

            // Use default if blank
            if (raw.isBlank() && defaultValue != null) {
                inputList.add(defaultValue);
                return this;
            }

            try {
                T value = parser.apply(raw);

                if (validator == null || validator.test(value)) {
                    inputList.add(value);
                    return this;
                } else {
                    System.out.println("❌ Input failed validation. Try again.");
                }
            } catch (Exception e) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }




    // Generic varargs execution
    @FunctionalInterface
    public interface ExecN {
        void accept(Object... args);
    }

    public void execute(ExecN consumer) {
        consumer.accept(inputList.toArray()); // Defensive copy
    }


    public List<Object> getInputs() {
        return new ArrayList<>(inputList);
    }

}