package cn.jimmy.travel.service;

public interface FavoriteService {
    public boolean isFavorite(String rid,int uid);

    public void add(String rid, int uid);
}
