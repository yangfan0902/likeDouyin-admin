package com.imooc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.bean.AdminUser;

import com.imooc.pojo.Bgm;
import com.imooc.pojo.Users;
import com.imooc.pojo.Videos;
import com.imooc.service.UsersService;
import com.imooc.service.VideoService;
import com.imooc.utils.IMoocJSONResult;

import com.imooc.utils.PagedResult;



@Controller
@RequestMapping("video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@GetMapping("/showAddBgm")
	public String showAddBgm(){
		return "video/addBgm";
	}
	
	@PostMapping("/queryBgmList")
	@ResponseBody
	public PagedResult showBgmList(Integer page){
		return videoService.queryBgmList(page, 10);
	}
	
	@GetMapping("/showBgmList")
	public String showBgmList(){
		return "video/bgmList";
	}
	
	@PostMapping("/bgmUpload")
	@ResponseBody
	public IMoocJSONResult upload(MultipartFile file) throws Exception {
		
		String fileSpace="D:"+File.separator+"imooc_videos_dev";
		
		// 保存到数据库中的相对路径
		String uploadPathDB = File.separator +"bgm";
		
		String finalVedioPath = "";
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;

		try {
			if (file != null) {
				String fileName = file.getOriginalFilename();
				if (StringUtils.isNoneBlank(fileName)) {
					finalVedioPath = fileSpace + uploadPathDB + File.separator + fileName;
					uploadPathDB += (File.separator + fileName);
					File outFile = new File(finalVedioPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						outFile.getParentFile().mkdirs();
					}
					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}
			} else {
				return IMoocJSONResult.errorMsg("上传出错");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return IMoocJSONResult.errorMsg("上传出错");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}
		return IMoocJSONResult.ok(uploadPathDB);
	}
	
	@ResponseBody
	@PostMapping("/addBgm")
	public IMoocJSONResult addBgm(Bgm bgm){
		videoService.addBgm(bgm);
		return IMoocJSONResult.ok();
	}
	
	@ResponseBody
	@PostMapping("/delBgm")
	public IMoocJSONResult delBgm(String bgmId){
		videoService.delBgm(bgmId);
		return IMoocJSONResult.ok("OK");
	}
	
}
