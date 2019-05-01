package com.imooc.service;

import com.imooc.pojo.Bgm;
import com.imooc.utils.PagedResult;

public interface VideoService {
	public void addBgm(Bgm bgm);
	
	public PagedResult queryBgmList(Integer page,Integer pageSize);

	public void delBgm(String bgmId);

	public PagedResult queryReportList(Integer page, Integer pageSize);

	public void changeStatus(String videoId);
}
