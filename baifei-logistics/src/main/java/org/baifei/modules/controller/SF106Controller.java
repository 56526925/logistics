package org.baifei.modules.controller;

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
import org.baifei.modules.entity.Needtotracknumber106;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.entity.response.wishu.WishuChannels;
import org.baifei.modules.entity.response.wishu.WishuChannelsResponse;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber106Service;
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
@RequestMapping("/modules/needtotracknumber106")
public class SF106Controller extends JeecgController<Needtotracknumber106, INeedtotracknumber106Service> {
   @Autowired
   private INeedtotracknumber106Service needtotracknumber106Service;

   @Autowired
   private RestTemplateUtil restTemplateUtil;
   private String url="http://122.51.68.40:8008";
//   private String url="http://192.168.1.8:8008";
   
   /**
    * 分页列表查询
    *
    * @param needtotracknumber106
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "物流交运-分页列表查询")
   @ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Needtotracknumber106 needtotracknumber106,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Needtotracknumber106> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber106, req.getParameterMap());
       Page<Needtotracknumber106> page = new Page<Needtotracknumber106>(pageNo, pageSize);
       IPage<Needtotracknumber106> pageList = needtotracknumber106Service.page(page, queryWrapper);
       return Result.ok(pageList);
   }
   
   /**
    * 添加
    *
    * @param needtotracknumber106
    * @return
    */
   @AutoLog(value = "物流交运-添加")
   @ApiOperation(value="物流交运-添加", notes="物流交运-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Needtotracknumber106 needtotracknumber106) {
       needtotracknumber106Service.save(needtotracknumber106);
       return Result.ok("添加成功！");
   }
   
   /**
    * 编辑
    *
    * @param needtotracknumber106
    * @return
    */
   @AutoLog(value = "物流交运-编辑")
   @ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Needtotracknumber106 needtotracknumber106) {
       needtotracknumber106Service.updateById(needtotracknumber106);
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
       needtotracknumber106Service.removeById(id);
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
       this.needtotracknumber106Service.removeByIds(Arrays.asList(ids.split(",")));
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
       Needtotracknumber106 needtotracknumber106 = needtotracknumber106Service.getById(id);
       return Result.ok(needtotracknumber106);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param needtotracknumber106
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber106 needtotracknumber106) {
     return super.exportXls(request, needtotracknumber106, Needtotracknumber106.class, "物流交运");
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
     return super.importExcel(request, response, Needtotracknumber106.class);
 }
}
