package org.baifei.modules.entity.response.fpx;


import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-10 17:56:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class FpxChannelsResponse {

    private String result;
    private String msg;
    private String errors;
    private List<FpxChannelsResponseData> data;

}
