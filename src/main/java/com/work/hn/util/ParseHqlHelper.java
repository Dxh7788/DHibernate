package com.work.hn.util;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.hibernate.impl.SessionFactoryImpl;

import java.util.Collections;
import java.util.List;

/**
 * @author xh.d
 * @date 2017/12/4 15:17
 */
public class ParseHqlHelper {

    public static String resultMsg="";
    private static final String nullMsg = "传入的hql为null或空!";

    public static String getResultMsg() {
        return resultMsg;
    }
    /**
     * 无参解析
     * */
    public static String noneParamsParser(String hql){
        Session session = DBUtil.currentSession();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl)session.getSessionFactory();
        if (!StringUtils.isNotBlank(hql)){
            resultMsg = nullMsg;
            return null;
        }
        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql,hql, Collections.EMPTY_MAP,sessionFactory);
        queryTranslator.compile(Collections.EMPTY_MAP,false);
        String sql = queryTranslator.getSQLString();
        resultMsg = sql;
        DBUtil.closeSession();
        return sql;
    }
    /**
     * 带参解析
     * */
    public static String holderParamsParser(String hql,List params){
        String sql = noneParamsParser(hql);
        if (StringUtils.isBlank(sql)){
            resultMsg = nullMsg;
            return null;
        }
        //占位参数拼接
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                sql = sql.replaceFirst("\\?", "\\'"+params.get(i).toString()+"\\'");
            }
        }
        return getResultMsg();
    }
}
