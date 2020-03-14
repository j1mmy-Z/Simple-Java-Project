package com.jimmy.dao;

import com.jimmy.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{OrdersId})")
    public List<Traveller> findByOrdersId(String OrdersId) throws Exception;
}
