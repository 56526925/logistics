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
import org.baifei.modules.entity.Ordercurrency0;
import org.baifei.modules.service.IOrdercurrency0Service;
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
 * @Description: 订单金额汇总信息
 * @Author: jeecg-boot
 * @Date:   2020-06-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="订单金额汇总信息")
@RestController
@RequestMapping("/modules/ordercurrency0")
public class Ordercurrency0Controller extends JeecgController<Ordercurrency0, IOrdercurrency0Service> {
	@Autowired
	private IOrdercurrency0Service ordercurrency0Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param ordercurrency0
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单金额汇总信息-分页列表查询")
	@ApiOperation(value="订单金额汇总信息-分页列表查询", notes="订单金额汇总信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ordercurrency0 ordercurrency0,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ordercurrency0> queryWrapper = QueryGenerator.initQueryWrapper(ordercurrency0, req.getParameterMap());
		Page<Ordercurrency0> page = new Page<Ordercurrency0>(pageNo, pageSize);
		IPage<Ordercurrency0> pageList = ordercurrency0Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ordercurrency0
	 * @return
	 */
	@AutoLog(value = "订单金额汇总信息-添加")
	@ApiOperation(value="订单金额汇总信息-添加", notes="订单金额汇总信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ordercurrency0 ordercurrency0) {
		ordercurrency0Service.save(ordercurrency0);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ordercurrency0
	 * @return
	 */
	@AutoLog(value = "订单金额汇总信息-编辑")
	@ApiOperation(value="订单金额汇总信息-编辑", notes="订单金额汇总信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ordercurrency0 ordercurrency0) {
		ordercurrency0Service.updateById(ordercurrency0);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单金额汇总信息-通过id删除")
	@ApiOperation(value="订单金额汇总信息-通过id删除", notes="订单金额汇总信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ordercurrency0Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单金额汇总信息-批量删除")
	@ApiOperation(value="订单金额汇总信息-批量删除", notes="订单金额汇总信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ordercurrency0Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单金额汇总信息-通过id查询")
	@ApiOperation(value="订单金额汇总信息-通过id查询", notes="订单金额汇总信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ordercurrency0 ordercurrency0 = ordercurrency0Service.getById(id);
		return Result.ok(ordercurrency0);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ordercurrency0
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ordercurrency0 ordercurrency0) {
      return super.exportXls(request, ordercurrency0, Ordercurrency0.class, "订单金额汇总信息");
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
      return super.importExcel(request, response, Ordercurrency0.class);
  }

}
