package com.jimmy.service;

import com.jimmy.domain.SysLog;

import java.util.List;


public interface SysLogService {
    public void save(SysLog sysLog) throws Exception;

    public List<SysLog> findAll() throws Exception;
}
