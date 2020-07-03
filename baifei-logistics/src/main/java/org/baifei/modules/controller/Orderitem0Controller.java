package org.baifei.modules.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.baifei.common.api.vo.Result;
import org.baifei.common.system.query.QueryGenerator;
import org.baifei.common.aspect.annotation.AutoLog;
import org.baifei.common.util.oConvertUtils;
import org.baifei.modules.entity.Orderitem0;
import org.baifei.modules.service.IOrderitem0Service;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 订单详情
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="订单详情")
@RestController
@RequestMapping("/modules/orderitem0")
public class Orderitem0Controller extends JeecgController<Orderitem0, IOrderitem0Service> {
	@Autowired
	private IOrderitem0Service orderitem0Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param orderitem0
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单详情-分页列表查询")
	@ApiOperation(value="订单详情-分页列表查询", notes="订单详情-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Orderitem0 orderitem0,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Orderitem0> queryWrapper = QueryGenerator.initQueryWrapper(orderitem0, req.getParameterMap());
		Page<Orderitem0> page = new Page<Orderitem0>(pageNo, pageSize);
		IPage<Orderitem0> pageList = orderitem0Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param orderitem0
	 * @return
	 */
	@AutoLog(value = "订单详情-添加")
	@ApiOperation(value="订单详情-添加", notes="订单详情-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Orderitem0 orderitem0) {
		orderitem0Service.save(orderitem0);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param orderitem0
	 * @return
	 */
	@AutoLog(value = "订单详情-编辑")
	@ApiOperation(value="订单详情-编辑", notes="订单详情-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Orderitem0 orderitem0) {
		orderitem0Service.updateById(orderitem0);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单详情-通过id删除")
	@ApiOperation(value="订单详情-通过id删除", notes="订单详情-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		orderitem0Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单详情-批量删除")
	@ApiOperation(value="订单详情-批量删除", notes="订单详情-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.orderitem0Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单详情-通过id查询")
	@ApiOperation(value="订单详情-通过id查询", notes="订单详情-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Orderitem0 orderitem0 = orderitem0Service.getById(id);
		return Result.ok(orderitem0);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param orderitem0
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Orderitem0 orderitem0) {
      return super.exportXls(request, orderitem0, Orderitem0.class, "订单详情");
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
      return super.importExcel(request, response, Orderitem0.class);
  }

}
