package com.bimils.myapp.common.sales.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.common.sales.model.Sales;

@Mapper
public interface SalesMapper {
	
	public int selectSalesCnt(Map<String, Object> paramMap) throws Exception;
	
	public List<Sales> selectSalesList(Map<String, Object> paramMap) throws Exception;
	
	public int salesInsert(Map<String, Object> paramMap) throws Exception;
	
	public int salesDelete(Map<String, Object> paramMap) throws Exception;
	
	
}
