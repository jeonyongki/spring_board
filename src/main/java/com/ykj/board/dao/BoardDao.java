package com.ykj.board.dao;

import java.util.List;
import java.util.Map;

import com.ykj.board.dto.BoardDto;
import com.ykj.board.dto.BoardFileDto;
import com.ykj.board.dto.BoardReplyDto;

public interface BoardDao {
//게시글 목록 가져오기
 public List<BoardDto> getList(int pageNum);
 //게시글 목록 갯수
 public int pageAllCnt();
 //게시글 저장
 public void boardInsert(BoardDto board);
 //파일정보 저장
 public void fileInfoInsert(Map<String, String> fileMap);
 //게시글 가져오기
 public BoardDto getBoardContents(int bnum);
 //게시글 파일목록 가져오기
 public List<BoardFileDto> getBoardFileList(int bnum);
 //게시글 댓글 목록 가져오기
 public List<BoardReplyDto> getBoardReplyList(int bnum);
 //게시글 조회수 증가
 public void boardViewCnt(int bnum);
}
