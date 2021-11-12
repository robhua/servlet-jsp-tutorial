package com.servletjsp.base.file.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.servletjsp.base.common.constants.CommonConstants;
import com.servletjsp.base.common.constants.SystemErrorCodeConstants;
import com.servletjsp.base.common.expceitons.ApplicationSystemException;
import com.servletjsp.base.file.util.CrudSettingUtil;

public class ReportUtils {
    private static final String TBL_DATA_PREFIX         = "tbl";
    private static final String EL_START                = "${";
    private static final String EL_END                  = "}";
    private static final String FILE_DOWNLOAD_COMPLETED = "file_download_completed";

    private ReportUtils() {
        // nop
    }

    /**
     * Generate Report File
     * 
     * @param reportBean {@link ReportBean}
     */
    public static void generateReport(ReportBean reportBean) {
        Workbook _workbook = reportBean.getWorkbook();
        /* Create work book and copy report template for the first time */
        try {
            if (_workbook != null) {
                String _reportFile = copyReportTemplate(reportBean.getTemplate(), reportBean.getFolder(),
                        reportBean.getFileName());
                List<RptCell> _reportCellList = obtainReportTemplateCellStyle(_reportFile, reportBean, true);
                reportBean.setReportCellList(_reportCellList);

                _workbook = new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream(new File(_reportFile))),
                        CommonConstants.INT_1000);
                reportBean.setWorkbook(_workbook);

                reportBean.setGeneratedReportFile(_reportFile);
            }

            /* Write data to report file */
            if (_workbook != null) {
                if (CollectionUtils.isNotEmpty(reportBean.getDataTable())) {
                    writeReportData(reportBean);
                }
            }

        } catch (IOException e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM, e);
        } finally {
            /* Release resources when process completed */
            if (reportBean.isCompleted()) {
                reportBean.resetDataTable();
                reportBean.resetReportCellList();
                executeReleaseReosurces(null, null, reportBean.getWorkbook());
            }
        }

    }

    /**
     * Release resource
     * 
     * @param inputStream  {@link InputStream}
     * @param outputStream {@link OutputStream}
     * @param workbook     {@link Workbook}
     */
    private static void executeReleaseReosurces(InputStream inputStream, OutputStream outputStream, Workbook workbook) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            throw new ApplicationSystemException(SystemErrorCodeConstants.SYSTEM, e);
        }

    }

    /**
     * Write data to report file
     * 
     * @param reportBean {@link ReportBean}
     */
    private static void writeReportData(ReportBean reportBean) {
        // TODO Auto-generated method stub

    }

    /**
     * Obtain report cell style list
     * 
     * @param path       Path of report file
     * @param reportBean ReportBean
     * @param shift      Shift Last Row
     */
    private static List<RptCell> obtainReportTemplateCellStyle(String path, ReportBean reportBean, boolean shift) {
        List<RptCell> _cells = new ArrayList<RptCell>();
        // TODO Auto-generated method stub
        return _cells;

    }

    /**
     * Copy report template
     * 
     * @param template Report template
     * @param folder   Report folder
     * @param fileName Report file name
     * @return Report file path
     */
    private static String copyReportTemplate(String template, String folder, String fileName) {

        if (StringUtils.isNoneBlank(template)) {
            String _exportFolder = CrudSettingUtil.getSettingValueByKey(CrudSettingUtil.EXPORT_FOLDER);
            String _folder = _exportFolder + File.separator + folder;
            File _directory = new File(_folder);

            if (!_directory.exists()) {
                _directory.mkdirs();
            }

            ClassLoader _classLoader = ReportUtils.class.getClassLoader();
            File _rptTemplateForm = new File(_classLoader.getResource(template).getFile());

            String _path = _folder + File.separator + fileName;
            File _rptTemplateTo = new File(_path);
        }
        // TODO Auto-generated method stub
        return null;
    }
}
