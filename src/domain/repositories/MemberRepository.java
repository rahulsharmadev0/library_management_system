package domain.repositories;
import domain.entities.Member;
import domain.services.LocalDB;

public class MemberRepository  extends LocalDB<Member>{

    public static MemberRepository instance = new MemberRepository();
    private static final String FILE_PATH = "member.dat";
   
    private MemberRepository() {
        super(FILE_PATH, Member::fromCsv);
    }
}
