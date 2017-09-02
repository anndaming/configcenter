/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-08-20 20:37 创建
 */
package org.antframework.configcenter.biz.service;

import org.antframework.configcenter.dal.dao.ProfileDao;
import org.antframework.configcenter.dal.entity.Profile;
import org.antframework.configcenter.facade.info.ProfileInfo;
import org.antframework.configcenter.facade.order.manage.QueryProfileOrder;
import org.antframework.configcenter.facade.result.manage.QueryProfileResult;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询环境服务
 */
@Service
public class QueryProfileService {
    @Autowired
    private ProfileDao profileDao;

    @ServiceExecute
    public void execute(ServiceContext<QueryProfileOrder, QueryProfileResult> serviceContext) {
        QueryProfileOrder order = serviceContext.getOrder();

        Page<Profile> page = profileDao.query(buildSearchParams(order), new PageRequest(order.getPageNo() - 1, order.getPageSize()));
        setResult(serviceContext.getResult(), page);
    }

    // 构建查询条件
    private Map<String, Object> buildSearchParams(QueryProfileOrder queryProfileOrder) {
        Map<String, Object> searchParams = new HashMap<>();
        if (queryProfileOrder.getProfileCode() != null) {
            searchParams.put("LIKE_profileCode", "%" + queryProfileOrder.getProfileCode() + "%");
        }
        return searchParams;
    }

    // 设置result
    private void setResult(QueryProfileResult result, Page<Profile> page) {
        result.setTotalCount(page.getTotalElements());

        for (Profile profile : page.getContent()) {
            ProfileInfo info = new ProfileInfo();
            BeanUtils.copyProperties(profile, info);

            result.addInfo(info);
        }
    }
}
