package WWSIS.service.impl;

import WWSIS.dao.FollowerDao;
import WWSIS.dao.UzytkownikDao;
import WWSIS.dao.WpisDao;
import WWSIS.model.Uzytkownik;
import WWSIS.model.Wpis;
import WWSIS.service.MicroblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroblogServiceImpl implements MicroblogService {

    @Autowired
    private UzytkownikDao uzytkownikDao;

    @Autowired
    private WpisDao wpisDao;

    @Autowired
    private FollowerDao followerDao;

    @Override
    public List<Wpis> getTimeline(Integer userId) {
        return wpisDao.timeline(userId);
    }

    @Override
    public List<Wpis> getFullTimeline(Integer userId) {
        return wpisDao.fullTimeline(userId);
    }

    @Override
    public List<Wpis> getPublicTimeline() {
        return wpisDao.publicTimeline();
    }

    @Override
    public void addWpis(Wpis wpis) {
        wpisDao.insert(wpis);
    }

    @Override
    public void registerUzytkownik(Uzytkownik uzytkownik) {
        uzytkownikDao.insert(uzytkownik);
    }

    @Override
    public Uzytkownik findUzytkownikByLogin(String login) {
        return uzytkownikDao.findByLogin(login);
    }

    @Override
    public void follow(Integer followerId, Integer followeeId) {
        followerDao.follow(followerId, followeeId);
    }

    @Override
    public void unfollow(Integer followerId, Integer followeeId) {
        followerDao.unfollow(followerId, followeeId);
    }

    @Override
    public boolean isFollowing(Integer followerId, Integer followeeId) {
        return followerDao.isFollowing(followerId, followeeId);
    }
}