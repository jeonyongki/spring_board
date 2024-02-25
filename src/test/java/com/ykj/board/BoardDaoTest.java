package com.ykj.board;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ykj.board.dao.BoardDao;
import com.ykj.board.dto.BoardDto;
import com.ykj.board.dto.BoardFileDto;
import com.ykj.board.dto.BoardReplyDto;

import lombok.extern.java.Log;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardDaoTest {
	@Autowired
	BoardDao boardDao;
	
	@Test
	public void getListTest() {
		int pageNo = 1;
		List<BoardDto> board = boardDao.getList(pageNo);
		if(board.size()!=0) {
		for(BoardDto bdto : board) {
			log.info(bdto.toString());
		}
		int num = (pageNo%2 > 0) ? 1 : 0;
		log.info(num+" ");
		}
	}
	@Test
	public void pageAllCntTest() {
		int cnt = boardDao.pageAllCnt();
		log.info("COUNT : "+cnt);
	}
	@Test
	public void boardInsertTest() {
		BoardDto board = BoardDto.builder().btitle("테스트글")
				.bcontents("첫눈처럼 너에게 가겠다 ")
				.bid("tester1").build();
		boardDao.boardInsert(board);
		log.info(board.toString());
	}
	@Test
	public void fileInsertTest() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bnum", "43");
		map.put("oriName", "tester43.txt");
		map.put("sysName", "1234567.txt");
		log.info("map : " + map);
		boardDao.fileInfoInsert(map);
	}
	@Test
	public void getBoardContents() {
		BoardDto board = boardDao.getBoardContents(43);
		log.info(board.toString());
	}
	@Test
	public void getBoardFileListTest() {
		List<BoardFileDto> bfList = boardDao.getBoardFileList(45);
		for(int i=0; i<bfList.size(); i++) {
			BoardFileDto bf = bfList.get(i);
			log.info(bf.toString());
//			log.info(bf.getBf_oriname());
//			log.info(bf.getBf_sysname());
		}
	}
	@Test
	public void getBoardReplyListTest() {
		List<BoardReplyDto> brList = boardDao.getBoardReplyList(5);
		for(int i=0; i<brList.size(); i++) {
			BoardReplyDto br = brList.get(i);
			log.info(br.toString());
		}
	}
	@Test
	public void boardViewCntTest() {
		int bnum = 44;
		try {
			boardDao.boardViewCnt(bnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
