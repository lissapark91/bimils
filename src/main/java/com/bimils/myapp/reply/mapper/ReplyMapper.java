package com.bimils.myapp.reply.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bimils.myapp.reply.model.Reply;

@Mapper
public interface ReplyMapper {
	
	public int selectReplyCnt(int bo_seq_no) throws Exception;

	
	public List<Reply> selectReplyList(Map<String, Object> paramMap) throws Exception;
	

	public List<Reply> selectMyReplyList(Map<String, Object> paramMap) throws Exception;
	
	
	public int replyInsert(Reply reply) throws Exception;
	
	public int replyUpdate(Reply reply) throws Exception;
	
	public int replyDelete(int re_seq_no) throws Exception;
	

}
