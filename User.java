import java.util.Arrays;

public class User {
    private String name;
    private String[] follows;
    private int fCount;
    public static int maxfCount = 10;

    public User(String name) {
        this.name = name;
        this.follows = new String[maxfCount];
        this.fCount = 0;
    }

    public boolean addFollowee(String followee) {
        if (fCount < maxfCount && !follows(followee)) {
            follows[fCount++] = followee;
            return true;
        }
        return false;
    }

    public boolean follows(String name) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i] != null && follows[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String[] getfFollows() {
        return Arrays.copyOf(follows, fCount);
    }

    public String getName() {
        return name;
    }

    public String[] getFollowees() {
        return follows;
    }

    public int getfCount() {
        return fCount;
    }

    @Override
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}