/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-20 10:40 创建
 */
package org.antframework.configcenter.web.manager.facade.order;

import org.antframework.common.util.facade.AbstractOrder;
import org.antframework.configcenter.web.manager.facade.enums.ManagerType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 添加管理员order
 */
public class AddManagerOrder extends AbstractOrder {
    // 用户名
    @NotBlank
    private String username;
    // 密码
    @NotBlank
    private String password;
    // 类型
    @NotNull
    private ManagerType type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }
}
