package com.team_ingterior.ingterior.util.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team_ingterior.ingterior.DTO.geometry.FeatureVO;

import lombok.RequiredArgsConstructor;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class FeatureVOTypeHandler extends BaseTypeHandler<FeatureVO> {

    private final ObjectMapper objectMapper;

     @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FeatureVO parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting FeatureVO to String", e);
        }
    }

    @Override
    public FeatureVO getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return parseJson(json);
    }

    @Override
    public FeatureVO getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return parseJson(json);
    }

    @Override
    public FeatureVO getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return parseJson(json);
    }

    private FeatureVO parseJson(String json) throws SQLException {
        try {
            return json == null ? null : objectMapper.readValue(json, FeatureVO.class);
        } catch (IOException e) {
            throw new SQLException("Error parsing JSON to FeatureVO", e);
        }
    }
}