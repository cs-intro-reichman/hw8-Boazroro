
public class User {
    private String name;
    private String[] follows;
    private int fCount;
    public static int maxfCount = 10;
    private boolean someFlag;

    public User(String name) {
        this.name = name;
        this.follows = new String[maxfCount];
        this.fCount = 0;
    }

    public User(String name, boolean someFlag) {
        this.name = name;
        this.follows = new String[maxfCount];
        this.fCount = 0;
        this.someFlag = someFlag;
        // Handle the boolean flag as needed
    }

    // Existing methods...

    // Adds a followee
    public void addFollowee(String followeeName) {
        if (fCount >= maxfCount) {
            System.out.println("Cannot follow more users.");
            return;
        }
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equals(followeeName)) {
                System.out.println("Already following " + followeeName);
                return;
            }
        }
        follows[fCount++] = followeeName;
        System.out.println(name + " is now following " + followeeName);
    }

    // Removes a followee
    public void removeFollowee(String followeeName) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equals(followeeName)) {
                for (int j = i; j < fCount - 1; j++) {
                    follows[j] = follows[j + 1];
                }
                follows[--fCount] = null;
                System.out.println(followeeName + " removed from follow list of " + name);
                return;
            }
        }
        System.out.println(followeeName + " is not followed by " + name);
    }

    // Check if this user follows another user
    public boolean follows(String followeeName) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equals(followeeName)) {
                return true;
            }
        }
        return false;
    }

    // Count mutual followees
    public int countMutual(User other) {
        int count = 0;
        for (int i = 0; i < fCount; i++) {
            if (other.follows(follows[i])) {
                count++;
            }
        }
        return count;
    }

    // Check if this user is a friend of another
    public boolean isFriendOf(User other) {
        return this.follows(other.name) && other.follows(this.name);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder(name + " -> ");
        for (int i = 0; i < fCount; i++) {
            ans.append(follows[i]).append(" ");
        }
        return ans.toString();
    }
}