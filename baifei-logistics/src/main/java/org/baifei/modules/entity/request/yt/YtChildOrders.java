package org.baifei.modules.entity.request.yt;

import lombok.Data;

import java.util.List;
@Data
public class YtChildOrders {
    private String BoxNumber;
    private int Length;
    private int Width;
    private int Height;
    private int BoxWeight;
    private List<YtChildDetails> ChildDetails;
}
