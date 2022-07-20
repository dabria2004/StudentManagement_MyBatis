package com.ppt.stu_mybatis.Service;

import com.ppt.stu_mybatis.Model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ppt.stu_mybatis.Mapper.UserMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
    
    @Autowired
    UserMapper userMapper;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\JWD\\stu_mybatis\\src\\main\\java\\com\\ppt\\stu_mybatis\\Reports";
        List<UserBean> user = userMapper.selectAll();
        File file = ResourceUtils.getFile("classpath:User.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(user);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created By", "Phyu Phyu Thin");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource); 
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\User.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\User.pdf");
        }
        return "Report generated in path : " + path;

    }
}

