package com.ykj.board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ykj.board.dao.BoardDao;
import com.ykj.board.dao.MemberDao;
import com.ykj.board.dto.BoardDto;
import com.ykj.board.dto.BoardFileDto;
import com.ykj.board.dto.BoardReplyDto;
import com.ykj.board.util.Paging;

import lombok.extern.java.Log;
@Log
@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;

	@Autowired
	MemberDao memberDao;//게시글작성후 사용자 point증가 처리

	@Autowired
	HttpSession httpSession;//세션의 회원정보를 변경(point변경 후)

	public ModelAndView getBoardList(Integer pageNumber) {
		ModelAndView mv = new ModelAndView();
		//페이지 번호에 null값을 받으면 1페이지로 사용
		int pageNum = (pageNumber!=null) ? pageNumber : 1;
		//게시글 목록을 가져와서 model에 담음
		List<BoardDto> bList = boardDao.getList(pageNum);
		mv.addObject("bList", bList);
		//페이징 처리후 model에 추가.
		String paging = pageHandler(pageNum);
		mv.addObject("paging", paging);
		//세션에 페이지번호 저장
		httpSession.setAttribute("pageNum",pageNum);
		//전달할 페이지
		mv.setViewName("boardList");
		return mv;
	}
	//페이징 메서드
	private String pageHandler(int pageNum) {
		String pagingInnerHtml = null;
		//전체 글 갯수
		int allCnt = boardDao.pageAllCnt();
		//페이지당 글 갯수
		int pageListCnt = 10;
		//페이지 네비게이션 갯수
		int pageNavCnt =2;
		//페이지 url
		String pageUrl = "boardList";
		//페이징 객체 초기화.
		Paging paging = new Paging(allCnt,pageNum,pageListCnt,pageNavCnt,pageUrl);
		//페이징 결과 전달.
		pagingInnerHtml = paging.makePaging();

		return pagingInnerHtml;
	}
	@Transactional
	public String boardInsert(MultipartHttpServletRequest multipart, RedirectAttributes redirectAttributes) {
		String view = "null";
		String msg = "null";
		//form-data 가져오기
		String id = multipart.getParameter("bid");
		String title = multipart.getParameter("btitle");
		String contents = multipart.getParameter("bcontents");
		String check = multipart.getParameter("fileCheck");
		//textarea는 앞뒤 공백 제거를 위해 trim()메서드 사용.
		contents = contents.trim();
		//게시물 데이터를 dto에 담아서 dao로 db에 저장.
		BoardDto board = BoardDto.builder().bid(id).btitle(title)
				.bcontents(contents).build();

		try {
			//게시글 등록
			boardDao.boardInsert(board);
			//파일처리
			if(check.equals("1")) {//파일업로드시 해당 게시글번호로 저장
				fileUpload(multipart, board.getBnum());
			}
			//회원 포인트 증가
			view = "redirect:boardList";//게시판 첫페이지로.
			msg = "게시글 작성완료!";
		} catch (Exception e) {
			view = "redirect:writeForm";
			msg = "게시글 작성실패!";
		}
		log.info(msg);
		redirectAttributes.addFlashAttribute("msg", "게시글 등록!");
		return view;
	}
	private void fileUpload(MultipartHttpServletRequest multipart, int bnum) throws IOException {
		//파일이 저장될 경로 지정(실제경로 + 지정경로)
		String realPath = multipart.getServletContext().getRealPath("/");
		realPath += "resources/upload/";log.info(realPath);
		//지정경로를 자동으로 생성(디렉터리 생성, 파일 정보 확인, 경로 작업 등)
		File filedir = new File(realPath);
		if(filedir.isDirectory()!=true) {
			filedir.mkdir();//없으면 폴더생성.
		}
		//실제 파일 저장 및 파일 정보를 DB에 저장
		//Map으로 파라미터를 하나로 묶는다
		Map<String, String> fileMap = new HashMap<String, String>();
		//각 file태그(element)의 name을 가져옴(files의 파일들은 List로 처리)
		Iterator<String> fileNames = multipart.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			//업로드한 여러개의 파일들에 대한 처리
			List<MultipartFile> fList = multipart.getFiles(fileName);
			
			for(int i=0; i<fList.size(); i++) {
				MultipartFile multipartFile = fList.get(i);
				String oname = multipartFile.getOriginalFilename();
				//원본파일명을 시간값(밀리초)으로 변경.
				String sname = System.currentTimeMillis()+"."+oname.substring(oname.lastIndexOf(".")+1);
				//fileMap에 추가(게시글번호bnum, 원본파일이름oname, 바꾼이름sname)
				fileMap.put("bnum", String.valueOf(bnum));//int >> String
				fileMap.put("oriName", oname);
				fileMap.put("sysName", sname);
				//업로드된 파일을 지정된 폴더에 실제 파일로 저장 
				//File file = new File(realPath+sname);
				multipartFile.transferTo(new File(realPath+sname));
				//db에 파일정보 저장
				boardDao.fileInfoInsert(fileMap);
			}
		}
	}
	public ModelAndView getBoardContents(int bnum) {
		ModelAndView mv = new ModelAndView();
		String view = null;
		try {
			//1.조회수 업데이트 1up
			boardDao.boardViewCnt(bnum);
			//2.글내용
			BoardDto bcDto = boardDao.getBoardContents(bnum);
			//3.파일목록
			List<BoardFileDto> bfList = boardDao.getBoardFileList(bnum);
			//4.댓글목록
			List<BoardReplyDto> brList = boardDao.getBoardReplyList(bnum);
			
			//5.모델에 담기
			mv.addObject("boardContent", bcDto);
			mv.addObject("boardFile", bfList);
			mv.addObject("boardReply", brList);
			view = "boardContents";
		} catch (Exception e) {
			view = "boardList";
		}
		mv.setViewName(view);
		return mv;
	}

}



