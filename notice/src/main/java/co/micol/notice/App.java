package co.micol.notice;

import java.util.ArrayList;
import java.util.List;

import co.micol.notice.service.NoticeService;
import co.micol.notice.service.NoticeVO;
import co.micol.notice.serviceImpl.NoticeSeriveImpl;
import co.micol.student.dto.StudentVO;
import co.micol.student.service.StudentService;
import co.micol.student.serviceImpl.StudentServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
    	Menu menu = new Menu();
    	menu.rnu();
    	
    	
    	
//    	StudentService studentDao = new StudentServiceImpl();
//    	List<StudentVO> list = new ArrayList<StudentVO>();
//    	list = studentDao.selectListStudent();
//    	for(StudentVO vo : list) {
//    		vo.toString();
//    	}
    	/////
//    	NoticeService notice = new NoticeSeriveImpl();
//    	
//    	List<NoticeVO> list = new ArrayList<>();
//    	list = notice.noticeSelectList();
//
//    	for(NoticeVO vo : list) {
//    		vo.toString();
//    	}
//    	
//    	NoticeVO vo = new NoticeVO();
//    	vo.setId(1);
//    	vo = notice.noticeSelect(vo);
//    	System.out.println("=========글 내용==========");
//    	System.out.println(vo.getSubject());
//    	
//    	for(NoticeVO vo : list) {
//    		vo.toString();
//   	}
    	/////
    }
}
