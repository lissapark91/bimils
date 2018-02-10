package com.bimils.myapp.common.sales.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimils.myapp.common.sales.mapper.SalesMapper;
import com.bimils.myapp.common.sales.model.Sales;
import com.bimils.myapp.common.sales.service.SalesService;

@Service("salesService")
public class SalesServiceImpl implements SalesService{
	
	@Autowired
	SalesMapper salesMapper;
		
	@Override
	public int getSalesCnt(Map<String, Object> paramMap) throws Exception {
		
		return salesMapper.selectSalesCnt(paramMap);
	}

	@Override
	public List<Sales> getSalesList(Map<String, Object> paramMap) throws Exception {
		return salesMapper.selectSalesList(paramMap);
	}

	@Override
	public int salesInsert(Map<String, Object> paramMap) throws Exception {
		return salesMapper.salesInsert(paramMap);
	}

	@Override
	public int salesDelete(Map<String, Object> paramMap) throws Exception {
		return salesMapper.salesDelete(paramMap);
	}
	
}
