package com.service.common;

import com.bean.Result;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dqf on 2015/8/17.
 */
@Service
public class ResultService {
    private static Logger logger = LoggerFactory.getLogger(ResultService.class);

    public ResultService() {
    }

    public Result build(int state, long errorCode, String message, Map<String, Object> data) {
        return new Result(state, message, errorCode, data);
    }

    public Result success() {
        return new Result(1, "操作成功", 0L, (Map)null);
    }

    public Result error() {
        return new Result(0, "操作失败", 1L, (Map)null);
    }

    public Result successWithId(String id) {
        HashMap map = new HashMap();
        map.put("id", id);
        return this.build(1, 0L, "操作成功", map);
    }

    public Result success(String message) {
        return new Result(1, message, 0L, (Map)null);
    }

    public Result error(String message) {
        return new Result(0, message, 1L, (Map)null);
    }

    public String toAjax(Result result) {
        int errorCode = result.getState();
        String errorMessage = StringUtils.isNotEmpty(result.getMessage())?result.getMessage():"";
        JSONObject object = result.getData() != null?JSONObject.fromObject(result.getData()):null;
        HashMap mpMap = new HashMap();
        HashMap rsMap = new HashMap();
        rsMap.put("errorCode", Integer.valueOf(errorCode));
        rsMap.put("errorMessage", errorMessage);
        mpMap.put("result", JSONObject.fromObject(rsMap));
        mpMap.put("data", object == null?"":object);
        return JSONObject.fromObject(mpMap).toString();
    }
}

