package com.duobi.wuye.controller;

import com.alibaba.fastjson.JSONObject;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.service.NormalUserService;
import com.duobi.wuye.utils.CosClientUtil;
import com.duobi.wuye.utils.ResponseJson;
import com.duobi.wuye.utils.weixinutils.DataExchangeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/wuye")
public class WuYeController {

    private static Logger logger = LoggerFactory.getLogger(WuYeController.class);

    @Autowired
    private NormalUserService normalUserService;

    @CrossOrigin( maxAge = 3600)
    @RequestMapping("/upload")
    public @ResponseBody
    Object upload(HttpServletRequest request, @RequestParam(value="file") MultipartFile file) {
        ResponseJson responseJson = new ResponseJson();

        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                File f = null;
                f = File.createTempFile(file.getOriginalFilename(),"-temp");
                System.out.print("名称是："+f.getName());
                file.transferTo(f);
                CosClientUtil.uploadFile(f);
                f.deleteOnExit();
//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                return "上传失败," + e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "上传失败," + e.getMessage();
//            }
            }catch (Exception e){
                e.printStackTrace();
                responseJson.setSuccess(false);
                responseJson.setMsg("上传失败");
                return responseJson;
            }

            responseJson.setSuccess(true);
            responseJson.setMsg("上传成功");

        } else {
            responseJson.setSuccess(false);
            responseJson.setMsg("上传失败，因为文件是空的");
        }

        return responseJson;
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping("/getdefaultaddress")
    public @ResponseBody
    Object getNormalUsersDefaultAddress(@RequestBody JSONObject jsonObject){
        ResponseJson responseJson = new ResponseJson();

        String openid = jsonObject.getString("openid");

        NormalUserEntity normalUserEntity = new NormalUserEntity();
        normalUserEntity.setOpenid(openid);
        NormalUserAddressDTO n = normalUserService.getUserDefaultAddressByOpenid(normalUserEntity);

        responseJson.setSuccess(true);
        if (n != null) responseJson.setTotal(1);
        else responseJson.setTotal(0);
        responseJson.setData(n);

        return responseJson;
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/faultreport")
    public String faultReport(HttpServletRequest request){
        String code = request.getParameter("code");
        String openid = null;
        NormalUserEntity userInfo = new NormalUserEntity();

        try {
//            openid = DataExchangeUtil.getOpenIdByCode(code);
            userInfo = DataExchangeUtil.getUserInfoByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "redirect:https://www.duobifuwu.com/#/baoxiu/"+userInfo.getOpenid();
        return url;
    }

}
