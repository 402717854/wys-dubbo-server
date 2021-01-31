package com.wys.dubbo.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description: mysql json类型处理
 * @Author: 86133
 * @Date: 2021/01/25
 */
/*转化后的数据类型*/
@MappedTypes(JSONObject.class)
/*数据库中的数据类型*/
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MySqlJsonHandler extends BaseTypeHandler<JSONObject> {

    /**
     * 通过 PreparedStatement 对象进行设置 SQL 参数的时候使用的具体方法，其中 i 是参数在 SQL 的下标，parameter 是参数，jdbcType 是数据库类型
     * @param preparedStatement
     * @param i
     * @param jsonObject
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,String.valueOf(jsonObject.toJSONString()));
   }

    /**
     * 根据列名获取值
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String sqlJson = resultSet.getString(s);
        if (null != sqlJson) {
            return JSONObject.parseObject(sqlJson);
       }
        return null;
   }

    /**
     * 根据列索引位置获取值
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String sqlJson = resultSet.getString(i);
        if (null != sqlJson) {
            return JSONObject.parseObject(sqlJson);
       }
        return null;
   }

    /**
     * 存储过程
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String sqlJson = callableStatement.getNString(i);
        if (null != sqlJson) {
            return JSONObject.parseObject(sqlJson);
       }
        return null;
   }
}