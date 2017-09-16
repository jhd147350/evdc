package evdc.vianet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileTran {
	public static boolean uploadFile(String url,// FTP服务器hostname  
            int port,// FTP服务器端口  
            String username, // FTP登录账号  
            String password, // FTP登录密码  
            String path, // FTP服务器保存目录  
            String filename, // 上传到FTP服务器上的文件名  
            InputStream input // 输入流  
    ){  
        boolean success = false;  
        FTPClient ftp = new FTPClient();  
        ftp.setControlEncoding("UTF-8");  
        try {  
            int reply;  
            ftp.connect(url, port);// 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.login(username, password);// 登录  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                return success;  
            }  
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
            ftp.makeDirectory(path);  
            ftp.changeWorkingDirectory(path);  
            ftp.storeFile(filename, input);  
            input.close();  
            ftp.logout();  
            success = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return success;  
    }  
  
    /** 
     * 将本地文件上传到FTP服务器上 * 
     */  
    public static void upLoadFromProduction(String url,// FTP服务器hostname  
            int port,// FTP服务器端口  
            String username, // FTP登录账号  
            String password, // FTP登录密码  
            String path, // FTP服务器保存目录  
            String filename, // 上传到FTP服务器上的文件名  
            String orginfilename // 输入流文件名  
       ) {  
        try {  
            FileInputStream in = new FileInputStream(new File(orginfilename));  
            boolean flag = uploadFile(url, port, username, password, path,filename, in);  
            System.out.println(flag);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
     //测试  
    public static void main(String[] args) {  
          
        upLoadFromProduction("172.16.136.20", 21, "davin", "paaspassword", "paasfolder/evdcFile", "hanshibo.doc", "E:/temp/photo.jpg");  
    }  
}
