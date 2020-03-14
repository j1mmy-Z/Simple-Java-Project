package cn.jimmy.travel.dao;

import cn.jimmy.travel.domain.Favorite;

public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);

    public int findCountByRid(int rid);

    public void add(int rid, int uid);
}
