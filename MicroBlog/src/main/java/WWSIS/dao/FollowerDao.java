package WWSIS.dao;

public interface FollowerDao {
    void follow(Integer followerId, Integer followeeId);
    void unfollow(Integer followerId, Integer followeeId);
    boolean isFollowing(Integer followerId, Integer followeeId);
}