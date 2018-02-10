package com.bimils.myapp.reply.service;

import java.util.List;
import java.util.Map;

import com.bimils.myapp.reply.model.Reply;

public interface ReplyService {
	
	public int getReplyCnt(int bo_seq_no) throws Exception;
	
	public List<Reply> getReplyList(Map<String, Object> paramMap) throws Exception;

	public List<Reply> getMyReplyList(Map<String, Object> paramMap) throws Exception;
	
	public int replyInsert(Reply reply) throws Exception;
	
	public int replyUpdate(Reply reply) throws Exception;
	
	public int replyDelete(int re_seq_no) throws Exception;
	


}
