#include "ClientManager.h"

int main()
{
	//main ���� ����ġ ���� ���� ����� �ȵ�
	int L_number, S_number; //Snumber;

	//client cin ����
	string c_word, c_name, c_phone, c_email;

	ClientManager cm;
	cout << "�ȳ��Ͻʴϱ�? Dentist Center �Դϴ�." << endl;
	while (true)
	{
		cout << "�Ʒ��� �ش��ϴ� ��ȣ�� �Է��Ͽ� ���Ͻô� ������ Ȱ���Ͻʽÿ�." << endl;
		cout << "1. ���� ���� ����, 2. ��ǰ ���� ����, 3.���� ���� ����, 4.����" << endl;
		cout << "��ȣ�� �Է��Ͽ� �ּ��� : "; cin >> L_number;

		switch (L_number)
		{
		case 1:
			cout << "\n1.���� ���� ����" << endl;
			cout << "1.�Է�, 2.��ȸ, 3.����, 4.��λ���, 5.����, 6.��������" << endl;
			cout << "�ش��ϴ� ��ȣ�� �Է����ּ��� : "; cin >> S_number;
			switch (S_number)
			{
			case 1:
				cout << "\n���� ���� �Է�" << endl;
				cout << "���� ��� ID : "; cin >> c_word;
				cout << "���� ��� �̸� : "; cin >> c_name;
				cout << "���� ��� ��ȭ��ȣ : "; cin >> c_phone;
				cout << "���� ��� �̸��� : "; cin >> c_email;
				cm.Client_Input(c_word, c_name, c_phone, c_email);
				break;
			case 2:
				cm.Client_Display();
				break;
			case 3:
				cm.Client_Display();
				cout << "\nID�� ������ �Է��ϸ� ������ �����˴ϴ�." << endl;
				cout << "������ ���� ID �Է� : "; cin >> c_word;
				cout << "������ ���� ������ �Է� : "; cin >> c_phone;
				cm.Client_Remove(c_word, c_phone); cout << endl;
				break;
			case 4:
				cm.Client_Remove_All();
				cm.Client_Display();
				break;
			case 5:
				cm.Client_Display();
				cout << "\nID�� ������ �Է��ϸ� ������ ����˴ϴ�." << endl;
				cout << "����� ���� ID�� �Է� : "; cin >> c_word;
				cout << "����� ���� ������ �Է� : "; cin >> c_name;
				cm.Client_Change(c_word, c_name); cout << endl;
				break;
			case 6:
				//��� ����
				break;
			default:
				cout << "�Է��Ͻ� ���� ���� ���� ��ȣ�� �����ϴ�." << endl;
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
			cout << "\n�ش� ��ȣ�� �����ϴ�!\n" << endl;
			break;
		}
	}

	return 0;
}