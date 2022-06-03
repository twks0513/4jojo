package vending;

import java.util.Iterator;
import java.util.Scanner;

public class VendingMachineTest {
	Scanner scan = new Scanner(System.in);
	Beverage bvg = new Beverage();
	private String menu;
	private int price;
	
	public void print() { // 메인 화면 출력
		String password = "1234";
		
		while(true) {
			System.out.println("===== 자판기 프로그램 =====");
			System.out.println("원하시는 서비스를 눌러주세요.");
			System.out.println("1. 물품 구매");
			System.out.println("2. 자판기 관리");
			System.out.println("3. 프로그램 종료");
			System.out.print(">>>");
			
			int choice = scan.nextInt();
			scan.nextLine();
			System.out.println();
			
			if(choice==1) {
				customerPrint();
			}else if(choice==2) {

				System.out.println("비밀번호는 1234입니다.");
				System.out.print("비밀번호: ");
				
				String UserPW = scan.next();
				
				if(UserPW.equals(password)) {
					managerPrint();
				}else {
					System.out.println("비밀번호가 틀렸습니다.");
					System.out.println("이전 화면으로 돌아갑니다.");
					continue;
				}
			}else if(choice==3) {
				System.out.println("자판기 프로그램을 종료합니다.");
				break;
			}else {
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				System.out.println();
			}
		}
	}
	
	
	public void managerPrint() {
		while(true) {
			System.out.println("===== SET UP =====");
			System.out.println("1. 메뉴 추가");
			System.out.println("2. 메뉴 수정");
			System.out.println("3. 종료");
			System.out.print(">>> ");
			
			int choice = scan.nextInt();
			if(choice==1) {
				addMenu();
				continue;
			}else if(choice==2) {
				editMenu();
				continue;
			}else if(choice==3) {
				System.out.println("관리자 모드를 종료합니다.");
				System.out.println("이전 화면으로 돌아갑니다.");
				print();
				break;
			}
		}
	}
	
	//메뉴 추가
	public void addMenu(){
		System.out.println("메뉴를 추가합니다.");
		System.out.println("추가할 메뉴의 이름을 입력하세요.");
		System.out.print(">>>");
		menu = scan.next();
		
		System.out.println(menu + "의 가격을 입력하세요.");
		System.out.print(">>>");
		price = scan.nextInt();
		bvg.menumap.put(menu, price);
		
		System.out.println("메뉴가 추가되었습니다.");
	}
	
	
	//메뉴 수정
	public void editMenu() {
		while(true) {
			System.out.println("===== 메뉴 수정 =====");
			System.out.println(bvg.menumap.keySet());
			System.out.println("수정할 메뉴를 입력해주세요.");
			System.out.print(">>>");
			String menu = scan.next();
			int price = (int)bvg.menumap.get(menu);
			
			if(bvg.menumap.containsKey(menu)) {
				System.out.println("선택하신 제품은 " + menu + "|" + price + "원 입니다.");
				System.out.println("1. 가격 수정");
				System.out.println("2. 제품 삭제");
				System.out.println("3. 종료");
				System.out.print(">>>");
				int choice = scan.nextInt();
				
				if(choice==1) {
					System.out.println("수정할 가격을 입력해주세요.");
					System.out.println("현재 가격: " + price + "원");
					System.out.print("수정 가격: ");
					price = scan.nextInt();
					bvg.menumap.put(menu, price);
					System.out.println("수정이 완료 되었습니다.");
				}else if(choice==2) {
					System.out.println(menu + "를 삭제합니다.");
					bvg.menumap.remove(menu);
					System.out.println("삭제 되었습니다.");
				}else if(choice==3) {
					System.out.println("메뉴 수정을 종료합니다.");
					break;
				}
				
			}else {
				System.out.println(menu + "는 등록되지 않은 제품입니다.");
				System.out.println("이전 페이지로 이동합니다.");
				break;
			}
		}
	}
	
	
	//고객용 화면 출력
	public void customerPrint() {
		int money = 0;
		Iterator it = bvg.menumap.keySet().iterator();
		
		while(true) {
			System.out.println("===== 자판기 =====");
			//현재 금액 출력
			System.out.println("서비스를 선택하세요.");
			System.out.println("1. 금액 투입");
			System.out.println("2. 메뉴 보기");
			System.out.println("3. 잔돈 반환");
			System.out.println("4. 종료");
			System.out.print(">>>");
			int choice = scan.nextInt();
			
			if(choice==1) {
				money = insertMoney(money);
			}else if(choice==2) {
				if(bvg.menumap.isEmpty()) {
					System.out.println("등록된 메뉴가 없습니다.");
					System.out.println("관리자에게 문의하세요.");
				}else if(!bvg.menumap.isEmpty()) {
					System.out.println("===== MENU =====");
					while(it.hasNext()) {
						String itmenu = (String)it.next();
						System.out.println(itmenu + " " + bvg.menumap.get(itmenu) + "원");
					}
					System.out.println("================");
					System.out.println("메뉴를 선택해주세요.");
					printMoney(money);
					
					System.out.println("메뉴 입력");
					System.out.print(">>> ");
					String menu = scan.next();
					
					int price = (int)bvg.menumap.get(menu);
					if(bvg.menumap.containsKey(menu)==false) {
						System.out.println("없는 메뉴입니다.");
					}else if(bvg.menumap.containsKey(menu)) {
						System.out.println("선택하신 메뉴는 " + menu + "입니다.");
						System.out.println("가격은 " + price + "원입니다.");
						System.out.println("구매 하시겠습니까?");
						System.out.println("1. 구매");
						System.out.println("2. 취소");
						System.out.print(">>> ");
						
						choice = scan.nextInt();
						if(choice==1) {
							if(money>=price) {
								System.out.println("구매가 완료되었습니다. 감사합니다.");
								money-=price;
								continue;
							}else {
								System.out.println("잔액이 부족합니다.");
								continue;
							}
						}
					}
					
				}
			}else if(choice==3) {
				returnMoney(money);
			}else if(choice==4) {
				System.out.println("프로그램을 종료합니다. 감사합니다.");
				break;
			}
		}
	}
	
	
	//금액 투입
	
	public int insertMoney(int money) {
		
		System.out.println("=== 금액을 투입해주세요 ===");
		System.out.println("현재 금액: " + money + "원");
		System.out.println("금액 투입");
		System.out.print(">>>");
		money += scan.nextInt();
		return money;
	}
	
	public void printMoney(int money) {
		System.out.println("현재 금액은 " + money +"원입니다.");
	}
	
	public void returnMoney(int money) {
		System.out.println("잔돈이 반환됩니다.");
		System.out.println("반환될 금액: " + money + "원");
		money = 0;
		System.out.println("이전 화면으로 돌아갑니다.");
		System.out.println();
	}
}
