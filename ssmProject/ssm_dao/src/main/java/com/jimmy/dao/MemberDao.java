package com.jimmy.dao;

import com.jimmy.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

    @Select("select * from member where id = #{id}")
    public Member findById(String id) throws Exception;
}
