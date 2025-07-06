package service;
import model.Member;

public class MemberService  extends LocalDB<Member>{

    public static MemberService instance = new MemberService();
    private static final String FILE_PATH = "member.dat";
   
    private MemberService() {
        super(FILE_PATH, Member::fromCsv);
    }
}
