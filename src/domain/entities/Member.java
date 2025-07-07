package domain.entities;

public record Member(String id, String name, String email, String phone) implements CsvSerializable {

    @Override
    public String toCsv() {
        return String.join(",", id, name, email, phone);
    }


    public static Member fromCsv(String csv) {
        String[] fields = csv.split(",");
        return new Member(fields[0], fields[1], fields[2], fields[3]);
    }
}

