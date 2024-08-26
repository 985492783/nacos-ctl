package com.alibaba.nacos.ctl.core.bean;

import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2024/8/27 2:56
 */
public class ServerVO {
    
    private String address = "";
    
    private String state;
    
    private Map<String, Object> extendInfo;
    
    private String version;
    
    @Override
    public String toString() {
        return "ServerVO{" + "address='" + address + '\'' + ", state='" + state + '\'' + ", version='" + version + '\''
                + '}';
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }
    
    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }
}
