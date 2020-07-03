package org.baifei.modules.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.api.vo.Result;
import org.baifei.common.aspect.annotation.AutoLog;
import org.baifei.common.system.base.controller.JeecgController;
import org.baifei.common.system.query.QueryGenerator;
import org.baifei.modules.entity.Needtotracknumber104;
import org.baifei.modules.entity.request.smtol.ChannelData;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticswarehouse;
import org.baifei.modules.entity.response.fpx.FpxChannelsResponse;
import org.baifei.modules.entity.response.fpx.FpxChannelsResponseData;
import org.baifei.modules.entity.response.fpx.FpxWareHouseResponseData;
import org.baifei.modules.entity.response.fpx.FpxWareHouseResponse;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber104Service;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
* @Description: 物流交运
* @Author: jeecg-boot
* @Date:   2020-06-04
* @Version: V1.0
*/
@Slf4j
@Api(tags="物流交运")
@RestController
@RequestMapping("/modules/needtotracknumber104")
public class FPX104Controller extends JeecgController<Needtotracknumber104, INeedtotracknumber104Service> {
   @Autowired
   private INeedtotracknumber104Service needtotracknumber104Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";

 //   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber104
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber104 needtotracknumber104,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber104> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber104, req.getParameterMap());
       Page<Needtotracknumber104> page = new Page<Needtotracknumber104>(pageNo, pageSize);
       IPage<Needtotracknumber104> pageList = needtotracknumber104Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber104
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber104 needtotracknumber104) {
       needtotracknumber104Service.save(needtotracknumber104);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber104
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber104 needtotracknumber104) {
       needtotracknumber104Service.updateById(needtotracknumber104);
       return Result.ok("编辑成功!");
   }
   
   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "物流交运-通过id删除")
   @ApiOperation(value="物流交运-通过id删除", notes="物流交运-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       needtotracknumber104Service.removeById(id);
       return Result.ok("删除成功!");
   }
   
   /**
    * 批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "物流交运-批量删除")
   @ApiOperation(value="物流交运-批量删除", notes="物流交运-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.needtotracknumber104Service.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
   }
   
   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "物流交运-通过id查询")
   @ApiOperation(value="物流交运-通过id查询", notes="物流交运-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       Needtotracknumber104 needtotracknumber104 = needtotracknumber104Service.getById(id);
       return Result.ok(needtotracknumber104);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber104
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber104 needtotracknumber104) {
     return super.exportXls(request, needtotracknumber104, Needtotracknumber104.class, "物流交运");
 }

 /**
  * 通过excel导入数据
  *
  * @param request
  * @param response
  * @return
  */
 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
     return super.importExcel(request, response, Needtotracknumber104.class);
 }

 //获取仓库
 @RequestMapping(value = "/getWarehouse")
 public Result<?> getWarehouse(HttpServletRequest request){
     String appkey = request.getParameter("appkey");
     String appSecret = request.getParameter("appSecret");
     String companyId = request.getParameter("companyId");
     String logisticsId = request.getParameter("logisticsId");
     String msg="";
     TrackResultModel resultModel =needtotracknumber104Service.getWarehouse(appkey,appSecret);
     if(resultModel.getFlag()==0){
         FpxWareHouseResponse fpxWareHouseResponse = JSONArray.parseObject(resultModel.getDescr(), FpxWareHouseResponse.class);
         if("1".equals(fpxWareHouseResponse.getResult())){
             List<FpxWareHouseResponseData> datalist = fpxWareHouseResponse.getData();
             if(datalist!=null && datalist.size()>0){
                 for(FpxWareHouseResponseData fpxWareHouseResponseData : datalist){
                     DbMylogisticswarehouse mylogisticswarehouse = new DbMylogisticswarehouse();
                     mylogisticswarehouse.setCompanyId(Integer.parseInt(companyId));
                     mylogisticswarehouse.setLogisticsId(Integer.parseInt(logisticsId));
                     mylogisticswarehouse.setMyLogisticsId(Integer.parseInt(logisticsId));
                     mylogisticswarehouse.setCountryCode(fpxWareHouseResponseData.getCountry());
                     mylogisticswarehouse.setCode(fpxWareHouseResponseData.getWarehouse_code());
                     mylogisticswarehouse.setName(fpxWareHouseResponseData.getWarehouse_name_cn());
                     ResulstCodeWeb<List<DbMylogisticswarehouse>> result = restTemplateUtil.getDataListForPost(DbMylogisticswarehouse.class,mylogisticswarehouse,"",url+"/syncDbMyLogisticsWarehouse");
                     if(result.getAck()!=0){
                         msg+="同步仓库失败"+result.getMsg();
                     }
                     TrackResultModel resultModelChannels =needtotracknumber104Service.getChannels(appkey,appSecret,fpxWareHouseResponseData.getWarehouse_code());
                     if(resultModelChannels.getFlag()==0){
                         FpxChannelsResponse channelsResponse = JSONArray.parseObject(resultModelChannels.getDescr(), FpxChannelsResponse.class);
                         if("1".equals(channelsResponse.getResult())){
                             List<FpxChannelsResponseData> channelsResponseDataList = channelsResponse.getData();
                             for(FpxChannelsResponseData fpxChannelsResponseData : channelsResponseDataList){
                                 DbLogisticschannel logisticschannel = new DbLogisticschannel();
                                 logisticschannel.setLogisticsId(104);
                                 logisticschannel.setCode(fpxChannelsResponseData.getLogistics_product_code());
                                 logisticschannel.setName(fpxChannelsResponseData.getLogistics_product_name_cn());
                                 logisticschannel.setWarehouseCode(fpxWareHouseResponseData.getWarehouse_code());
                                 logisticschannel.setLogisticsName("递四方");
                                 ResulstCodeWeb<List<DbLogisticschannel>> resultChannels = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,logisticschannel,"",url+"/syncDbLogisticsChannel");
                                 if(resultChannels.getAck()!=0){
                                     msg+="仓库"+fpxWareHouseResponseData.getWarehouse_name_cn()+"同步渠道失败"+result.getMsg()+"/r/n";
                                 }
                             }
                             System.out.println("仓库"+fpxWareHouseResponseData.getWarehouse_name_cn()+"同步渠道完成"+channelsResponseDataList.size());
                         }else{
                                msg+="仓库"+fpxWareHouseResponseData.getWarehouse_name_cn()+"获取渠道失败"+"/r/n";;
                         }
                     }

                 }
                 if(!"".equals(msg)){
                     return Result.error(msg);
                 }else{
                     return Result.ok("同步仓库完成"+datalist.size());
                 }

             }else{
                 return Result.error("未获取到仓库信息");
             }
         }else{
             return Result.error("获取仓库失败");
         }
     }else{
         return Result.error("获取失败接口异常");
     }


 }
}
