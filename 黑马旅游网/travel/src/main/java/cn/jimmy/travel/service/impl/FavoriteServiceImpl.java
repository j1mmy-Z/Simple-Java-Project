package cn.jimmy.travel.service.impl;

import cn.jimmy.travel.dao.FavoriteDao;
import cn.jimmy.travel.dao.impl.FavoriteDaoImpl;
import cn.jimmy.travel.domain.Favorite;
import cn.jimmy.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid);
        if (favorite == null) {
            return false;
        }
        return true;
    }

    @Override
    public void add(String rid, int uid) {
        dao.add(Integer.parseInt(rid),uid);
    }
}

