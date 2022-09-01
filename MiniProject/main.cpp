#include "ClientManager.h"

int main()
{
	//main 내에 스위치 변수 절대 지우면 안됨
	int L_number, S_number; //Snumber;

	//client cin 정보
	string c_word, c_name, c_phone, c_email;

	ClientManager cm;
	cout << "안녕하십니까? Dentist Center 입니다." << endl;
	while (true)
	{
		cout << "아래에 해당하는 번호를 입력하여 원하시는 정보를 활용하십시오." << endl;
		cout << "1. 고객 정보 관리, 2. 상품 정보 관리, 3.쇼핑 정보 관리, 4.종료" << endl;
		cout << "번호를 입력하여 주세요 : "; cin >> L_number;

		switch (L_number)
		{
		case 1:
			cout << "\n1.고객 정보 관리" << endl;
			cout << "1.입력, 2.조회, 3.삭제, 4.모두삭제, 5.변경, 6.파일저장" << endl;
			cout << "해당하는 번호를 입력해주세요 : "; cin >> S_number;
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
			case 6:
				//잠시 보류
				break;
			default:
				cout << "입력하신 고객 정보 관리 번호가 없습니다." << endl;
				break;
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			exit(1);
			break;
		default:
			cout << "\n해당 번호가 없습니다!\n" << endl;
			break;
		}
	}

	return 0;
}