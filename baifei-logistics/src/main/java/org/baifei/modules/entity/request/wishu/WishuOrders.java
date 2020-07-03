/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.wishu;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-11 14:10:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class WishuOrders {

    private String access_token;
    private String bid;
    private List<WishuOrder> order;

}