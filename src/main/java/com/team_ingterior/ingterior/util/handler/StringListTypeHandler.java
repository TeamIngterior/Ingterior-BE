package com.team_ingterior.ingterior.util.handler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        // List<String>을 String 배열로 변환하여 데이터베이스에 전달
        String[] stringArray = parameter.toArray(new String[0]);
        Array array = ps.getConnection().createArrayOf("text", stringArray);
        ps.setArray(i, array);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // ResultSet으로부터 Array를 받아서 List<String>으로 변환
        return convertArrayToList(rs.getArray(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertArrayToList(rs.getArray(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertArrayToList(cs.getArray(columnIndex));
    }

    private List<String> convertArrayToList(Array sqlArray) throws SQLException {
        if (sqlArray == null) {
            return null;
        }
        // Array를 String 배열로 받아온 후 List로 변환
        String[] stringArray = (String[]) sqlArray.getArray();
        return new ArrayList<>(Arrays.asList(stringArray));
    }
}
