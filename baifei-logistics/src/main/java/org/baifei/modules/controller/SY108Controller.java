package org.baifei.modules.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunyou.api.model.v1_0_0.common.APIRequest;
import com.sunyou.api.model.v1_0_0.common.APIResponse;
import com.sunyou.api.model.v1_0_0.common.FindShippingMethodsResult;
import com.sunyou.api.model.v1_0_0.out.FindShippingMethodsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.api.vo.Result;
import org.baifei.common.aspect.annotation.AutoLog;
import org.baifei.common.system.base.controller.JeecgController;
import org.baifei.common.system.query.QueryGenerator;
import org.baifei.modules.entity.Needtotracknumber108;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.wishu.WishuChannels;
import org.baifei.modules.entity.response.wishu.WishuChannelsResponse;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber108Service;
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
@RequestMapping("/modules/needtotracknumber108")
public class SY108Controller extends JeecgController<Needtotracknumber108, INeedtotracknumber108Service> {
   @Autowired
   private INeedtotracknumber108Service needtotracknumber108Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";
//   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber108
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber108 needtotracknumber108,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber108> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber108, req.getParameterMap());
       Page<Needtotracknumber108> page = new Page<Needtotracknumber108>(pageNo, pageSize);
       IPage<Needtotracknumber108> pageList = needtotracknumber108Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber108
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber108 needtotracknumber108) {
       needtotracknumber108Service.save(needtotracknumber108);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber108
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber108 needtotracknumber108) {
       needtotracknumber108Service.updateById(needtotracknumber108);
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
       needtotracknumber108Service.removeById(id);
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
       this.needtotracknumber108Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber108 needtotracknumber108 = needtotracknumber108Service.getById(id);
       return Result.ok(needtotracknumber108);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber108
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber108 needtotracknumber108) {
     return super.exportXls(request, needtotracknumber108, Needtotracknumber108.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber108.class);
 }
 //获取渠道
 @RequestMapping(value = "/getChannels")
 public Result<?> getChannels(HttpServletRequest request){
    boolean flag = true;
    String token = request.getParameter("token");
    TrackResultModel resultModel =needtotracknumber108Service.getChannels(token);
    if(resultModel.getFlag()==0){
        APIResponse<FindShippingMethodsResponse> resultAPIResponse = JSONObject.parseObject(resultModel.getDescr(),new TypeReference<APIResponse<FindShippingMethodsResponse>>() {});
        if("success".equals(resultAPIResponse.getAck())){
            for(FindShippingMethodsResult shippingMethodsResult:resultAPIResponse.getData().getResultList()){
                DbLogisticschannel logisticschannel = new DbLogisticschannel();
                logisticschannel.setLogisticsId(108);
                logisticschannel.setCode(shippingMethodsResult.getShippingMethodCode());
                logisticschannel.setName(shippingMethodsResult.getShippingMethodCnName());
                logisticschannel.setLogisticsName("顺友");
                ResulstCodeWeb<List<DbLogisticschannel>> result = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,logisticschannel,"",url+"/syncDbLogisticsChannel");
                if(result.getAck()!=0){
                    flag = false;
                    return Result.error("更新失败");
                }
            }
        }else{
            return Result.error("获取失败"+resultAPIResponse.getErrorMsg());
        }
    }else{
        return Result.error("获取失败"+resultModel.getDescr());
    }
    return Result.ok("获取完成");
 }
}
