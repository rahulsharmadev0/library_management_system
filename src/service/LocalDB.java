package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import model.CsvSerializable;

public abstract class LocalDB<T extends CsvSerializable> {
    final List<T> list = new ArrayList<>();
    final String filePath;
    final Function<String, T> csvSerializable;

    public LocalDB(String filePath,
            Function<String, T> csvSerializable) {
        this.filePath = filePath;
        this.csvSerializable = csvSerializable;
        loadFromFile();
    }

    public T update(T newEntity, Predicate<T> predicate) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                list.set(i, newEntity);
                saveToFile();
                return newEntity;
            }
        }
        return null;
    }

    public List<T> getAll() {
        return new ArrayList<>(list);
    }

    public void add(T entity) throws IOException {
        list.add(entity);
        saveToFile();
    }

    public T remove(int index) throws IOException {
        T entity = list.remove(index);
        saveToFile();
        return entity;
    }

    public List<T> search(Predicate<T> predicate) {
        return list.stream().filter(predicate).toList();
    }

    public Optional<T> findBy(Predicate<? super T> predicate) {
        return list.stream()
                .filter(predicate)
                .findFirst();
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().forEach(line -> list.add(csvSerializable.apply(line)));
        } catch (IOException e) {
            System.out.println("Error: Could not load from " + filePath + ": " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : list) {
                writer.write(entity.toCsv());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error: Could not write.");
        }

    }

}
