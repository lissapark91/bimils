package com.bimils.myapp.common.util;

import java.util.Arrays;
import java.util.List;

public class ConstantUtil {
	
	public static final String UPLOAD_PATH = "/uploadFiles";
	
	public static final List<String> boTypeList = Arrays.asList(
			  "00" //나
			, "01" //연예인
			, "02" //정치
			, "03" //학교
			, "04" //회사
			, "05" //자연
			, "06" //호러
			, "07" //요리
			
		);
	public static final List<String> boNameList = Arrays.asList(
			"내비밀"
			, "연예인"
			, "정치"
			, "학교"
			, "회사"
			, "자연"
			, "호러"
			, "요리"
			
			);

	public static final List<String> adminList = Arrays.asList(
				"admin_lisa"
			);

}
