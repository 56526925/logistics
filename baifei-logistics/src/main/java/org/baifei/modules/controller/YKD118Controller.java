package org.baifei.modules.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.baifei.modules.entity.Needtotracknumber118;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.common.DbMylogisticswarehouse;
import org.baifei.modules.entity.response.fpx.FpxChannelsResponse;
import org.baifei.modules.entity.response.fpx.FpxChannelsResponseData;
import org.baifei.modules.entity.response.fpx.FpxWareHouseResponse;
import org.baifei.modules.entity.response.fpx.FpxWareHouseResponseData;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber118Service;
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
@RequestMapping("/modules/needtotracknumber118")
public class YKD118Controller extends JeecgController<Needtotracknumber118, INeedtotracknumber118Service> {
   @Autowired
   private INeedtotracknumber118Service needtotracknumber118Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";

 //   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber118
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber118 needtotracknumber118,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber118> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber118, req.getParameterMap());
       Page<Needtotracknumber118> page = new Page<Needtotracknumber118>(pageNo, pageSize);
       IPage<Needtotracknumber118> pageList = needtotracknumber118Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber118
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber118 needtotracknumber118) {
       needtotracknumber118Service.save(needtotracknumber118);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber118
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber118 needtotracknumber118) {
       needtotracknumber118Service.updateById(needtotracknumber118);
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
       needtotracknumber118Service.removeById(id);
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
       this.needtotracknumber118Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber118 needtotracknumber118 = needtotracknumber118Service.getById(id);
       return Result.ok(needtotracknumber118);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber118
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber118 needtotracknumber118) {
     return super.exportXls(request, needtotracknumber118, Needtotracknumber118.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber118.class);
 }

 //获取仓库
 @RequestMapping(value = "/getWarehouse")
 public Result<?> getWarehouse(HttpServletRequest request){
     String appToken = request.getParameter("appToken");
     String appKey = request.getParameter("appKey");
     String msg="";
     TrackResultModel resultModel =needtotracknumber118Service.getWarehouse(appToken,appKey);
     if(resultModel.getFlag()==0){
         JSONObject house = JSONObject.parseObject(resultModel.getDescr());
         if("Success".equals(house.getString("ask"))){
            JSONArray houselist = house.getJSONArray("data");
             if(houselist!=null && houselist.size()>0){
                 for(int i=0;i<houselist.size();i++){
                     JSONObject warehouse = houselist.getJSONObject(i);
                     DbMylogisticswarehouse mylogisticswarehouse = new DbMylogisticswarehouse();
                     mylogisticswarehouse.setCompanyId(101300);
                     mylogisticswarehouse.setLogisticsId(118);
                     mylogisticswarehouse.setMyLogisticsId(118);
                     mylogisticswarehouse.setCountryCode(warehouse.getString("country_code"));
                     mylogisticswarehouse.setCode(warehouse.getString("warehouse_code"));
                     mylogisticswarehouse.setName(warehouse.getString("warehouse_name"));
                     ResulstCodeWeb<List<DbMylogisticswarehouse>> result = restTemplateUtil.getDataListForPost(DbMylogisticswarehouse.class,mylogisticswarehouse,"",url+"/syncDbMyLogisticsWarehouse");
                     if(result.getAck()!=0){
                         msg+="同步仓库失败"+result.getMsg();
                     }
                     TrackResultModel resultModelChannels =needtotracknumber118Service.getChannels(appToken,appKey,warehouse.getString("warehouse_code"));
                     if(resultModelChannels.getFlag()==0){
                         JSONObject channels = JSONObject.parseObject(resultModelChannels.getDescr());
                         if("Success".equals(channels.getString("ask"))){
                             JSONArray channellist = channels.getJSONArray("data");
                             for(int c=0;c<channellist.size();c++){
                                 JSONObject channel = channellist.getJSONObject(c);
                                 DbLogisticschannel logisticschannel = new DbLogisticschannel();
                                 logisticschannel.setLogisticsId(118);
                                 logisticschannel.setCode(channel.getString("code"));
                                 logisticschannel.setName(channel.getString("name"));
                                 logisticschannel.setWarehouseCode(channel.getString("warehouse_code"));
                                 logisticschannel.setLogisticsName("易可达");
                                 ResulstCodeWeb<List<DbLogisticschannel>> resultChannels = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,logisticschannel,"",url+"/syncDbLogisticsChannel");
                                 if(resultChannels.getAck()!=0){
                                     msg+="仓库"+channel.getString("name")+"同步渠道失败"+result.getMsg()+"/r/n";
                                 }
                             }
                             System.out.println("仓库"+warehouse.getString("warehouse_name")+"同步渠道完成"+channellist.size());
                         }else{
                                msg+="仓库"+warehouse.getString("warehouse_name")+"获取渠道失败"+"/r/n";
                         }
                     }

                 }
                 if(!"".equals(msg)){
                     return Result.error(msg);
                 }else{
                     return Result.ok("同步仓库完成"+houselist.size());
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
