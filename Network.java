
public class Network {
    private User[] users;
    private int userCount;
    public static int maxUserCount;

    public Network(int maxUserCount, boolean someFlag) {
        this.maxUserCount = maxUserCount;
        this.users = new User[maxUserCount];
        this.userCount = 0;
        // Handle the boolean flag as needed
    }


    public void addUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                System.out.println("User " + name + " already exists.");
                return;
            }
        }
        users[userCount++] = new User(name);
        System.out.println("User " + name + " added.");
    }

    public User getUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    public void addFollowee(String followerName, String followeeName) {
        User follower = getUser(followerName);
        User followee = getUser(followeeName);

        if (follower == null || followee == null) {
            System.out.println("One or both users not found in the network.");
            return;
        }

        follower.addFollowee(followeeName);
    }

    public User mostPopularUser() {
        User mostPopular = null;
        int maxFollowers = 0;

        for (User u : users) {
            if (u == null)
                break;
            int followerCount = 0;
            for (User v : users) {
                if (v == null)
                    break;
                if (v.follows(u.getName())) {
                    followerCount++;
                }
            }
            if (followerCount > maxFollowers) {
                maxFollowers = followerCount;
                mostPopular = u;
            }
        }
        return mostPopular;
    }

    public String toString() {
        String result = "Network:\n";
        for (int i = 0; i < userCount; i++) {
            result += users[i] + "\n";
        }
        return result;
    }
}
