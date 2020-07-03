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
import org.baifei.common.util.Util;
import org.baifei.modules.entity.Needtotracknumber119;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber119Service;
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
@RequestMapping("/modules/needtotracknumber119")
public class JOOM119Controller extends JeecgController<Needtotracknumber119, INeedtotracknumber119Service> {
   @Autowired
   private INeedtotracknumber119Service needtotracknumber119Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";
//   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber119
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber119 needtotracknumber119,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber119> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber119, req.getParameterMap());
       Page<Needtotracknumber119> page = new Page<Needtotracknumber119>(pageNo, pageSize);
       IPage<Needtotracknumber119> pageList = needtotracknumber119Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber119
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber119 needtotracknumber119) {
       needtotracknumber119Service.save(needtotracknumber119);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber119
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber119 needtotracknumber119) {
       needtotracknumber119Service.updateById(needtotracknumber119);
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
       needtotracknumber119Service.removeById(id);
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
       this.needtotracknumber119Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber119 needtotracknumber119 = needtotracknumber119Service.getById(id);
       return Result.ok(needtotracknumber119);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber119
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber119 needtotracknumber119) {
     return super.exportXls(request, needtotracknumber119, Needtotracknumber119.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber119.class);
 }
 //获取渠道
 @RequestMapping(value = "/getChannels")
 public Result<?> getChannels(HttpServletRequest request) {
     boolean flag = true;
     String token = request.getParameter("token");
     TrackResultModel resultModel = needtotracknumber119Service.getChannels(token);
     if (resultModel.getFlag() == 0) {
         try {
             JSONObject resultJson =  JSONObject.parseObject(resultModel.getDescr());
             if("0".equals(resultJson.getString("code"))){
                 JSONArray channels = resultJson.getJSONArray("data");
                 for (int i = 0; i < channels.size(); i++) {
                     JSONObject channel = channels.getJSONObject(i);
                     DbLogisticschannel logisticschannel = new DbLogisticschannel();
                     logisticschannel.setLogisticsId(119);
                     logisticschannel.setCode(channel.getString("name"));
                     if(!Util.isNull(channel.getString("nameCn"))){
                         logisticschannel.setName(channel.getString("nameCn"));
                     }else{
                         logisticschannel.setName(channel.getString("nameLc"));
                     }
                     logisticschannel.setLogisticsName("Joom");
                     ResulstCodeWeb<List<DbLogisticschannel>> result = restTemplateUtil.getDataListForPost(DbLogisticschannel.class, logisticschannel, "", url + "/syncDbLogisticsChannel");
                     if (result.getAck() != 0) {
                         flag = false;
                         return Result.error("渠道更新失败");
                     }
                 }
             }else{
                 return Result.error("调用渠道接口失败"+resultJson.getString("msg"));
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

