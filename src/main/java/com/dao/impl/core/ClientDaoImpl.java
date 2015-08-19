package com.dao.impl.core;

import com.dao.core.ClientDao;
import com.dao.impl.sys.BaseEntityDaoImpl;
import com.entity.core.Client;
import org.springframework.stereotype.Repository;

/**
 * Created by amin on 2015/2/27.
 */
@Repository
public class ClientDaoImpl extends BaseEntityDaoImpl<Client,String> implements ClientDao {
}
