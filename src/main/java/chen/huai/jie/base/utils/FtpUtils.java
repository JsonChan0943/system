package chen.huai.jie.base.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class FtpUtils
 * 
 * @version $Revision:0.1,$Date: 2015年10月13日$
 * 
 *          Description: Ftp连接工具类
 * 
 * 
 */
public class FtpUtils {
	private static final Logger log = LoggerFactory.getLogger(FtpUtils.class);

	private String name;
	private String ftpIp;
	private Integer ftpPort;
	private String ftpUsername;
	private String ftpPassword;
	private String ftpEncoding;
	private Integer ftpTimeout;
	private String ftpPath;

	public FtpUtils() {
		this.name = "";
		this.ftpIp = GlobalConst.getConfig("ftp.host");
		this.ftpPort = Integer.parseInt(GlobalConst.getConfig("ftp.port"));
		this.ftpUsername = GlobalConst.getConfig("ftp.username");
		this.ftpPassword = GlobalConst.getConfig("ftp.password");
		this.ftpEncoding = GlobalConst.getConfig("ftp.encoding");
		this.ftpTimeout = Integer.parseInt(GlobalConst.getConfig("ftp.timeout"));
		this.ftpPath = GlobalConst.getConfig("ftp.path");
	}

	public FtpUtils(String ftpHost, String ftpPort, String ftpUsername, String ftpPassword, String ftpencodeing,
			String ftpTimeout, String ftpPath) {
		this.name = "";
		this.ftpIp = ftpHost;
		this.ftpPort = Integer.parseInt(ftpPort);
		this.ftpUsername = ftpUsername;
		this.ftpPassword = ftpPassword;
		this.ftpEncoding = ftpencodeing;
		this.ftpTimeout = Integer.parseInt(ftpTimeout);
		this.ftpPath = ftpPath;
	}

	public String storeByFilename(String filename, InputStream in) throws IOException {
		store(filename, in);
		return filename;
	}

	public File retrieve(String name, String fileName) throws IOException {
		String path = System.getProperty("java.io.tmpdir");
		File file = new File(path, fileName);
		file = getUniqueFile(file);

		FTPClient ftp = getClient();
		OutputStream output = new FileOutputStream(file);
		ftp.retrieveFile(getFtpPath() + name, output);
		ftp.logout();
		ftp.disconnect();
		return file;
	}

	/**
	 * 列出所有文件名
	 * 
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	public String[] listFileNames(String pathname) throws IOException {
		FTPClient ftp = getClient();
		String[] fileNames = ftp.listNames(getFtpPath() + pathname);
		ftp.logout();
		ftp.disconnect();
		return fileNames;
	}

	/**
	 * 复制文件.
	 * 
	 * @param sourceFileName
	 * @param targetFile
	 * @throws IOException
	 */
	public void copyFile(String sourceFileName, String sourceDir, String targetDir) throws IOException {
		ByteArrayInputStream in = null;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			File copyDirFile = new File(targetDir);
			if (!copyDirFile.exists()) {
				copyDirFile.mkdirs();
			}
			FTPClient ftpClient = getClient();
			ftpClient.setBufferSize(1024 * 2);
			// 变更工作路径
			ftpClient.changeWorkingDirectory(sourceDir);
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 将文件读到内存中
			ftpClient.retrieveFile(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"), fos);
			in = new ByteArrayInputStream(fos.toByteArray());
			if (in != null) {
				ftpClient.changeWorkingDirectory(targetDir);
				ftpClient.storeFile(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"), in);
			}
			ftpClient.logout();
			ftpClient.disconnect();
		} finally {
			// 关闭流
			if (in != null) {
				in.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 复制文件夹.
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public void copyDirectiory(String sourceDir, String targetDir) throws IOException {
		// 新建目标目录
		File copyDirFile = new File(targetDir);
		if (!copyDirFile.exists()) {
			copyDirFile.mkdirs();
		}
		// 获取源文件夹当前下的文件或目录
		// File[] file = (new File(sourceDir)).listFiles();
		FTPClient ftpClient = getClient();
		ftpClient.setDefaultTimeout(1000);
		FTPFile[] ftpFiles = ftpClient.listFiles(sourceDir);
		for (int i = 0; i < ftpFiles.length; i++) {
			if (ftpFiles[i].isFile()) {
				copyFile(ftpFiles[i].getName(), sourceDir, targetDir);
			} else if (ftpFiles[i].isDirectory()) {
				copyDirectiory(sourceDir + "/" + ftpFiles[i].getName(), targetDir + "/" + ftpFiles[i].getName());
			}
		}
		ftpClient.logout();
		ftpClient.disconnect();
	}

	/**
	 * ftp端下载文件
	 * 
	 * @param ftpFileName
	 * @param localFile
	 * @throws IOException
	 */
	public void download(String ftpFileName, File localFile) throws IOException {
		// Download.
		OutputStream out = null;
		FTPClient ftp = getClient();
		try {
			// Use passive mode to pass firewalls.
			ftp.enterLocalPassiveMode();

			// Get file info.
			FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
			if (fileInfoArray == null) {
				throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
			}

			// Check file size.
			FTPFile fileInfo = fileInfoArray[0];
			long size = fileInfo.getSize();
			if (size > Integer.MAX_VALUE) {
				throw new IOException("File " + ftpFileName + " is too large.");
			}

			// Download file.
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			if (!ftp.retrieveFile(ftpFileName, out)) {
				throw new IOException("Error loading file " + ftpFileName
						+ " from FTP server. Check FTP permissions and path.");
			}

			out.flush();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	private int store(String remote, InputStream in) {
		try {
			FTPClient ftp = getClient();
			if (ftp != null) {
				String filename = getFtpPath() + remote;
				String name = FilenameUtils.getName(filename);
				String path = FilenameUtils.getFullPath(filename);
				if (!ftp.changeWorkingDirectory(path)) {
					String[] ps = StringUtils.split(path, '/');
					String p = "/";
					ftp.changeWorkingDirectory(p);
					for (String s : ps) {
						p += s + "/";
						if (!ftp.changeWorkingDirectory(p)) {
							ftp.makeDirectory(s);
							ftp.changeWorkingDirectory(p);
						}
					}
				}
				ftp.storeFile(name, in);
				ftp.logout();
				ftp.disconnect();
			}
			in.close();
			return 0;
		} catch (SocketException e) {
			log.error("ftp store error", e);
			return 3;
		} catch (IOException e) {
			log.error("ftp store error", e);
			return 4;
		}
	}

	private FTPClient getClient() throws SocketException, IOException {
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		ftp.setDefaultPort(getFtpPort());
		ftp.connect(getFtpIp());
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			log.warn("FTP server refused connection: {}", getFtpIp());
			ftp.disconnect();
			return null;
		}
		if (!ftp.login(getFtpUsername(), getFtpUsername())) {
			log.warn("FTP server refused login: {}, user: {}", getFtpIp(), getFtpUsername());
			ftp.logout();
			ftp.disconnect();
			return null;
		}
		ftp.setControlEncoding(getFtpEncoding());
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
		return ftp;
	}

	public File getUniqueFile(final File file) {
		if (!file.exists())
			return file;

		File tmpFile = new File(file.getAbsolutePath());
		File parentDir = tmpFile.getParentFile();
		int count = 1;
		String extension = FilenameUtils.getExtension(tmpFile.getName());
		String baseName = FilenameUtils.getBaseName(tmpFile.getName());
		do {
			tmpFile = new File(parentDir, baseName + "(" + count++ + ")." + extension);
		} while (tmpFile.exists());
		return tmpFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpEncoding() {
		return ftpEncoding;
	}

	public void setFtpEncoding(String ftpEncoding) {
		this.ftpEncoding = ftpEncoding;
	}

	public Integer getFtpTimeout() {
		return ftpTimeout;
	}

	public void setFtpTimeout(Integer ftpTimeout) {
		this.ftpTimeout = ftpTimeout;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

}
