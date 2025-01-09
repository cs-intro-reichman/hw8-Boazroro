
/**
 * Represents a social network. The network has users, who follow other uesrs.
 * Each user is an instance of the User class.
 */

public class Network {
    private User[] users;
    private int userCount;
    public static int maxUserCount = 100;

    public Network() {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /**
     * Finds in this network, and returns, the user that has the given name.
     * If there is no such user, returns null.
     * Notice that the method receives a String, and returns a User object.
     */
    public User getUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    public boolean addUser(String name) {
        if (userCount >= maxUserCount || getUser(name) != null) {
            return false;
        }
        User newUser = new User(name);
        users[userCount] = newUser;
        userCount++;
        return true;
    }

    /**
     * Makes the user with name1 follow the user with name2. If successful, returns
     * true.
     * If any of the two names is not a user in this network,
     * or if the "follows" addition failed for some reason, returns false.
     */
    public boolean addFollowee(String name1, String name2) {
        User user1 = getUser(name1);
        User user2 = getUser(name2);

        if (user1 == null || user2 == null || name1.equals(name2)) {
            return false;
        }

        return user1.addFollowee(name2);
    }

    /**
     * For the user with the given name, recommends another user to follow. The
     * recommended user is
     * the user that has the maximal mutual number of followees as the user with the
     * given name.
     */
    public String recommendWhoToFollow(String name) {
        User user = getUser(name);
        if (user == null) {
            return null;
        }

        String[] followees = user.getFollowees();
        int maxMutual = 0;
        String recommendedUser = null;

        for (int i = 0; i < userCount; i++) {
            User potentialFollowee = users[i];
            if (potentialFollowee != null && !potentialFollowee.getName().equals(name)
                    && !user.follows(potentialFollowee.getName())) {
                int mutualCount = 0;
                for (String followee : followees) {
                    if (potentialFollowee.follows(followee)) {
                        mutualCount++;
                    }
                }
                if (mutualCount > maxMutual) {
                    maxMutual = mutualCount;
                    recommendedUser = potentialFollowee.getName();
                }
            }
        }
        return recommendedUser;
    }

    /**
     * Returns the name of the most popular user in the network.
     * The most popular user is the one with the highest number of followers.
     * If there are multiple users with the same number of followers, return any one
     * of them.
     * If the network is empty, return null.
     */
    public String mostPopularUser() {
        if (userCount == 0) {
            return null;
        }

        int maxFollowers = 0;
        String mostPopular = null;

        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            int followerCount = 0;
            for (int j = 0; j < userCount; j++) {
                if (users[j] != null && users[j].follows(user.getName())) {
                    followerCount++;
                }
            }
            if (followerCount > maxFollowers) {
                maxFollowers = followerCount;
                mostPopular = user.getName();
            }
        }
        return mostPopular;
    }

    @Override
    public String toString() {
        String result = "Network:\n";
        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            result += user.getName() + " -> ";
            String[] followees = user.getFollowees();
            for (int j = 0; j < user.getfCount(); j++) {
                result += followees[j];
                if (j < user.getfCount() - 1) {
                    result += ", ";
                }
            }
            result += "\n";
        }
        return result;
    }
}