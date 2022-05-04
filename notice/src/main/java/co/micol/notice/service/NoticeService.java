package co.micol.notice.service;

import java.util.List;

public interface NoticeService {
	List<NoticeVO> noticeSelectList();	// 전체 조회
	NoticeVO noticeSelect(NoticeVO vo);	// 하나의 글 조회
	int noticeINsert(NoticeVO vo);	// 추가
	int noticeUpdate(NoticeVO vo);	// 변경
	int noticeDelete(NoticeVO vo);	// 삭제
}
