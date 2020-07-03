/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.response.fpx;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-10 16:16:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class FpxWareHouseResponse {

    private String result;
    private String msg;
    private String errors;
    private List<FpxWareHouseResponseData> data;

}