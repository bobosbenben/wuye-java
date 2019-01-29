package com.duobi.wuye.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CosClientUtil {

    private static COSClient cosClientInstance;
    private static String bucketName;

    private CosClientUtil(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDEOn7KCXt2BKSj2FMJ5kEOkcXTOVS567z", "rXDjcR9ihsy2LGJAtPq7Wnsqg3LeMtKa");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        bucketName = "duobifuwu-1252535629";

        cosClientInstance = cosClient;
    }

    public String uploadFile(File uploadFile) throws Exception{

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
//        File localFile = new File("jy.jpg");
//        File localFile = uploadFile;
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        // https://duobifuwu-1252535629.cos.ap-beijing.myqcloud.com/ceshi.png
        String key = "wuye/user/"+uploadFile.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, uploadFile);
        PutObjectResult putObjectResult = cosClientInstance.putObject(putObjectRequest);

        cosClientInstance.shutdown();

        String id = putObjectResult.getRequestId();
        if (id == null) throw new Exception("上传文件失败");

        String url = "https://duobifuwu-1252535629.cos.ap-beijing.myqcloud.com/wuye/"+java.net.URLEncoder.encode(uploadFile.getName(),"utf-8");
        return url;
    }



}
