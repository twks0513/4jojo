package vending;

import java.util.Iterator;
import java.util.Scanner;

public class VendingMachineTest {
	Scanner scan = new Scanner(System.in);
	Beverage bvg = new Beverage();
	private String menu;
	private int price;
	
	public void print() { // ���� ȭ�� ���
		String password = "1234";
		
		while(true) {
			System.out.println("===== ���Ǳ� ���α׷� =====");
			System.out.println("���Ͻô� ���񽺸� �����ּ���.");
			System.out.println("1. ��ǰ ����");
			System.out.println("2. ���Ǳ� ����");
			System.out.println("3. ���α׷� ����");
			System.out.print(">>>");
			
			int choice = scan.nextInt();
			scan.nextLine();
			System.out.println();
			
			if(choice==1) {
				customerPrint();
			}else if(choice==2) {

				System.out.println("��й�ȣ�� 1234�Դϴ�.");
				System.out.print("��й�ȣ: ");
				
				String UserPW = scan.next();
				
				if(UserPW.equals(password)) {
					managerPrint();
				}else {
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					System.out.println("���� ȭ������ ���ư��ϴ�.");
					continue;
				}
			}else if(choice==3) {
				System.out.println("���Ǳ� ���α׷��� �����մϴ�.");
				break;
			}else {
				System.out.println("�߸��� �Է��Դϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
				System.out.println();
			}
		}
	}
	
	
	public void managerPrint() {
		while(true) {
			System.out.println("===== SET UP =====");
			System.out.println("1. �޴� �߰�");
			System.out.println("2. �޴� ����");
			System.out.println("3. ����");
			System.out.print(">>> ");
			
			int choice = scan.nextInt();
			if(choice==1) {
				addMenu();
				continue;
			}else if(choice==2) {
				editMenu();
				continue;
			}else if(choice==3) {
				System.out.println("������ ��带 �����մϴ�.");
				System.out.println("���� ȭ������ ���ư��ϴ�.");
				print();
				break;
			}
		}
	}
	
	//�޴� �߰�
	public void addMenu(){
		System.out.println("�޴��� �߰��մϴ�.");
		System.out.println("�߰��� �޴��� �̸��� �Է��ϼ���.");
		System.out.print(">>>");
		menu = scan.next();
		
		System.out.println(menu + "�� ������ �Է��ϼ���.");
		System.out.print(">>>");
		price = scan.nextInt();
		bvg.menumap.put(menu, price);
		
		System.out.println("�޴��� �߰��Ǿ����ϴ�.");
	}
	
	
	//�޴� ����
	public void editMenu() {
		while(true) {
			System.out.println("===== �޴� ���� =====");
			System.out.println(bvg.menumap.keySet());
			System.out.println("������ �޴��� �Է����ּ���.");
			System.out.print(">>>");
			String menu = scan.next();
			int price = (int)bvg.menumap.get(menu);
			
			if(bvg.menumap.containsKey(menu)) {
				System.out.println("�����Ͻ� ��ǰ�� " + menu + "|" + price + "�� �Դϴ�.");
				System.out.println("1. ���� ����");
				System.out.println("2. ��ǰ ����");
				System.out.println("3. ����");
				System.out.print(">>>");
				int choice = scan.nextInt();
				
				if(choice==1) {
					System.out.println("������ ������ �Է����ּ���.");
					System.out.println("���� ����: " + price + "��");
					System.out.print("���� ����: ");
					price = scan.nextInt();
					bvg.menumap.put(menu, price);
					System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
				}else if(choice==2) {
					System.out.println(menu + "�� �����մϴ�.");
					bvg.menumap.remove(menu);
					System.out.println("���� �Ǿ����ϴ�.");
				}else if(choice==3) {
					System.out.println("�޴� ������ �����մϴ�.");
					break;
				}
				
			}else {
				System.out.println(menu + "�� ��ϵ��� ���� ��ǰ�Դϴ�.");
				System.out.println("���� �������� �̵��մϴ�.");
				break;
			}
		}
	}
	
	
	//���� ȭ�� ���
	public void customerPrint() {
		int money = 0;
		Iterator it = bvg.menumap.keySet().iterator();
		
		while(true) {
			System.out.println("===== ���Ǳ� =====");
			//���� �ݾ� ���
			System.out.println("���񽺸� �����ϼ���.");
			System.out.println("1. �ݾ� ����");
			System.out.println("2. �޴� ����");
			System.out.println("3. �ܵ� ��ȯ");
			System.out.println("4. ����");
			System.out.print(">>>");
			int choice = scan.nextInt();
			
			if(choice==1) {
				money = insertMoney(money);
			}else if(choice==2) {
				if(bvg.menumap.isEmpty()) {
					System.out.println("��ϵ� �޴��� �����ϴ�.");
					System.out.println("�����ڿ��� �����ϼ���.");
				}else if(!bvg.menumap.isEmpty()) {
					System.out.println("===== MENU =====");
					while(it.hasNext()) {
						String itmenu = (String)it.next();
						System.out.println(itmenu + " " + bvg.menumap.get(itmenu) + "��");
					}
					System.out.println("================");
					System.out.println("�޴��� �������ּ���.");
					printMoney(money);
					
					System.out.println("�޴� �Է�");
					System.out.print(">>> ");
					String menu = scan.next();
					
					int price = (int)bvg.menumap.get(menu);
					if(bvg.menumap.containsKey(menu)==false) {
						System.out.println("���� �޴��Դϴ�.");
					}else if(bvg.menumap.containsKey(menu)) {
						System.out.println("�����Ͻ� �޴��� " + menu + "�Դϴ�.");
						System.out.println("������ " + price + "���Դϴ�.");
						System.out.println("���� �Ͻðڽ��ϱ�?");
						System.out.println("1. ����");
						System.out.println("2. ���");
						System.out.print(">>> ");
						
						choice = scan.nextInt();
						if(choice==1) {
							if(money>=price) {
								System.out.println("���Ű� �Ϸ�Ǿ����ϴ�. �����մϴ�.");
								money-=price;
								continue;
							}else {
								System.out.println("�ܾ��� �����մϴ�.");
								continue;
							}
						}
					}
					
				}
			}else if(choice==3) {
				returnMoney(money);
			}else if(choice==4) {
				System.out.println("���α׷��� �����մϴ�. �����մϴ�.");
				break;
			}
		}
	}
	
	
	//�ݾ� ����
	
	public int insertMoney(int money) {
		
		System.out.println("=== �ݾ��� �������ּ��� ===");
		System.out.println("���� �ݾ�: " + money + "��");
		System.out.println("�ݾ� ����");
		System.out.print(">>>");
		money += scan.nextInt();
		return money;
	}
	
	public void printMoney(int money) {
		System.out.println("���� �ݾ��� " + money +"���Դϴ�.");
	}
	
	public void returnMoney(int money) {
		System.out.println("�ܵ��� ��ȯ�˴ϴ�.");
		System.out.println("��ȯ�� �ݾ�: " + money + "��");
		money = 0;
		System.out.println("���� ȭ������ ���ư��ϴ�.");
		System.out.println();
	}
}
