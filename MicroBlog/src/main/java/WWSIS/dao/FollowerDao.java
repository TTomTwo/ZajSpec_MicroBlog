package WWSIS.dao;

import WWSIS.model.Follower;

public interface FollowerDao {

    /**
     * Dodaje relację follower (zalogowany użytkownik śledzi innego)
     */
    void followUser(Integer followerId, Integer uzytkownikId);

    /**
     * Usuwa relację follower (przestaję śledzić)
     */
    void unfollowUser(Integer followerId, Integer uzytkownikId);

    /**
     * Sprawdza czy użytkownik śledzi innego użytkownika
     */
    boolean isFollowing(Integer followerId, Integer uzytkownikId);

}