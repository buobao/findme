package com.service.impl.core;

import com.dao.core.ProSignInDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.ProInfo;
import com.entity.core.ProSignIn;
import com.entity.sys.Locations;
import com.service.core.ProSignInService;
import com.service.sys.LocationsService;
import com.utils.DataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amin on 2015/2/27.
 */
@Service
public class ProSignInServiceImpl extends BaseLimitServiceImpl<ProSignIn,String> implements ProSignInService {

    @Resource
    ProSignInDao proSignInDao;
    @Resource
    private LocationsService locationsService;

    @Override
    public BaseEntityDao<ProSignIn, String> getBaseEntityDao() {
        return proSignInDao;
    }

    /**
     * app用
     * @param proSignIn
     * @return
     */
    @Override
    public Map<String, Object> getDetailMap(ProSignIn proSignIn) {
        String tmp;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(proSignIn.getId())?proSignIn.getId():"");

        ProInfo proInfo = proSignIn.getProInfo();
        if(proInfo==null){
            map.put("proInfoId", "");
            map.put("proInfoName", "");
            map.put("proInfoArea", "");
            map.put("proInfoAddress", "");
        }else{
            map.put("proInfoId", proInfo.getId());
            map.put("proInfoName", proInfo.getName());
            map.put("proInfoArea", proInfo.getProvince()+proInfo.getCity()+proInfo.getCounty());
            map.put("proInfoAddress", proInfo.getAddress());
        }

        map.put("address", StringUtils.isNotEmpty(proSignIn.getAddress())?proSignIn.getAddress():"");

        if(proSignIn.getStatus()==1){
            map.put("status","正常");
        }else{
            map.put("status","迟到");
        }

        map.put("createrName", com.utils.StringUtils.isNotEmpty(proSignIn.getCreater().getName())?proSignIn.getCreater().getName():"");

        if (proSignIn.getCreateDate() != null) {
            tmp = DataUtil.DateToString(proSignIn.getCreateDate(), "yyyy-MM-dd HH:mm");
            map.put("createDate", tmp);
        }
        return map;
    }

    @Override
    public void saveEntity(ProSignIn signin, Locations locations) {
        Assert.notNull(signin, "signin is required !");
        Assert.notNull(locations, "locations is required !");

        locationsService.save(locations);
        signin.setLocationId(locations.getId());

        save(signin);
    }
}
