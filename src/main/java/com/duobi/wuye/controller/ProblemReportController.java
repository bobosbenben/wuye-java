package com.duobi.wuye.controller;

import com.duobi.wuye.entity.ProblemEntity;
import com.duobi.wuye.service.ProblemService;
import com.duobi.wuye.utils.CosClientUtil;
import com.duobi.wuye.utils.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/problem")
public class ProblemReportController {

    private Logger logger = LoggerFactory.getLogger(ProblemReportController.class);

    @Autowired
    private ProblemService problemService;
    @Autowired
    private CosClientUtil cosClientUtil;

    @CrossOrigin( maxAge = 3600)
    @RequestMapping("/new")
    public @ResponseBody
    Object upload(@RequestParam(value = "file",required = false) MultipartFile multipartfiles[], @RequestParam("openid") String openid, @RequestParam("problemType") String problemType, @RequestParam String problemDescription) {
        ResponseJson responseJson = new ResponseJson();

        ProblemEntity p = new ProblemEntity();
        p.setOpenid(openid);
        p.setNormalUser(problemService.getNormalUserByOpenid(openid));
        p.setProblemType(problemService.getProblemTypeByShortName(problemType));
        p.setProblemDescription(problemDescription);
        List<String> pictureUrl = new ArrayList<>();

        if (multipartfiles !=null && multipartfiles.length>0) {
            try {
                for (MultipartFile file :multipartfiles){
                    File f = File.createTempFile(UUID.randomUUID().toString().replaceAll("-", ""),"-pic");
                    file.transferTo(f);
                    String url = cosClientUtil.uploadFile(f);
                    f.deleteOnExit();
                    pictureUrl.add(url);
                }
                p.setProblemPictureUrl(pictureUrl);
                problemService.insertProblem(p);
            }catch (Exception e){
                e.printStackTrace();
                responseJson.setSuccess(false);
                responseJson.setMsg("上传失败");
                return responseJson;
            }
        }

        responseJson.setSuccess(true);
        if (problemType.equals("baoxiu")) responseJson.setMsg("报修成功");
        else responseJson.setMsg("投诉成功");

        return responseJson;
    }











}
