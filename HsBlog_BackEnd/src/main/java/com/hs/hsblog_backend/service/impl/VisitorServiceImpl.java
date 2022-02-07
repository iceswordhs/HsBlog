package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.constants.RedisKey;
import com.hs.hsblog_backend.entity.Visitor;
import com.hs.hsblog_backend.model.dto.VisitLogUuidTime;
import com.hs.hsblog_backend.repository.VisitorMapper;
import com.hs.hsblog_backend.service.RedisService;
import com.hs.hsblog_backend.service.VisitorService;
import com.hs.hsblog_backend.util.IpAddressUtils;
import com.hs.hsblog_backend.util.UserAgentUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/7 22:39
 */
@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    VisitorMapper visitorMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    UserAgentUtils userAgentUtils;

    @Override
    public List<Visitor> getVisitorListByDate(String startDate, String endDate) {
        return visitorMapper.getVisitorListByDate(startDate, endDate);
    }

    @Override
    public List<String> getNewVisitorIpSourceByYesterday() {
        return visitorMapper.getNewVisitorIpSourceByYesterday();
    }

    @Override
    public boolean hasUUID(String uuid) {
        return visitorMapper.hasUUID(uuid) == 0 ? false : true;
    }

    @Transactional
    @Override
    public void saveVisitor(Visitor visitor) {
        String ipSource = IpAddressUtils.getCityInfo(visitor.getIp());
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(visitor.getUserAgent());
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        visitor.setIpSource(ipSource);
        visitor.setOs(os);
        visitor.setBrowser(browser);
        if (visitorMapper.saveVisitor(visitor) != 1) {
            throw new PersistenceException("访客添加失败");
        }
    }

    @Override
    public void updatePVAndLastTimeByUUID(VisitLogUuidTime dto) {
        visitorMapper.updatePVAndLastTimeByUUID(dto);
    }

    @Transactional
    @Override
    public void deleteVisitor(Long id, String uuid) {
        //删除Redis中该访客的uuid
        redisService.deleteValueBySet(RedisKey.IDENTIFICATION_SET, uuid);
        if (visitorMapper.deleteVisitorById(id) != 1) {
            throw new PersistenceException("删除访客失败");
        }
    }
}
