package com.team3.memberservice.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;


public class FTP_SERVER {

    @Value("${ftp.name}")
    private String ftpName;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.server}")
    private String ftpServer;
    @Value("${ftp.port}")
    private int ftpPort;


    public FTPClient connectFTP(){

        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");

        try {
            ftpClient.connect(ftpServer,ftpPort);

            int replyCode = ftpClient.getReplyCode();

            ftpClient.login(ftpName,ftpPassword);
            return ftpClient;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnectFTP(FTPClient ftpClient){
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean uploadFile(long memberId, MultipartFile imgFile) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpServer);
            ftpClient.login(ftpName, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

            // memberId를 파일명으로 사용하여 파일 경로 설정
            String fileName = memberId + ".jpg"; // 예시: memberId.jpg
            String remoteFilePath = fileName;

            // MultipartFile에서 InputStream을 가져와 FTP 서버로 업로드
            InputStream inputStream = imgFile.getInputStream();
            boolean uploaded = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();

            if (uploaded) {
                System.out.println("File uploaded successfully.");
            } else {
                System.out.println("File upload failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return true; // 업로드 결과를 반환하는 부분입니다. 업로드 성공 여부를 반환하도록 수정해야 합니다.
        }
    }

    //선택된 디렉토리(폴더)에 존재하는 파일이름을 모두 불러옵니다.
    public static void ftpFileReadFiles(String uri, String id, String pw,String directoryLocation){
        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            ftp.setControlEncoding("UTF-8");
            ftp.connect(uri);
            ftp.login(id, pw);
            ftp.changeWorkingDirectory(directoryLocation);//파일 가져올 디렉터리 위치

            for(String fileName :ftp.listNames()){
                System.out.println(fileName);
            }

            ftp.logout();
        } catch (SocketException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (ftp != null && ftp.isConnected()) {
                try {
                    ftp.disconnect();//ftp연결 끊기
                } catch (IOException e) {
                }
            }
        }
    }
}
