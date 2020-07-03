package org.baifei.modules.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.baifei.modules.entity.Needtotracknumber109;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber109Service;
import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
@RequestMapping("/modules/needtotracknumber109")
public class YANWEN109Controller extends JeecgController<Needtotracknumber109, INeedtotracknumber109Service> {
   @Autowired
   private INeedtotracknumber109Service needtotracknumber109Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";
//   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber109
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber109 needtotracknumber109,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber109> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber109, req.getParameterMap());
       Page<Needtotracknumber109> page = new Page<Needtotracknumber109>(pageNo, pageSize);
       IPage<Needtotracknumber109> pageList = needtotracknumber109Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber109
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber109 needtotracknumber109) {
       needtotracknumber109Service.save(needtotracknumber109);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber109
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber109 needtotracknumber109) {
       needtotracknumber109Service.updateById(needtotracknumber109);
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
       needtotracknumber109Service.removeById(id);
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
       this.needtotracknumber109Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber109 needtotracknumber109 = needtotracknumber109Service.getById(id);
       return Result.ok(needtotracknumber109);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber109
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber109 needtotracknumber109) {
     return super.exportXls(request, needtotracknumber109, Needtotracknumber109.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber109.class);
 }
 //获取渠道
 @RequestMapping(value = "/getChannels")
 public void getChannels(HttpServletRequest request) {
    boolean flag = true;
    String apiToken  = request.getParameter("apiToken");
    String userId = request.getParameter("userId");
    TrackResultModel resultModel =needtotracknumber109Service.getChannels(userId,apiToken);
    if(resultModel.getFlag()==0){
        try {
            Document document = DocumentHelper.parseText(resultModel.getDescr());
            Element root = document.getRootElement();//获取根节点  root
            List<Element> list = root.element("ChannelCollection").elements("ChannelType");
            if("true".equals(root.element("CallSuccess").getText())){
                for(Element channel:list){
                    DbLogisticschannel logisticschannel = new DbLogisticschannel();
                    logisticschannel.setLogisticsId(109);
                    logisticschannel.setCode(channel.element("Id").getText());
                    logisticschannel.setName(channel.element("Name").getText());
                    logisticschannel.setLogisticsName("燕文");
                    ResulstCodeWeb<List<DbLogisticschannel>> result = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,logisticschannel,"",url+"/syncDbLogisticsChannel");
                    if(result.getAck()!=0){
                        flag = false;
                        log.info("燕文渠道更新失败");
                    }
                }
            }else{
                log.info("调用获取渠道接口失败");
            }
        }catch (Exception e){
            log.info("调用获取渠道接口返回数据异常"+e.getMessage());
        }

    }else{
        log.info("获取失败"+resultModel.getDescr());
    }
     log.info("获取成功");
 }
}
