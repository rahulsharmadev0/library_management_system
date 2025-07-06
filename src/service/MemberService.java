package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Member;

public class MemberService {

    public static MemberService instance = new MemberService();

    private final String FILE_PATH = "member.dat";
    private List<Member> members = new ArrayList<>();

    private MemberService() {
        loadMembersFromFile();
    }

    private void loadMembersFromFile() {
        try (BufferedReader fr = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = fr.readLine()) != null) {
                members.add(Member.fromCsv(line));
            }
        } catch (IOException e) {
            // File might not exist yet, which is okay
            System.out.println("Info: Could not load members: " + e.getMessage());
        }
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) throws IOException {
        members.add(member);
        saveToFile();
    }

    public Member removeMember(int index) throws IOException {
        Member member = members.remove(index);
        saveToFile();
        return member;
    }

    public void updateMember(Member member) throws IOException {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).id().equals(member.id())) {
                members.set(i, member);
                saveToFile();
                return;
            }
        }
        // Member with given ID not found
        throw new IllegalArgumentException("Member with ID " + member.id() + " not found");
    }

    public boolean memberExists(String id) {
        return members.stream().anyMatch(member -> member.id().equals(id));
    }

    public Member findMemberById(String id) {
        return members.stream()
                .filter(member -> member.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void saveToFile() throws IOException {
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Member member : members) {
                fw.write(member.toCsv());
                fw.newLine();
            }
        }
    }
}
