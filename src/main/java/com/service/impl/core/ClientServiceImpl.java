package com.service.impl.core;

import com.dao.core.ClientDao;
import com.dao.sys.BaseEntityDao;
import com.entity.core.Client;
import com.entity.sys.Dict;
import com.service.core.ClientService;
import com.service.impl.sys.BaseEntityServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amin on 2015/2/27.
 */
@Service
public class ClientServiceImpl extends BaseEntityServiceImpl<Client,String> implements ClientService {
    @Resource
    ClientDao clientDao;

    @Override
    public BaseEntityDao<Client, String> getBaseEntityDao() {
        return clientDao;
    }

    /**
     * app用
     * @param client
     * @return
     */
    @Override
    public Map<String, Object> getListItemMap(Client client) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(client.getId())?client.getId():"");
        map.put("no", StringUtils.isNotEmpty(client.getNo())?client.getNo():"");
        map.put("name", StringUtils.isNotEmpty(client.getName())?client.getName():"");
        map.put("subName", StringUtils.isNotEmpty(client.getSubName())?client.getSubName():"");

        if(client.getType()!=null){
            Dict category = client.getType();
            map.put("typeId", category.getId());
            map.put("typeName", category.getName());
        }else{
            map.put("typeId", "");
            map.put("typeName", "");
        }
        return map;
    }

    /**
     * app用
     * @param client
     * @return
     */
    @Override
    public Map<String, Object> getDetailMap(Client client) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", StringUtils.isNotEmpty(client.getId())?client.getId():"");
        map.put("name", StringUtils.isNotEmpty(client.getName())?client.getName():"");
        map.put("subName", StringUtils.isNotEmpty(client.getSubName())?client.getSubName():"");

        if(client.getType()!=null){
            Dict category = client.getType();
//            map.put("typeId", category.getId());
            map.put("typeName", category.getName());
        }else{
//            map.put("typeId", "");
            map.put("typeName", "");
        }

        map.put("invoiceTitle", StringUtils.isNotEmpty(client.getInvoiceTitle())?client.getInvoiceTitle():"");
        map.put("province", StringUtils.isNotEmpty(client.getProvince())?client.getProvince():"");
        map.put("city", StringUtils.isNotEmpty(client.getCity())?client.getCity():"");
        map.put("county", StringUtils.isNotEmpty(client.getCounty())?client.getCounty():"");

        map.put("remark", StringUtils.isNotEmpty(client.getRemark())?client.getRemark():"");
        map.put("situtation", StringUtils.isNotEmpty(client.getSitutation())?client.getSitutation():"");

        return map;
    }
}
