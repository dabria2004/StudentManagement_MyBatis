package com.ppt.stu_mybatis.Service;

import com.ppt.stu_mybatis.Model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ppt.stu_mybatis.Mapper.UserMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportService {
    
    @Autowired
    UserMapper userMapper;

    public void exportReport(String reportFormat,HttpServletResponse response ) throws JRException, IOException {
        
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

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=User.pdf;");
            JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream() );
            // JRXlsxExporter exporter = new JRXlsxExporter();
            // exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            // exporter.setExporterOutput();
            // JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
           
        }
        if(reportFormat.equalsIgnoreCase("excel")){
            JRXlsxExporter exporter = new JRXlsxExporter(); // initialresponse.getOutputStream()ize exporter 
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // set compiled report as input
            response.setContentType("application/xlsx");
            response.addHeader("Content-Disposition", "inline; filename=User.xlsx;");
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));  
            // set output file via path with filename
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true); // setup configuration
            configuration.setDetectCellType(true);
            exporter.setConfiguration(configuration); // set configuration
            exporter.exportReport();
            
             }
        // return "Report generated in path : " + path;

    }
}

// if(reportFormat.equalsIgnoreCase("html")){

//     JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\student.html");
// }
// else if(reportFormat.equalsIgnoreCase("pdf")){
//     /* JRPdfExporter exporter = new JRPdfExporter();
//     exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//     exporter.setExporterOutput(response.getOutputStream()); */
//     JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
//     response.setContentType("application/pdf");
//     response.addHeader("Content-Disposition", "inline; filename=student.pdf;");

// }
// else if(reportFormat.equalsIgnoreCase("excel")){
//     JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
//   exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // set compiled report as input

//   SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//   configuration.setOnePagePerSheet(true); // setup configuration
//   configuration.setDetectCellType(true);
//   exporter.setConfiguration(configuration); 
//   exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream())); 
//   response.setContentType("application/xlsx");
//     response.addHeader("Content-Disposition", "inline; filename=student.xlsx;"); 
//   exporter.exportReport();
// }

