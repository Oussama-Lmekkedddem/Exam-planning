package com.PE.springprj.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.IOException;


public class ExamForm {

    @NotNull(message = "This filed is required")
    private Long elementId;
    @NotNull(message = "This filed is required")
    private String startTime;
    @NotNull(message = "This filed is required")
    private String expectedDuration;
    @NotNull(message = "This filed is required")
    private Long coordinatorId;
    @NotNull(message = "This filed is required")
    private String contractType;
    @NotNull(message = "This filed is required")
    private String actualDuration;
    @NotNull(message = "This filed is required")
    private String examDate;
    @NotNull(message = "This filed is required")
    private String universityYear;
    @NotNull(message = "This filed is required")
    private String semester;
    @NotNull(message = "This filed is required")
    private String Typeelement;
    private MultipartFile epreuveFile;
    private MultipartFile pvFile;

    private String rapport;

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(String expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getActualDuration() {
        return actualDuration;
    }

    public void setActualDuration(String actualDuration) {
        this.actualDuration = actualDuration;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getUniversityYear() {
        return universityYear;
    }

    public void setUniversityYear(String universityYear) {
        this.universityYear = universityYear;
    }

    public String getTypeelement() {
        return Typeelement;
    }

    public void setTypeelement(String typeelement) {
        Typeelement = typeelement;
    }



    public void setEpreuveFile(MultipartFile epreuveFile) {
        this.epreuveFile = epreuveFile;
    }

    public MultipartFile getEpreuveFile() {
        return epreuveFile;
    }

    public MultipartFile getPvFile() {
        return pvFile;
    }

    public void setPvFile(MultipartFile pvFile) {
        this.pvFile = pvFile;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


}
