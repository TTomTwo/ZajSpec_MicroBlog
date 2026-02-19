package WWSIS.dao;

import WWSIS.model.Wpis;
import java.util.List;

public interface WpisDao {
    List<Wpis> timeline(Integer userId);          // wpisy konkretnego usera
    List<Wpis> fullTimeline(Integer userId);      // moje + obserwowanych
    List<Wpis> publicTimeline();               // wszystkie
    void insert(Wpis wpis);
}