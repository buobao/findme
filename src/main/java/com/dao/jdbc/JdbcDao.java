package com.dao.jdbc;

/**
 * Created by dqf on 2015/8/17.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository("jdbcDao")
public class JdbcDao extends NamedParameterJdbcTemplate {
    public static final String DATABSE_TYPE_MYSQL = "mysql";
    public static final String DATABSE_TYPE_POSTGRE = "postgresql";
    public static final String DATABSE_TYPE_ORACLE = "oracle";
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {1} offset {2}";
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";

    @Autowired
    public JdbcDao(DataSource dataSource) {
        super(dataSource);
    }

    /** @deprecated */
    @Deprecated
    public List<Map<String, Object>> findForListMap(String sql, Map filter) {
        return super.queryForList(sql, filter);
    }

    /** @deprecated */
    @Deprecated
    public int executeForMap(String sql, Map filter) {
        return super.update(sql, filter);
    }
}
