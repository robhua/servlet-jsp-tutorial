package com.servletjsp.base.file.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class ReportBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            template;
    private String            folder;
    private String            fileName;
    private Workbook          workbook;

    private List<Object>      dataTable        = new ArrayList<>();
    private List<RptCell>     reportCellList   = new ArrayList<>();

    private boolean           completed;
    private String            generatedReportFile;

    /**
     * 
     */
    public void resetReportCellList() {
        reportCellList.clear();
    }

    /**
     * Clear data table
     */
    public void resetDataTable() {
        dataTable.clear();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getGeneratedReportFile() {
        return generatedReportFile;
    }

    public void setGeneratedReportFile(String generatedReportFile) {
        this.generatedReportFile = generatedReportFile;
    }

    public List<RptCell> getReportCellList() {
        return reportCellList;
    }

    public void setReportCellList(List<RptCell> reportCellList) {
        this.reportCellList = reportCellList;
    }

    public List<Object> getDataTable() {
        return dataTable;
    }

    public void setDataTable(List<Object> dataTable) {
        this.dataTable = dataTable;
    }

}
