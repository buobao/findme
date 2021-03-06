package com.service.sys;

import com.bean.BaseEnum;
import com.bean.EnumManage;
import com.bean.Pager;
import com.entity.core.Comment;
import com.entity.sys.PushLog;
import com.entity.sys.Users;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by dqf on 2015/8/18.
 */
public interface PushLogService extends BaseEntityService<PushLog, String> {

    /**
     * 用户下推送记录（所有的）
     *
     * @param pager
     *            分页对象（不能为空）
     * @param users
     *            用户（null则无视）
     * @param pushWay
     *            推送类别（null则无视）
     * @param pushType
     *            推送方式（null则无视）
     * @param pushErrcode
     *            推送错误code（ErrorCodeEnum；-1则无视）
     * @param states
     *            状态数组（DocumentStateEnum；null则无视）
     *
     * @return Pager
     */
    public Pager queryByPager(Pager pager, Users users, EnumManage.PushWayEnum pushWay, EnumManage.PushTypeEnum pushType, int pushErrcode, BaseEnum.StateEnum... states);
    public Pager queryByPager(Pager pager, Users users, EnumManage.PushWayEnum pushWay, EnumManage.PushTypeEnum pushType, int pushErrcode, Date date, BaseEnum.StateEnum... states);
    /**
     * 用户下推送记录（已发送/未发送）
     *
     * @param pager
     *            分页对象（不能为空）
     * @param users
     *            用户（null则无视）
     * @param pushWay
     *            推送类别（null则无视）
     * @param pushType
     *            推送方式（null则无视）
     * @param pushErrcode
     *            推送错误code（ErrorCodeEnum；-1则无视）
     * @param isSend
     *            是否已发送
     * @param states
     *            状态数组（DocumentStateEnum；null则无视）
     *
     * @return Pager
     */
    public Pager queryByPager(Pager pager, Users users, EnumManage.PushWayEnum pushWay, EnumManage.PushTypeEnum pushType, int pushErrcode, boolean isSend, BaseEnum.StateEnum... states);

    /**
     * 转换为Map集合
     * @param pushLog
     * @return
     */
    public Map<String, Object> getPushLogListMap(PushLog pushLog);

    /**
     * 保存并启用
     * @param pushLog
     * @return
     */
    public String saveAndEnable(PushLog pushLog);

    /**
     * 发送批注通知
     * @param comment
     * @param target
     */
    public void sendCommentNotification(Comment comment, String target);

    /**
     * 发送批注通知(针对多人)
     * @param comment
     * @param usersSet
     */
    public void sendCommentNotification(Comment comment, Set<Users> usersSet);
    /**
     * App用
     * 发送批注通知(针对多人)
     * @param comment
     * @param usersSet
     */
    public void sendCommentNotificationApp(Comment comment, Set<Users> usersSet);
}
