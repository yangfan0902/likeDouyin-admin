package com.imooc.service.impl;

import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.BgmMapper;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.BgmExample;
import com.imooc.service.VideoService;
import com.imooc.utils.PagedResult;

@Service
public class VideoServiceImpl implements VideoService{
	@Autowired
	private BgmMapper bgmMapper;
	
	@Autowired
	private Sid sid;
	
	@Override
	public void addBgm(Bgm bgm) {
		
		String id=sid.nextShort();
		bgm.setId(id);
		bgmMapper.insert(bgm);
		
	}

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

	@Override
	public void delBgm(String bgmId) {
		bgmMapper.deleteByPrimaryKey(bgmId);
	}
	
}
