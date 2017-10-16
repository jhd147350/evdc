package evdc.vianet.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @author jaden
 *
 * 2017年9月17日上午9:04:42
 */
enum FTPMETHOD{
	DELETE,DOWNLOAD,UPLOAD
}
public class FtpServer {
	
	private String url;// FTP服务器hostname
	private int port;// FTP服务器端口  
	private String username; // FTP登录账号  
	private String password; // FTP登录密码  
	private String path; // FTP服务器保存目录  
	private String certPath;
	private String certPasswd;
	FTPClient ftp;
	public FtpServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public FTPClient getFtp() {
		return ftp;
	}

	public void setFtp(FTPClient ftp) {
		this.ftp = ftp;
	}
	

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	
	public String getCertPasswd() {
		return certPasswd;
	}

	public void setCertPasswd(String certPasswd) {
		this.certPasswd = certPasswd;
	}

	/**
	 * 初始化FTPClient对象
	 */
	public boolean init() {
		boolean success = false;  
        ftp = new FTPClient();  
        ftp.setControlEncoding("UTF-8"); 
        try {  
            int reply;  
            ftp.connect(url, port);// 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.enterLocalPassiveMode();
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  
            ftp.setConnectTimeout(500000);
            ftp.login(username, password);// 登录  
            reply = ftp.getReplyCode(); 
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();
                System.out.println("连接失败");
                return success;  
            } 
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            System.out.println(ftp.listFiles().length);
            ftp.makeDirectory(path);  
            ftp.changeWorkingDirectory(path);  
            success = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return success; 
	}
	/**
	 * 上传文件
	 */
	protected boolean uploadFile(String filename, InputStream fileInputStream){  
		boolean success = false;
        try {  
            success = ftp.storeFile(filename, fileInputStream);  
            fileInputStream.close();       
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return success;  
    }  
	/**
	 * 下载文件
	 */
	protected boolean downloadFile(String filename, String localPath){  
		boolean success = false;
        try {  
        	FTPFile ftpFile[] = ftp.listFiles(filename);
        	FileOutputStream fileOutputStream = null;
        	if(ftpFile.length > 0) {
        		fileOutputStream = new FileOutputStream(localPath+ftpFile[0].getName()); 
        		ftp.retrieveFile(ftpFile[0].getName(), fileOutputStream); 
        		success = true;
        	}
        	fileOutputStream.close();     
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return success;  
    }  
	
	/**
	 * 删除文件
	 */
	protected boolean deleteFile(String filename){  
		boolean success = false;
        try {  
        	if(ftp.dele(filename)>0) {
        		success = true;
        	}
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return success;  
    }
    /** 
     * 执行 * 
     */  
    public void execute(FTPMETHOD executeMethod, String filename, InputStream fileInputStream, String localPath) {  
    	boolean flag = false;
    	try { 
    		
    		switch (executeMethod) {
			case UPLOAD:
				flag = uploadFile(filename, fileInputStream);
				break;
			case DOWNLOAD:
				flag = downloadFile(filename, localPath);
				break;
			case DELETE:
				flag = deleteFile(filename);
				break;
			default:
				break;
			} 
            System.out.println("文件服务器执行结果：" + flag);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    public FTPMETHOD getMethod(String s) {
    	return FTPMETHOD.valueOf(s);
    }
    /**
     * 断开连接
     * */
    
    public void destoryFtpCon() {
    	if (ftp.isConnected()) {  
            try {  
                ftp.disconnect();  
            } catch (IOException ioe) {  
            }  
        }	  
    }
    //测试  
    /*public static void main(String[] args) {  
        FtpServer ftpServer = new FtpServer();
        ftpServer.setPort(21);
        ftpServer.setUrl("172.16.136.20");
        ftpServer.setUsername("davin");
        ftpServer.setPassword("paaspassword");
        ftpServer.setPath("/paasfolder/evdcFile");
        
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		System.out.println(df.format(new Date()));// 
        File file = new File("E:\\temp\\photo.jpg");
        
        if(ftpServer.init()) {
        	*//**
        	 * 下载
        	 * ftpServer.execute(FTPMETHOD.DOWNLOAD, "photo.jpg", null, "E:\\temp\\");
        	 * 删除
        	 * ftpServer.execute(FTPMETHOD.DELETE, "photo.jpg", null, null);
        	 * 上传
        	 * try {
        	 * 	ftpServer.execute(FTPMETHOD.UPLOAD, "photo.jpg", new FileInputStream(file), null);
        	 * } catch (FileNotFoundException e) {
        	 * 	// TODO Auto-generated catch block
        	 * 	e.printStackTrace();
        	 * }
        	 * *//*
        }
    }  */
    
	private KeyManager getKeyManager() {
    	  KeyStore key_ks = null;
    	  KeyManager[] km = null;
		try {
			key_ks = KeyStore.getInstance("jks");
			String p = this.getClass().getResource("/").getPath();
			System.out.println(p);
			key_ks.load(new FileInputStream(p+certPath), certPasswd.toCharArray());
			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
					.getDefaultAlgorithm());

			kmf.init(key_ks, certPasswd.toCharArray());
			
			km = kmf.getKeyManagers();
    	  	System.out.println("km len: " + km.length);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return km[0];
    }
    private TrustManager getTrustManager() {
    	TrustManager[] tm = null;
    	try {
	  	  	KeyStore trust_ks = KeyStore.getInstance("jks");
		  	String p = this.getClass().getResource("/").getPath();
			System.out.println(p);
			trust_ks.load(new FileInputStream(p+certPath), certPasswd.toCharArray());
	  	  
		  	TrustManagerFactory tf = TrustManagerFactory
		  	    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
		  	tf.init(trust_ks);
		  	tm = tf.getTrustManagers();
		  	System.out.println("tm len: " + tm.length);  
	    } catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	  return tm[0];
  	 } 	 
}
