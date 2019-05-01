package com.imooc.mapper;

import com.imooc.pojo.Bgm;
import com.imooc.pojo.BgmExample;
import com.imooc.pojo.VO.ReportVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportMapperCustom {
    
    List<ReportVO> quryReports();

}