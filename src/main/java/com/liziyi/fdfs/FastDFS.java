package com.liziyi.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @version 1.0
 * @Description 上传图片，可以实现
 * @Author liziyi
 * @CreateDate 2021/1/1 14:51
 * @UpdateUser
 * @UpdateDate
 * @UpdateRemark
 */
public class FastDFS {
    public static void main(String[] args) {
        fileUpload();
    }
    //上传文件的方法
    public static void fileUpload() {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            //1.加载配置文件，默认去classpath下加载
            ClientGlobal.init("fdfs_client.conf");
            //2.创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //3.创建TrackerServer对象
            trackerServer = trackerClient.getConnection();
            //4.创建StorageServler对象
            storageServer = trackerClient.getStoreStorage(trackerServer);
            //5.创建StorageClient对象，这个对象完成对文件的操作
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            //6.上传文件  第一个参数：本地文件路径 第二个参数：上传文件的后缀 第三个参数：文件信息
            String[] uploadArray = storageClient.upload_file("D:\\鸡腿.jpg", "jpg", null);
            for (String str : uploadArray) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
