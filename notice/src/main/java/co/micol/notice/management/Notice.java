package co.micol.notice.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.notice.service.NoticeService;
import co.micol.notice.service.NoticeVO;
import co.micol.notice.serviceImpl.NoticeSeriveImpl;

public class Notice {
	private NoticeService dao = new NoticeSeriveImpl();
	private Scanner scanner = new Scanner(System.in);
	
	public void noticeList() {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		list = dao.noticeSelectList();
		System.out.println("======게시글목록======");
		
		for(NoticeVO vo : list) {
			vo.toString();
		}
	}
	
	public void noticeSelect() {
		NoticeVO vo = new NoticeVO();
		System.out.println("== 읽을 공지사항 번호 입력 ==");
		int id = scanner.nextInt();
		vo.setId(id);
		vo = dao.noticeSelect(vo);
		vo.toString();
		System.out.println("글내용 : " + vo.getSubject());
		scanner.nextLine();
	}
	
	public void noticeInsert() {
		NoticeVO vo = new NoticeVO();
		System.out.println("작성자를 입력");
		vo.setWriter(scanner.nextLine());
		System.out.println("제목 입력");
		vo.setTitle(scanner.nextLine());
		System.out.println("내용 입력");
		vo.setSubject(scanner.nextLine());
		
		int n = dao.noticeINsert(vo);
		if(n != 0 ) {
			System.out.println("정상적으로 글등록 완료");
		}else {
			System.out.println("정상적으로 글등록 실패");
		}
	}
	
	public void noticeUpdate() {
		NoticeVO vo = new NoticeVO();
		System.out.println("수정할 글의 번호를 입력");
		int id = scanner.nextInt();
		scanner.nextLine();
		vo.setId(id);
		System.out.println("수정할 글 내용 : ");
		vo.setSubject(scanner.nextLine());
		
		int u = dao.noticeUpdate(vo);
		if(u != 0) {
			System.out.println("정상적으로 글수정 완료");
		}else {
			System.out.println("정상적으로 글수정 실패");
		}
	}
	
	public void noticeDelete() {
		NoticeVO vo = new NoticeVO();
		System.out.println("삭제할 글의 번호를 입력");
		int id = scanner.nextInt();
		scanner.nextLine();
		
	}
}
