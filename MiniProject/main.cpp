#include "ClientManager.h"
#include "ProductManager.h"
#include "ShoppingManager.h"

int main()
{
	//main 내에 스위치 변수 절대 지우면 안됨
	int L_number = 0, S_number;

	//client cin 정보
	string c_word, c_name, c_phone, c_email;

	//product cin 정보
	string p_id, p_name; int p_price;

	//shopping cin 정보
	string pk_cl, pk_pr; int s_date, s_quan, s_num;

	ClientManager cm;
	ProductManager pm;
	ShoppingManager sm;

	//Client Product Shopping 텍스트 불러오기
	cm.Client_Load();
	pm.Product_Load();
	sm.Shopping_Load();
	cout << "안녕하십니까? Dentist Center 입니다." << endl;
	while (L_number != 4)
	{
		cout << "\n아래에 해당하는 번호를 입력하여 원하시는 정보를 활용하십시오." << endl;
		cout << "1. 고객 정보 관리, 2. 상품 정보 관리, 3.구매 정보 관리, 4.종료" << endl;
		cout << "번호를 입력하여 주세요 : "; cin >> L_number;

		if (!cin) //정수형 인자 콘솔 입력부분 경계 검사
		{
			cout << "\n1 ~ 4번 까지 정수형 숫자만 입력가능 합니다." << endl;
			cin.clear();
			cin.ignore(INT_MAX, '\n');
		}

		switch (L_number)
		{

		//고객 정보 스위치
		case 1:
		client:;
			cout << "\n1.고객 정보 관리" << endl;
			cout << "1.입력, 2.조회, 3.삭제, 4.모두삭제, 5.변경" << endl;
			cout << "해당하는 번호를 입력해주세요 : "; cin >> S_number;

			if (!cin) //정수형 인자 콘솔 입력부분 경계 검사
			{
				cout << "\n고객 정보 관리에 정수형 숫자를 입력하지 않았습니다." << endl;
				cin.clear();
				cin.ignore(INT_MAX, '\n');
				goto client;
			}

			switch (S_number)
			{
			case 1:
				cout << "\n고객 정보 입력" << endl;
				cout << "고객 등록 ID : "; cin >> c_word;
				cout << "고객 등록 이름 : "; cin >> c_name;
				cout << "고객 등록 전화번호 : "; cin >> c_phone;
				cout << "고객 등록 이메일 : "; cin >> c_email;
				cm.Client_Input(c_word, c_name, c_phone, c_email);
				break;
			case 2:
				cm.Client_Display();
				break;
			case 3:
				cm.Client_Display();
				cout << "\nID와 성함을 입력하면 정보가 삭제됩니다." << endl;
				cout << "삭제할 고객 ID 입력 : "; cin >> c_word;
				cout << "삭제할 고객 성함을 입력 : "; cin >> c_phone;
				cm.Client_Remove(c_word, c_phone); cout << endl;
				break;
			case 4:
				cm.Client_Remove_All();
				cm.Client_Display();
				break;
			case 5:
				cm.Client_Display();
				cout << "\nID와 성함을 입력하면 정보가 변경됩니다." << endl;
				cout << "변경될 고객 ID를 입력 : "; cin >> c_word;
				cout << "변경될 고객 성함을 입력 : "; cin >> c_name;
				cm.Client_Change(c_word, c_name); cout << endl;
				break;
			default:
				cout << "\n입력하신 고객 정보 관리 번호가 없습니다." << endl;
				break;
			}
			break;

		//상품 정보 스위치
		case 2:
		product:;
			cout << "\n2.상품 정보 관리" << endl;
			cout << "1.입력, 2.조회, 3.삭제, 4.모두삭제, 5.변경" << endl;
			cout << "해당하는 번호를 입력해주세요 : "; cin >> S_number;

			if (!cin)//정수형 인자 콘솔 입력부분 경계 검사
			{
				cout << "\n상품 정보 관리에 정수형 숫자를 입력하지 않았습니다." << endl;
				cin.clear();
				cin.ignore(INT_MAX, '\n');
				goto product;
			}

			switch (S_number)
			{
			case 1:
			
				cout << "\n상품 정보 입력" << endl;
				cout << "상품 등록 ID : "; cin >> p_id;
				cout << "상품 등록 이름 : "; cin >> p_name;
			product_1:;
				cout << "상품 등록 가격 : "; cin >> p_price;
				if (!cin)//정수형 인자 콘솔 입력부분 경계 검사
				{
					cout << "\n등록 가격에 정수형 숫자를 입력하지 않았습니다." << endl;
					cin.clear();
					cin.ignore(INT_MAX, '\n');
					goto product_1;
				}
				pm.Product_Input(p_id, p_name, p_price);
				break;
			case 2:
				pm.Product_Display();
				break;
			case 3:
				pm.Product_Display();
				cout << "\nID를 입력하면 정보가 삭제됩니다." << endl;
				cout << "삭제할 상품 ID 입력 : "; cin >> p_id;
				pm.Product_Remove(p_id); cout << endl;
				break;
			case 4:
				pm.Product_Remove_All();
				pm.Product_Display();
				break;
			case 5:
				pm.Product_Display();
				cout << "\nID를 입력하면 정보가 변경됩니다." << endl;
				cout << "변경될 상품 ID를 입력 : "; cin >> p_id;
				pm.Product_Change(p_id);
				break;
			default:
				cout << "입력하신 상품 정보 관리 번호가 없습니다." << endl;
				break;
			}
			break;


		//구매 정보 스위치
		case 3:
		shopping:;
			sm.sort();
			cout << "\n3.구매 정보 관리" << endl;
			cout << "1.주문, 2.조회, 3.삭제, 4.전체삭제, 5.변경" << endl;
			cout << "번호를 입력해주세요 : "; cin >> S_number;
			if (!cin)//정수형 인자 콘솔 입력부분 경계 검사
			{
				cout << "\n구매 정보 관리에 정수형 숫자를 입력하지 않았습니다." << endl;
				cin.clear();
				cin.ignore(INT_MAX, '\n');
				goto shopping;
			}
			switch (S_number)
			{
			case 1:
				cm.Client_Display();
				pm.Product_Display();
				cout << "위의 리스트를 보고 해당하는 상품과 고객정보를 입력하세요." << endl;
				cout << "고객의 등록 ID : "; cin >> pk_cl;
				cout << "상품의 등록 ID : "; cin >> pk_pr;
				shopping_1:;
				cout << "구매날짜(예 : 220830, 210578, YYMMDD) : "; cin >> s_date;
				if (!cin)
				{
					cout << "\n구매날짜에 정수형 숫자를 입력하지 않았습니다." << endl;
					cin.clear();
					cin.ignore(INT_MAX, '\n');
					goto shopping_1;
				}
				shopping_2:;
				cout << "구매수량 : "; cin >> s_quan; cout << endl;
				if (!cin)
				{
					cout << "\n구매수량에 정수형 숫자를 입력하지 않았습니다." << endl;
					cin.clear();
					cin.ignore(INT_MAX, '\n');
					goto shopping_2;
				}
				sm.Shopping_Input(cm, pm, sm.getSNumber(), pk_cl,
					pk_pr, s_date, s_quan); cout << endl;
				break;
			case 2:
				sm.Shopping_Display(); cout << endl;
				break;
			case 3:
				sm.Shopping_Display();
			shopping_3:;
				cout << "\n삭제할 구매 번호를 입력해주세요 : "; cin >> s_num;
				if (!cin)//정수형 인자 콘솔 입력부분 경계 검사
				{
					cout << "\n번호에 정수형 숫자를 입력하지 않았습니다." << endl;
					cin.clear();
					cin.ignore(INT_MAX, '\n');
					goto shopping_3;
				}
				sm.Shopping_Remove(s_num); 
				break;
			case 4:
				sm.Shopping_Remove_All();
				sm.Shopping_Display();
				break;
			case 5:
			shopping_4:;
				sm.Shopping_Display();
				cout << "\n변경할 구매 번호를 입력해주세요 : "; cin >> s_num;
				if (!cin)//정수형 인자 콘솔 입력부분 경계 검사
				{
					cout << "\n번호에 정수형 숫자를 입력하지 않았습니다." << endl;
					cin.clear();
					cin.ignore(INT_MAX, '\n');
					goto shopping_4;
				}
				sm.Shopping_Change(s_num, pm);
				break;
			default:
				cout << "입력하신 구매 정보 관리 번호가 없습니다." << endl;
				break;
			}
			break;

		//프로그램 종료 스위치	
		case 4:
			char push;
			cout << "\n저장후 프로그램을 종료하시겠습니까? (y/n) : "; cin >> push;
			if (push == 'y' || push == 'Y')
			{
				cm.Client_Save();
				pm.Product_Save();
				sm.Shopping_Save();
				cout << "프로그램 종료!!" << endl << endl;
				exit(1);
				break;
			}
			cout << "프로그램 종료!!" << endl << endl;
			exit(1);
			break;

		default:
			cout << "\n해당 번호가 없습니다!\n" << endl;
			break;
		}
	}

	return 0;
}