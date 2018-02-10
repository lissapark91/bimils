package com.bimils.myapp.common.sales.service;

import java.util.List;
import java.util.Map;

import com.bimils.myapp.common.sales.model.Sales;

public interface SalesService {
	
	public int getSalesCnt(Map<String, Object> paramMap) throws Exception;
	
	public List<Sales> getSalesList(Map<String, Object> paramMap) throws Exception;
	
	public int salesInsert(Map<String, Object> paramMap) throws Exception;
	
	public int salesDelete(Map<String, Object> paramMap) throws Exception;
}
