package com.imooc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.BGMOperationTypeEnum;
import com.imooc.enums.VideoStatusEnum;
import com.imooc.mapper.BgmMapper;
import com.imooc.mapper.ReportMapperCustom;
import com.imooc.mapper.VideosMapper;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.BgmExample;
import com.imooc.pojo.Videos;
import com.imooc.pojo.VO.ReportVO;
import com.imooc.service.VideoService;
import com.imooc.utils.PagedResult;
import com.imooc.web.util.ZKcurator;

@Service
public class VideoServiceImpl implements VideoService{
	@Autowired
	private BgmMapper bgmMapper;
	
	@Autowired
	private ReportMapperCustom reportMapperCustom;
	
	@Autowired
	private VideosMapper videosMapper;
	
	@Autowired
	private Sid sid;
	
	@Autowired
	private ZKcurator zkcurator;

	@Override
	public PagedResult queryBgmList(Integer page, Integer pageSize) {
		
		BgmExample example=new BgmExample();
		PageHelper.startPage(page, pageSize);
		List<Bgm> list=bgmMapper.selectByExample(example);
		 
		PageInfo<Bgm> pageList=new PageInfo<>(list);
		
		PagedResult result=new PagedResult();
		result.setTotal(pageList.getPages());
		result.setRows(list);
		result.setPage(page);
		result.setRecords(pageList.getTotal());
		
		return result;
	}
	/**
	 * 添加bgm
	 */
	@Override
	public void addBgm(Bgm bgm) {
		
		String id=sid.nextShort();
		bgm.setId(id);
		bgmMapper.insert(bgm);
		Map<String,String> map=new HashMap<>();
		map.put("operType",  BGMOperationTypeEnum.ADD.type);
		map.put("path", bgm.getPath());
		zkcurator.sendBgmOperator(bgm.getId(), JSONUtils.toJSONString(map));
	}
	
	/**
	 * 删除bgm
	 */
	@Override
	public void delBgm(String bgmId) {
		Bgm bgm=bgmMapper.selectByPrimaryKey(bgmId);
		bgmMapper.deleteByPrimaryKey(bgmId);
		Map<String,String> map=new HashMap<>();
		map.put("operType",  BGMOperationTypeEnum.DELETE.type);
		map.put("path", bgm.getPath());
		zkcurator.sendBgmOperator(bgm.getId(), JSONUtils.toJSONString(map));
	}
	
	/**
	 * 查找举报列表
	 */
	@Override
	public PagedResult queryReportList(Integer page, Integer pageSize) {
	
		PageHelper.startPage(page, pageSize);
		List<ReportVO> list= reportMapperCustom.quryReports();
		 
		PageInfo<ReportVO> pageList=new PageInfo<>(list);
		
		PagedResult result=new PagedResult();
		result.setTotal(pageList.getPages());
		result.setRows(list);
		result.setPage(page);
		result.setRecords(pageList.getTotal());
		
		return result;
	}
	@Override
	public void changeStatus(String videoId) {
		
		Videos video=videosMapper.selectByPrimaryKey(videoId);
		if(video!=null){
			int oldStatus=video.getStatus();
			if(oldStatus==VideoStatusEnum.ALLOWED.type){
				video.setStatus(VideoStatusEnum.FORBIDDEN.type);
			}else if(oldStatus==VideoStatusEnum.FORBIDDEN.type){
				video.setStatus(VideoStatusEnum.ALLOWED.type);
			}
			
			videosMapper.updateByPrimaryKey(video);
		}
		
	}
	
}
