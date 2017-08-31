package com.rstang.core.page;

import java.io.Serializable;
import java.util.Properties;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import com.rstang.core.config.GlobalConfig;
import com.rstang.core.persistence.dialect.DB2Dialect;
import com.rstang.core.persistence.dialect.Dialect;
import com.rstang.core.persistence.dialect.MySQLDialect;
import com.rstang.core.persistence.dialect.OracleDialect;
import com.rstang.util.reflect.Reflections;

/**
 * Mybatis分页拦截器基类
 * 
 * @author YEYAOXIONG
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {
	
	private static final long serialVersionUID = 1L;

    protected static final String PAGE = "page";
    
    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";

    protected Log log = LogFactory.getLog(this.getClass());

    protected Dialect DIALECT;

//    /**
//     * 拦截的ID，在mapper中的id，可以匹配正则
//     */
//    protected String _SQL_PATTERN = "";

    /**
     * 对参数进行转换和检查
     * @param parameterObject 参数对象
     * @param pageView            分页对象
     * @return 分页对象
     * @throws NoSuchFieldException 无法找到参数
     */
    @SuppressWarnings("unchecked")
	protected static PageView<Object> convertParameter(Object parameterObject, PageView<Object> pageView) {
    	try{
            if (parameterObject instanceof PageView) {
                return (PageView<Object>) parameterObject;
            } else {
                return (PageView<Object>)Reflections.getFieldValue(parameterObject, PAGE);
            }
    	}catch (Exception e) {
			return null;
		}
    }

    /**
     * 设置属性，支持自定义方言类和制定数据库的方式
     * <code>dialectClass</code>,自定义方言类。可以不配置这项
     * <ode>dbms</ode> 数据库类型，插件支持的数据库
     * <code>sqlPattern</code> 需要拦截的SQL ID
     * @param p 属性
     */
    protected void initProperties(Properties p) {
    	Dialect dialect = null;
        String dbType = GlobalConfig.getConfig("jdbc.type");
        if ("db2".equals(dbType)){
        	dialect = new DB2Dialect();
        }else if("mysql".equals(dbType)){
        	dialect = new MySQLDialect();
        }else if("oracle".equals(dbType)){
        	dialect = new OracleDialect();
        }
        if (dialect == null) {
            throw new RuntimeException("mybatis dialect error.");
        }
        DIALECT = dialect;
    }
}
