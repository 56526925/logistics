package org.baifei.modules.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.baifei.common.api.vo.Result;
import org.baifei.common.system.query.QueryGenerator;
import org.baifei.common.aspect.annotation.AutoLog;
import org.baifei.modules.entity.Needtotracknumber101;
import org.baifei.modules.entity.response.common.DbLogisticschannel;
import org.baifei.modules.model.TrackResultModel;
import org.baifei.modules.service.INeedtotracknumber101Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.baifei.common.system.base.controller.JeecgController;

import org.baifei.modules.util.RestTemplateUtil;
import org.baifei.modules.util.ResulstCodeWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 物流交运
 * @Author: jeecg-boot
 * @Date:   2020-06-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="物流交运")
@RestController
@RequestMapping("/modules/needtotracknumber101")
public class POST101Controller extends JeecgController<Needtotracknumber101, INeedtotracknumber101Service> {
	@Autowired
	private INeedtotracknumber101Service needtotracknumber101Service;

	@Autowired
	private RestTemplateUtil restTemplateUtil;
	private String url="http://122.51.68.40:8008";
	
	/**
	 * 分页列表查询
	 *
	 * @param needtotracknumber101
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "物流交运-分页列表查询")
	@ApiOperation(value="物流交运-分页列表查询", notes="物流交运-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Needtotracknumber101 needtotracknumber101,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<Needtotracknumber101> queryWrapper = QueryGenerator.initQueryWrapper(needtotracknumber101, req.getParameterMap());
		Page<Needtotracknumber101> page = new Page<Needtotracknumber101>(pageNo, pageSize);
		IPage<Needtotracknumber101> pageList = needtotracknumber101Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param needtotracknumber101
	 * @return
	 */
	@AutoLog(value = "物流交运-添加")
	@ApiOperation(value="物流交运-添加", notes="物流交运-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Needtotracknumber101 needtotracknumber101) {
		needtotracknumber101Service.save(needtotracknumber101);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param needtotracknumber101
	 * @return
	 */
	@AutoLog(value = "物流交运-编辑")
	@ApiOperation(value="物流交运-编辑", notes="物流交运-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Needtotracknumber101 needtotracknumber101) {
		needtotracknumber101Service.updateById(needtotracknumber101);
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
		needtotracknumber101Service.removeById(id);
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
		this.needtotracknumber101Service.removeByIds(Arrays.asList(ids.split(",")));
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
		Needtotracknumber101 needtotracknumber101 = needtotracknumber101Service.getById(id);
		return Result.ok(needtotracknumber101);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param needtotracknumber101
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Needtotracknumber101 needtotracknumber101) {
      return super.exportXls(request, needtotracknumber101, Needtotracknumber101.class, "物流交运");
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
      return super.importExcel(request, response, Needtotracknumber101.class);
  }

  //获取渠道
  @RequestMapping(value = "/getChannels")
  public Result<?> getChannels(){
  	   boolean flag = true;
	   TrackResultModel resultModel =needtotracknumber101Service.getChannels();
	   if(resultModel.getFlag()==0){
		   JSONObject jsonObject = JSONObject.parseObject(resultModel.getDescr());
		   JSONArray jsonArray = jsonObject.getJSONArray("data");
		   if(jsonArray!=null && jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					DbLogisticschannel logisticschannel = new DbLogisticschannel();
					JSONObject object =jsonArray.getJSONObject(i);
					logisticschannel.setLogisticsId(101);
					logisticschannel.setLogisticsName("中邮");
					logisticschannel.setCode(object.getString("businessCode"));
					logisticschannel.setName(object.getString("businessName"));
					ResulstCodeWeb<List<DbLogisticschannel>> result = restTemplateUtil.getDataListForPost(DbLogisticschannel.class,logisticschannel,"",url+"/syncDbLogisticsChannel");
					if(result.getAck()!=0){
						flag = false;
						return Result.error("更新失败");
					}

				}
		   }else{
			   return Result.error("获取失败");
		   }
	   }else{
		   return Result.error("获取失败"+resultModel.getDescr());
	   }
	  return Result.ok("获取完成");
  }
}
