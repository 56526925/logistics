package org.baifei.modules.message.service;

import java.util.List;

import org.baifei.modules.message.entity.SysMessageTemplate;
import org.baifei.common.system.base.service.JeecgService;

/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends JeecgService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
