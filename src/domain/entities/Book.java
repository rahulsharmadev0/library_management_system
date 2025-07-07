package domain.entities;

public record Book(String title, String author, String isbn, String pages) implements CsvSerializable {

    @Override
    public String toCsv() {
        return String.join(",", title, author, isbn, pages);
    }

    public static Book fromCsv(String csv) {
        String[] fields = csv.split(",");
        return new Book(fields[0], fields[1], fields[2], fields[3]);
    }
}



