package com.cskaoyan.mall.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FileUploadUtils {


    public static Map<String, Object> parseRequest(HttpServletRequest request) {

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //设置一个缓存仓库，如果文件很大，那么就边缓存边上传
        factory.setRepository(repository);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置上传的文件名中文乱码问题
        upload.setHeaderEncoding("utf-8");
        // bytes
        //upload.setFileSizeMax(1024);
        // Parse the request
        Map<String, Object> params = new HashMap<>();
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()){
                FileItem fileItem = iterator.next();
                if(fileItem.isFormField()){
                    //是一个常规的form表单数据
                    processFormField(fileItem, params);
                }else {
                    //上传的文件
                    processUploadedFile(fileItem, params, request);
                }
            }
            //map里面有哪些数据？
            System.out.println(params);
            //  BeanUtils.populate(product, params)
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return params;
    }

    private static void processUploadedFile(FileItem fileItem, Map<String, Object> params, HttpServletRequest request) {
        String fieldName = fileItem.getFieldName();
        String fileName = fileItem.getName();
        String s = UUID.randomUUID().toString();
        fileName = s + "-" + fileName;
        System.out.println("file:" + fieldName);
        System.out.println("file:" + fileName);
        //取hashcode
        int hashCode = fileName.hashCode();
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();
        String uploadPath = "upload";
        for (char aChar : chars) {
            uploadPath = uploadPath + "/" + aChar;
        }
        String relativePath = uploadPath + "/" +  fileName;
        String realPath = request.getServletContext().getRealPath(relativePath);
        //   http://localhost/app/upload/1.jpg
        File file = new File(realPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            fileItem.write(file);
            params.put(fieldName, relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通的form表单数据，name属性以及对应的值
     * @param fileItem
     * @param params
     */
    private static void processFormField(FileItem fileItem, Map<String, Object> params) {
        String fieldName = fileItem.getFieldName();
        String value = null;
        //反射吗？
        try {
            value = fileItem.getString("utf-8");
            params.put(fieldName, value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(fieldName + ":" + value);
    }
}
