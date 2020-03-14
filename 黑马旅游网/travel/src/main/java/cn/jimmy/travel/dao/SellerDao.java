package cn.jimmy.travel.dao;

import cn.jimmy.travel.domain.Seller;

public interface SellerDao {
    public Seller findBySid(int sid);
}
