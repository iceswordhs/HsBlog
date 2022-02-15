package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.entity.VisitLog;
import com.hs.hsblog_backend.model.dto.VisitLogUuidTime;
import com.hs.hsblog_backend.repository.VisitLogMapper;
import com.hs.hsblog_backend.service.VisitLogService;
import com.hs.hsblog_backend.util.IpAddressUtils;
import com.hs.hsblog_backend.util.UserAgentUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/7 22:22
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {

    @Autowired
    VisitLogMapper visitLogMapper;
    @Autowired
    UserAgentUtils userAgentUtils;

    @Override
    public List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate) {
        return visitLogMapper.getVisitLogListByUUIDAndDate(uuid, startDate, endDate);
    }

    @Override
    public List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday() {
        return visitLogMapper.getUUIDAndCreateTimeByYesterday();
    }

    @Transactional
    @Override
    public void saveVisitLog(VisitLog log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        log.setIpSource(ipSource);
        log.setOs(os);
        log.setBrowser(browser);
        if (visitLogMapper.saveVisitLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional
    @Override
    public void deleteVisitLogById(Long id) {
        if (visitLogMapper.deleteVisitLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
