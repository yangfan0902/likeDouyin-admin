package com.imooc.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.mapper.BgmMapper;
import com.imooc.pojo.Bgm;
import com.imooc.service.VideoService;

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
	
}
