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
import org.baifei.modules.entity.Needtotracknumber111;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber111Service;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.baifei.modules.webservice.haihehui.GetTransportWayListResponse;
import org.baifei.modules.webservice.haihehui.TransportWay;
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
@RequestMapping("/modules/needtotracknumber111")
public class HAIHEHUI111Controller extends JeecgController<Needtotracknumber111, INeedtotracknumber111Service> {
   @Autowired
   private INeedtotracknumber111Service needtotracknumber111Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";
//   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber111
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber111 needtotracknumber111,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber111> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber111, req.getParameterMap());
       Page<Needtotracknumber111> page = new Page<Needtotracknumber111>(pageNo, pageSize);
       IPage<Needtotracknumber111> pageList = needtotracknumber111Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber111
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber111 needtotracknumber111) {
       needtotracknumber111Service.save(needtotracknumber111);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber111
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber111 needtotracknumber111) {
       needtotracknumber111Service.updateById(needtotracknumber111);
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
       needtotracknumber111Service.removeById(id);
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
       this.needtotracknumber111Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber111 needtotracknumber111 = needtotracknumber111Service.getById(id);
       return Result.ok(needtotracknumber111);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber111
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber111 needtotracknumber111) {
     return super.exportXls(request, needtotracknumber111, Needtotracknumber111.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber111.class);
 }
 //获取渠道
 @RequestMapping(value = "/getChannels")
 public Result<?> getChannels(HttpServletRequest request) {
     boolean flag = true;
     String token = request.getParameter("token");
     TrackResultModel resultModel = needtotracknumber111Service.getChannels(token);
     if (resultModel.getFlag() == 0) {
         try {
             GetTransportWayListResponse resultJson = JSONObject.parseObject(resultModel.getDescr(),GetTransportWayListResponse.class);


             for (int i = 0; i < resultJson.getTransportWays().length; i++) {
                 TransportWay transportWay = resultJson.getTransportWays()[i];
                 DbLogisticschannel logisticschannel = new DbLogisticschannel();
                 logisticschannel.setLogisticsId(111);
                 logisticschannel.setCode(transportWay.getCode());
                 logisticschannel.setName(transportWay.getName());
                 logisticschannel.setLogisticsName("海河汇");
                 ResulstCodeWeb<List<DbLogisticschannel>> result = restTemplateUtil.getDataListForPost(DbLogisticschannel.class, logisticschannel, "", url + "/syncDbLogisticsChannel");
                 if (result.getAck() != 0) {
                     flag = false;
                     return Result.error("渠道更新失败");
                 }
             }

         } catch (Exception e) {
             return  Result.error("调用获取渠道接口返回数据异常" + e.getMessage());
         }

     } else {
         return Result.error("获取失败" + resultModel.getDescr());
     }
     return Result.ok("获取成功");
 }
}

