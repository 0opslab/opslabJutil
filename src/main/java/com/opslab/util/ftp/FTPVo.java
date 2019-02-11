package com.opslab.util.ftp;

/**
 * 用于包装FTP的相关的信息
 */
public class FTPVo {
    private String hostName;
    private int port;
    private String username;
    private String password;
    private String remoteDir;
    private String localDir;
    private String remoteEncoding;
    private boolean passiveMode;


    public FTPVo(String hostName, int port, String username, String password, String remoteDir, String localDir,
                 String remoteEncoding, boolean passiveMode) {
        this.hostName = hostName;
        this.port = port;
        this.remoteDir = remoteDir;
        this.localDir = localDir;
        this.remoteEncoding = remoteEncoding;
        this.passiveMode = passiveMode;
        this.username = username;
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }

    public String getRemoteEncoding() {
        return remoteEncoding;
    }

    public void setRemoteEncoding(String remoteEncoding) {
        this.remoteEncoding = remoteEncoding;
    }


    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }
}
