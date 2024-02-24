package com.ykj.board.util;

public class Paging {
	private int allCnt;//전체 글 갯수
	private int pageNum; //현제 페이지 번호
	private int pageListCnt; //페이지 글 개수 10
	private int pageNavCnt; //페이지 네비 개수 2
	private String pageListName; //페이지 이름 저장
	
	//초기값 setting
	public Paging(int allCnt, int pageNum, int pageListCnt, int pageNavCnt, String pageListName) {
		this.allCnt = allCnt;
		this.pageNum = pageNum;
		this.pageListCnt = pageListCnt;
		this.pageNavCnt = pageNavCnt;
		this.pageListName = pageListName;
	}

	//페이징용 html코드 생성
	public String makePaging() {
		String page = null;
		//1.전체 페이지 갯수 구하기(페이지당 10개)
		//전체 게시글/10 나눈값에+1(소수점버림) 나머지가 0이면 나눈값 그대로
		int totalPage = ((allCnt % pageListCnt) > 0) ? allCnt/pageListCnt+1 : allCnt/pageListCnt;
		
		//2.현제 페이지가 속해있는 그룹번호 구하기
		int groupNum = ((pageNum % pageNavCnt) > 0) ? pageNum/pageNavCnt+1 : pageNum/pageNavCnt;
		
		//3.현제 보이는 페이지 그룹의 시작번호 구하기
		int beginPageNum = (groupNum * pageNavCnt) - (pageNavCnt -1);
		
		//4.현제 보이는 페이지 그룹의 끝 번호 구하기
		int endPageNum = ((groupNum * pageNavCnt) >= totalPage) ? totalPage : (groupNum * pageNavCnt);
		
		//메모리에 임시공간을 생성하여 문자배열 데이터를 저장 
		//문자열을 동적으로 수정 삽입후 최종 문자열로 완성한다.
		StringBuffer sb = new StringBuffer();
		//이전 버튼처리<a class="pno" href="boardList?pageNum=2">&nbsp;이전&nbsp;</a>
		if(beginPageNum!=1) {
			sb.append("<a class='pno' href='"+pageListName+"?pageNum="+(beginPageNum-1)+"'>");
			sb.append("&nbsp;이전&nbsp;");
			sb.append("</a>");
		}
		//중간 페이지 번호처리
		for(int i= beginPageNum; i<= endPageNum; i++) {
			//현제페이지는 링크를 걸지않는다.
			if(pageNum != i) {//현제페이지가 아닐떄 링크
				sb.append("<a class='pno' href='"+pageListName+"?pageNum="+i+"'>");
				sb.append("&nbsp;"+i+"&nbsp;</a>");
			}else {//현제페이지는 highlight
				sb.append("<span class='pno' style='color:red'>");
				sb.append("&nbsp;"+i+"&nbsp;</span>");
			}	
		}
		//다음 버튼처리
		if(endPageNum!=totalPage) {
			sb.append("<a class='pno' href='"+pageListName+"?pageNum="+(endPageNum+1)+"'>");
			sb.append("&nbsp;다음&nbsp;");
			sb.append("</a>");
		}
		//최종 문자열로 보냄
		page = sb.toString();
		System.out.println(page);
		return page;
	}
	

}
