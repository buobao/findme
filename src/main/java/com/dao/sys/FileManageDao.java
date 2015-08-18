package com.dao.sys;

import com.entity.sys.FileManage;

import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
public interface FileManageDao extends BaseEntityDao<FileManage, String> {
    /**
     * 根据keyID来查找文件
     * @param keyId
     * @return
     */
    List<FileManage> getFileById(String keyId);

    /**
     * 根据keyId和targetClass查找文件
     * @param keyId
     * @param targetClass
     * @return
     */
    List<FileManage> getFileByIdAndTarget(String keyId, String targetClass);

    /**
     * 根据keyID,taskId,processInstanceId来查找文件
     * @param keyId -- 对象的Id
     * @param taskId -- 节点的Id
     * @param processInstanceId ---流程实例的Id
     * @return
     */
    List<FileManage> getFileByTask(String keyId, String taskId, String processInstanceId);

    List<FileManage> getFileByTask(String keyId, String taskId, String proId, String type);
}