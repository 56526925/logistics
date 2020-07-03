/**
 * Copyright 2020 bejson.com
 */
package org.baifei.modules.entity.request.qufahuo;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-06-20 9:49:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class QufahuoRequestStep1 {

    private String bNo;
    private String charged;
    private List<QufahuoDeclareInfos> declareInfos;
    private int insuranceValue;
    private int insureValue;
    private int itemType;
    private String logisticsId;
    private String material;
    private String note;
    private String orderNo;
    private String passportNumber;
    private int pracelType;
    private QufahuoRecipient recipient;
    private QufahuoSender sender;
    private String source;
    private String taxId;
    private String trackNo;
    private String transportNo;
    private double weight;

}