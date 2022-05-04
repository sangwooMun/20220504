package co.micol.notice;

import java.util.Scanner;

import co.micol.notice.management.Notice;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private Notice notice = new Notice();
	

	private void mainTitle() {
		System.out.println("=================");
		System.out.println("=1.  학생관리		=");
		System.out.println("=2.	 공지사항		=");
		System.out.println("=3.	 종료			=");
		System.out.println("=================");
	}

	private void studentTitle() {
		System.out.println("=================");
		System.out.println("=1. 전체학생목록	=");
		System.out.println("=2. 학생정보조회	=");
		System.out.println("=3. 학생정보등록	=");
		System.out.println("=4. 학생정보수정	=");
		System.out.println("=5. 학생 등록		=");
		System.out.println("=6. 메인메뉴가기	=");
		System.out.println("=================");
	}

	private void noticeTitle() {
		System.out.println("=================");
		System.out.println("=1. 공지사항목록	=");
		System.out.println("=2. 공지사항조회	=");
		System.out.println("=3. 공지사항등록	=");
		System.out.println("=4. 공지사항수정	=");
		System.out.println("=5. 공지사항삭제	=");
		System.out.println("=6. 메인메뉴가기	=");
		System.out.println("=================");
	}

	private void mainMenu() {
		boolean b = false;
		do {
			mainTitle();
			System.out.println("원하는 작업선택 : ");
			int job = sc.nextInt();
			switch (job) {
			case 1:
				studentMenu();
				break;
			case 2:
				noticeMenu();
				break;
			case 3:
				b = true;
				System.out.println("잘 가");
				break;
			}
		} while (!b);
	}

	private void noticeMenu() {
		boolean b = false;
		do {
			noticeTitle();
			System.out.println("원하는 작업을 선택하세요");
			int job = sc.nextInt();
			switch (job) {
			case 1:
				// 전체 목록
				notice.noticeList();
				break;
			case 2:
				// 조회
				notice.noticeSelect();
				break;
			case 3:
				// 등록
				notice.noticeInsert();
				break;
			case 4:
				// 수정
				notice.noticeUpdate();
				break;
			case 5:
				// 삭제
				break;
			case 6:
				b = true;
				System.out.println("잘 가");
				break;
			}
		} while (!b);

	}

	private void studentMenu() {
		boolean b = false;
		do {
			studentTitle();
			System.out.println("원하는 작업을 선택하세요");
			int job = sc.nextInt();
			switch (job) {
			case 1:
				// 전체 목록
				break;
			case 2:
				// 조회
				break;
			case 3:
				// 등록
				break;
			case 4:
				// 수정
				break;
			case 5:
				// 삭제
				break;
			case 6:
				b = true;
				System.out.println("잘 가");
				break;
			}
		} while (!b);
	}

	public void rnu() {
		mainMenu();
	}
}
