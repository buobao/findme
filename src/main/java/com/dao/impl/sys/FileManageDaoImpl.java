package com.dao.impl.sys;

import com.bean.BaseEnum;
import com.dao.sys.FileManageDao;
import com.entity.sys.FileManage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

/**
 * Created by dqf on 2015/8/18.
 */
@Repository
public class FileManageDaoImpl extends BaseEntityDaoImpl<FileManage, String> implements FileManageDao {

    private String pathname;
    private File file;

    //删除 处理附件
    @Override
    public void delete(FileManage fileManage) {
        pathname = fileManage.getUrl();
        if(!StringUtils.isEmpty(pathname)){
            file = new File(pathname);
            file.delete();
        }
        super.delete(fileManage);
    }

    @Override
    public void delete(String id) {
        FileManage fileManage = load(id);
        this.delete(fileManage);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            this.delete(id);
        }
    }

    @Override
    public List<FileManage> getFileById(String keyId) {
        String hql =  "From FileManage fm where fm.keyId=:k and fm.state=:s and fm.taskId=null and fm.proIntanceId=null order by fm.createDate desc";
        List<FileManage> fileList = getSession().createQuery(hql).setParameter("k",keyId).setParameter("s", BaseEnum.StateEnum.Enable).list();
        return fileList;
    }

    @Override
    public List<FileManage> getFileByIdAndTarget(String keyId, String targetClass) {
        String hql =  "From FileManage fm where fm.keyId=:k and fm.state=:s and fm.taskId=null and fm.proIntanceId=null and fm.targetClass=:f order by fm.createDate desc";
        List<FileManage> fileList = getSession().createQuery(hql).setParameter("k",keyId).setParameter("f",targetClass).setParameter("s", BaseEnum.StateEnum.Enable).list();
        return fileList;
    }

    @Override
    public List<FileManage> getFileByTask(String keyId, String taskId, String processInstanceId) {
        String hql =  "From FileManage fm where fm.keyId=:k and fm.state=:s and fm.taskId=:t and fm.proIntanceId=:p order by fm.createDate desc";
        List<FileManage> fileList = getSession().createQuery(hql).setParameter("k",keyId).setParameter("t",taskId).setParameter("p",processInstanceId).setParameter("s", BaseEnum.StateEnum.Enable).list();
        return fileList;
    }

    @Override
    public List<FileManage> getFileByTask(String keyId, String taskId, String proId, String type) {
        String hql =  "From FileManage fm where fm.keyId=:k and fm.state=:s and fm.taskId=:t and fm.proIntanceId=:p and fm.type=:tp order by fm.createDate desc";
        List<FileManage> fileList = getSession().createQuery(hql).setParameter("k",keyId)
                .setParameter("t", taskId).setParameter("p",proId).setParameter("tp",type)
                .setParameter("s", BaseEnum.StateEnum.Enable).list();
        return fileList;
    }
}


























