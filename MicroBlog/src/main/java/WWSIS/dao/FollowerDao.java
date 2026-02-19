package WWSIS.dao;

public interface FollowerDao {

    // Metoda do rozpoczęcia obserwowania przez użytkownika (follower) innego użytkownika (followee)
    void follow(Integer followerId, Integer followeeId);  // Parametr followerId to ID osoby, która zaczyna obserwować, followeeId to ID osoby, która jest obserwowana

    // Metoda do przestania obserwowania przez użytkownika (follower) innego użytkownika (followee)
    void unfollow(Integer followerId, Integer followeeId);  // Parametr followerId to ID osoby, która przestaje obserwować, followeeId to ID osoby, którą przestaje się obserwować

    // Metoda do sprawdzenia, czy użytkownik (follower) obserwuje danego użytkownika (followee)
    boolean isFollowing(Integer followerId, Integer followeeId);  // Zwraca true, jeśli follower obserwuje followee, w przeciwnym razie false
}