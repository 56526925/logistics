/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.response.wishu;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-06-12 15:8:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class WishuStatusResponse {

    private Date timestamp;
    private String message;
    private int code;
    private List<WishuStatusOrders> orders;

}