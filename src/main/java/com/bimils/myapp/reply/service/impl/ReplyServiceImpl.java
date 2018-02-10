package com.bimils.myapp.reply.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimils.myapp.reply.mapper.ReplyMapper;
import com.bimils.myapp.reply.model.Reply;
import com.bimils.myapp.reply.service.ReplyService;


@Service("replyService")
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public int getReplyCnt(int bo_seq_no) throws Exception {
		return replyMapper.selectReplyCnt(bo_seq_no);
	}

	@Override
	public List<Reply> getReplyList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.selectReplyList(paramMap);
	}

	@Override
	public int replyInsert(Reply reply) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.replyInsert(reply);
	}

	@Override
	public int replyUpdate(Reply reply) throws Exception {
		return replyMapper.replyUpdate(reply);
	}

	@Override
	public int replyDelete(int re_seq_no) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.replyDelete(re_seq_no);
	}

	@Override
	public List<Reply> getMyReplyList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.selectMyReplyList(paramMap);
	}
	
}
