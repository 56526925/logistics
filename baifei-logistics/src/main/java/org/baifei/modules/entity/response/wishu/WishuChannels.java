/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.response.wishu;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-12 17:25:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class WishuChannels {

    private boolean support_warehouses;
    private String channel_name;
    private String channel_name_en;
    private boolean is_only_online_payment;
    private String otype;
    private boolean allow_battery;
    private List<Integer> sensitivity_types;
    private boolean is_registered;
    private String  warehouses;
    private String warehouses_return_address;
    private int channel;

}