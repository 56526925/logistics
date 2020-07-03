/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.ft;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-24 11:11:6
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class FtRequestStep1 {

    private String UAccount;
    private String Password;
    private String Token;
    private List<FtOrderList> OrderList;

}