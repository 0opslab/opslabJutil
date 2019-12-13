package com.opslab.util.ftp;

import com.opslab.util.CheckUtil;
import com.opslab.util.FileUtil;
import com.opslab.util.JacksonUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FTP工具类的实现
 */
public class FTPUtilImpl implements FTPUtil {
    private Logger logger = LoggerFactory.getLogger(FTPUtilImpl.class);
    private FTPClient client;
    private FTPVo vo;


    public FTPUtilImpl(FTPVo vo) throws IOException {
        this.vo = vo;
        client = createFTPClien(vo);
    }

    //创建变连接FTP
    private FTPClient createFTPClien(FTPVo vo) {
        FTPClient client = new FTPClient();
        int reply = -1;
        try {
            client.connect(vo.getHostName(), vo.getPort());
            client.login(vo.getUsername(), vo.getPassword());
            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                return null;
            } else {
                client.setControlEncoding(vo.getRemoteEncoding());
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
                if (vo.isPassiveMode()) {
                    client.enterLocalPassiveMode();
                } else {
                    client.enterRemotePassiveMode();
                }
                client.cwd(vo.getRemoteDir());
            }
            return client;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    //通过FTP响应码判断是否操作成功
    public boolean reply(String operation) {
        int replyCode = client.getReplyCode();
        FTPLog log = new FTPLog();
        log.setHost(vo.getHostName());
        log.setOperation(operation);
        log.setLocalFile("");
        log.setRemoteFile("");
        log.setReplyCode(replyCode);
        log.setReplyCodeDesc(FTPConstant.REPLYCODE.get(replyCode));
        logger.info(JacksonUtil.toJson(log));
        return FTPReply.isPositiveCompletion(replyCode);
    }

    public boolean reply(String operation, String localFile, String remoteFile) {
        int replyCode = client.getReplyCode();
        FTPLog log = new FTPLog();
        log.setHost(vo.getHostName());
        log.setOperation(operation);
        log.setLocalFile(localFile);
        log.setRemoteFile(remoteFile);
        log.setReplyCode(replyCode);
        log.setReplyCodeDesc(FTPConstant.REPLYCODE.get(replyCode));
        logger.info(JacksonUtil.toJson(log));
        return FTPReply.isPositiveCompletion(replyCode);
    }

    @Override
    public boolean isExists(String fileName) {
        List<String> list = listFile(vo.getRemoteDir());
        if (list.contains(fileName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean downLoad(String fileName) {
        String localfileName = vo.getLocalDir() + File.separator + fileName;
        FileUtil.createFiles(localfileName);
        OutputStream out = null;
        try {
            out = new FileOutputStream(localfileName, true);
            client.retrieveFile(new String(fileName.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return reply("DOWNLOAD", localfileName, fileName);
    }

    @Override
    public boolean downLoadDir(String directory) {
        List<String> files = listFile(directory);
        for (String s : files) {
            downLoad(s);
        }
        return true;
    }

    @Override
    public boolean deleteFile(String fileName) {
        if (isExists(fileName)) {
            try {
                client.deleteFile(new String(fileName.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
                return reply("DELETE", "", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteDir(String directory) {
        List<String> files = listFile(directory);
        try {
            for (String s : files) {
                deleteFile(s);
            }
            List<String> dirs = listDir(directory);
            for (int i = dirs.size() - 1; i >= 0; i--) {
                client.removeDirectory(new String(dirs.get(i).getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
            }
            client.removeDirectory(new String(directory.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply("DELETE", "", directory);
    }

    @Override
    public boolean putFile(String fileName, String remoteFileName, boolean isDelete) {
        File file = new File(fileName);
        return putFile(file, remoteFileName, isDelete);
    }

    @Override
    public boolean putFile(File file, String remoteFileName, boolean isDelete) {
        String fileName = remoteFileName;
        String path = "";
        String parent = getParentPath(remoteFileName);
        if (remoteFileName.lastIndexOf("/") != -1) {
            path = remoteFileName.substring(0, remoteFileName.lastIndexOf("/"));
            fileName = remoteFileName.substring(remoteFileName.lastIndexOf("/") + 1);
            mkDir(path);
            changeWorkDir(path);
        }
        try (InputStream in = new FileInputStream(file)) {
            if (isDelete) {
                deleteFile(new String(file.getName().getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
            }
            client.appendFile(new String(fileName.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"), in);
            return reply("UPLOAD", file.getAbsoluteFile().toString(), remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean putDir(String fileName, String remoteDir) {
        File file = new File(fileName);
        return putDir(file, remoteDir);
    }

    @Override
    public boolean putDir(File file, String remoteDir) {
        List<File> list = FileUtil.listFile(file);
        for (File f : list) {
            String name = f.getAbsolutePath();
            name = name.substring(name.indexOf(file.getName())).replaceAll("\\\\", "/");
            putFile(f, remoteDir + "/" + name, true);
        }
        return true;
    }

    @Override
    public List<String> listFile(String directory) {
        List<String> list = new ArrayList<String>();
        try {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++) {
                String t = (directory + "/" + files[i].getName()).replaceAll("//", "/");
                if (files[i].isFile()) {
                    list.add(t);
                } else if (files[i].isDirectory()) {
                    list.addAll(listFile((t + "/").replaceAll("//", "/")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Map<String, FileAttr> listFileAttr(String directory) {
        Map<String, FileAttr> map = new HashMap<String, FileAttr>();
        try {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    FTPFile file = files[i];
                    String fileName = directory + file.getName();
                    FileAttr attr = new FileAttr();
                    attr.setFileName(fileName);
                    attr.setModifyTime(file.getTimestamp().getTime());
                    attr.setSize(file.getSize());
                    map.put(fileName, attr);
                } else if (files[i].isDirectory()) {
                    map.putAll(listFileAttr(directory + files[i].getName() + "/"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean changeWorkDir(String directory) {
        try {
            client.cwd(directory);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getWorkDir() {
        try {
            return client.printWorkingDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean mkDir(String directory) {
        directory = directory.replaceAll("//", "/");
        if (directory.startsWith("/")) {
            directory = directory.substring(1);
        }
        if (directory.endsWith("/")) {
            directory = directory.substring(0, directory.length() - 1);
        }
        try {
            String[] str = (new String(directory.getBytes(vo.getRemoteEncoding()), "ISO-8859-1")).split("/");
            String t = "";
            String parnet = "";
            for (int i = 0; i < str.length; i++) {
                t += ("/" + str[i]);
                if (!isExists(t.substring(1))) {
                    client.makeDirectory(str[i]);
                }
                client.changeWorkingDirectory(str[i]);
                parnet += "../";
            }
            if (str.length >= 1) {
                client.changeWorkingDirectory(parnet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean changName(String oldName, String newName) {
        return false;
    }

    public LinkedList<String> listDir(String directory) {
        LinkedList<String> list = new LinkedList<String>();
        try {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++) {
                String t = (directory + "/" + files[i].getName()).replaceAll("//", "/");
                if (files[i].isDirectory()) {
                    list.add(t);
                    list.addAll(listDir(t + "/"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public FTPClient client() {
        return client;
    }

    @Override
    public void destory() {
        if (CheckUtil.valid(client)) {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String getParentPath(String file) {
        if (file.indexOf("/") != -1) {
            String temp = null;
            Pattern p = Pattern.compile("[/]+");
            Matcher m = p.matcher(file);
            int i = 0;
            while (m.find()) {
                temp = m.group(0);
                i += temp.length();
            }
            String parent = "";
            for (int j = 0; j < i; j++) {
                parent += "../";
            }
            return parent;
        } else {
            return "./";
        }
    }

    private String getRelativePath(File path) {
        String path1 = path.getPath();
        String path2 = path.getAbsolutePath();
        return null;

    }
}
