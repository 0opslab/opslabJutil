package evilp0s.FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * 一些FTP相关操作的函数集
 */
public class FunctionSet {
    //创建变连接FTP
    private FTPClient createFTPClien(FTPVo vo) throws IOException {
        FTPClient client = new FTPClient();
        int reply = -1;
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
    }

}
