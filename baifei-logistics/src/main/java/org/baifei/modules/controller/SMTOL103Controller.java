package org.baifei.modules.controller;

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
import org.baifei.modules.entity.Needtotracknumber103;
import org.baifei.modules.entity.request.smtol.ChannelData;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber103Service;
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
@RequestMapping("/modules/needtotracknumber103")
public class SMTOL103Controller extends JeecgController<Needtotracknumber103, INeedtotracknumber103Service> {
   @Autowired
   private INeedtotracknumber103Service needtotracknumber103Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber103
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber103 needtotracknumber103,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber103> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber103, req.getParameterMap());
       Page<Needtotracknumber103> page = new Page<Needtotracknumber103>(pageNo, pageSize);
       IPage<Needtotracknumber103> pageList = needtotracknumber103Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber103
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber103 needtotracknumber103) {
       needtotracknumber103Service.save(needtotracknumber103);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber103
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber103 needtotracknumber103) {
       needtotracknumber103Service.updateById(needtotracknumber103);
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
       needtotracknumber103Service.removeById(id);
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
       this.needtotracknumber103Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber103 needtotracknumber103 = needtotracknumber103Service.getById(id);
       return Result.ok(needtotracknumber103);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber103
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber103 needtotracknumber103) {
     return super.exportXls(request, needtotracknumber103, Needtotracknumber103.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber103.class);
 }

 //获取渠道
 @RequestMapping(value = "/getChannels")
 public Result<?> getChannels(HttpServletRequest request){
     String orderId = request.getParameter("orderId");
     String costomId = request.getParameter("costomId");
     TrackResultModel resultModel =needtotracknumber103Service.getChannels(orderId,costomId);
     if(resultModel.getFlag()==0){
          List<ChannelData> datalist= JSONArray.parseArray(resultModel.getDescr(),ChannelData.class);
          if(datalist!=null && datalist.size()>0){
               for(ChannelData channelData:datalist){
                   DbLogisticschannel logisticschannel = new DbLogisticschannel();
                   logisticschannel.setLogisticsId(103);
                   logisticschannel.setLogisticsName("速卖通");
                   logisticschannel.setCode(channelData.getLogistics_service_id());
                   logisticschannel.setName(channelData.getLogistics_service_name());
                   logisticschannel.setDescr(channelData.getDelivery_address());
                   ResulstCodeWeb<List<DbLogisticschannel>> result = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,logisticschannel,"",url+"/syncDbLogisticsChannel");
                   if(result.getAck()!=0){
                       return Result.error("同步失败"+result.getMsg());
                   }
               }
          }
         return Result.ok("获取完成"+datalist.size());
     }else{
         return Result.error("获取失败接口异常");
     }

 }
}
