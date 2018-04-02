package com.zyc.cloud.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 2018/3/18
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Entity
@Table(name = "user_skill")
public class UserSkill extends SkillijUserBase{
    @Id
    @GeneratedValue
    private Integer id;

    private Boolean computerBasis;
    private Boolean javaProgram;
    private Boolean javaWebDevelopment;
    private Boolean javaVirtualMachine;
    private Boolean database;
    private Boolean linux;
    private Boolean server;
    private Boolean designPattern;
    private Boolean algorithm;
    private Boolean distributedSystem;
    private Boolean projectExperience;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getComputerBasis() {
        return computerBasis;
    }

    public void setComputerBasis(Boolean computerBasis) {
        this.computerBasis = computerBasis;
    }

    public Boolean getJavaProgram() {
        return javaProgram;
    }

    public void setJavaProgram(Boolean javaProgram) {
        this.javaProgram = javaProgram;
    }

    public Boolean getJavaWebDevelopment() {
        return javaWebDevelopment;
    }

    public void setJavaWebDevelopment(Boolean javaWebDevelopment) {
        this.javaWebDevelopment = javaWebDevelopment;
    }

    public Boolean getJavaVirtualMachine() {
        return javaVirtualMachine;
    }

    public void setJavaVirtualMachine(Boolean javaVirtualMachine) {
        this.javaVirtualMachine = javaVirtualMachine;
    }

    public Boolean getDatabase() {
        return database;
    }

    public void setDatabase(Boolean database) {
        this.database = database;
    }

    public Boolean getLinux() {
        return linux;
    }

    public void setLinux(Boolean linux) {
        this.linux = linux;
    }

    public Boolean getServer() {
        return server;
    }

    public void setServer(Boolean server) {
        this.server = server;
    }

    public Boolean getDesignPattern() {
        return designPattern;
    }

    public void setDesignPattern(Boolean designPattern) {
        this.designPattern = designPattern;
    }

    public Boolean getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Boolean algorithm) {
        this.algorithm = algorithm;
    }

    public Boolean getDistributedSystem() {
        return distributedSystem;
    }

    public void setDistributedSystem(Boolean distributedSystem) {
        this.distributedSystem = distributedSystem;
    }

    public Boolean getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(Boolean projectExperience) {
        this.projectExperience = projectExperience;
    }
}
