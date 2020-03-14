package com.jimmy.service.impl;

import com.jimmy.dao.SysLogDao;
import com.jimmy.domain.SysLog;
import com.jimmy.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
