#include "ClientManager.h"

void ClientManager::Client_Input(string _word,
	string _name, string _phone, string _email)
{
	clientList.push_back(new Client(_word, _name, _phone, _email));
	ClientManager::C_Count += 1;
	Client_PK(_word);
	cout << "\n���� ���� �Է� �Ϸ�" << endl;
}

void ClientManager::Client_Display()
{
	cout << "+++++++++++++++++++++���� ���� ����Ʈ+++++++++++++++++++++" << endl;
	for_each(clientList.begin(), clientList.end(), [](Client* c)
		{
			cout << c->getCWord() << " : " << c->getCName() << ", "
				<< c->getCPhone() << ", " << c->getCEmail() << endl;
		});
	cout << "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" << endl << endl;
}

void ClientManager::Client_Remove(string _word, string _name)
{
	for (int i = 0; i < ClientManager::C_Count; i++)
	{
		if ((clientList.at(i)->getCWord().compare(_word) == 0) &&
			(clientList.at(i)->getCName().compare(_name) == 0))
		{
			clientList.erase(clientList.begin() + i);
			ClientManager::C_Count -= 1;
			cout << "\n���� ���� ���� �Ϸ�" << endl;
		}
	}
}

void ClientManager::Client_Remove_All()
{
	while (!clientList.empty())
	{
		clientList.erase(clientList.begin(), clientList.end());
	}
	ClientManager::C_Count = 0;
	cout << "\n ���� ���� ��ü ���� �Ϸ�" << endl;
}

void ClientManager::Client_Change(string _word, string _name)
{
	for (int i = 0; i < ClientManager::C_Count; i++)
	{
		if ((clientList.at(i)->getCWord().compare(_word) == 0) &&
			(clientList.at(i)->getCName().compare(_name) == 0))
		{
			cout << "������ ���� ID : "; cin >> ClientManager::CM_Word;
			clientList.at(i)->setCWord(ClientManager::CM_Word);
			cout << "������ ���� ���� : "; cin >> ClientManager::CM_Name;
			clientList.at(i)->setCName(ClientManager::CM_Name);
			cout << "������ ���� ��ȭ��ȣ : "; cin >> ClientManager::CM_Phone;
			clientList.at(i)->setCPhone(ClientManager::CM_Phone);
			cout << "������ ���� �̸��� : "; cin >> ClientManager::CM_Email;
			clientList.at(i)->setCEmail(ClientManager::CM_Email);
		}
	}
}

void ClientManager::Client_PK(string _word)
{
	string C_WORD = _word;
	auto it = find_if(clientList.begin(), clientList.end(),
		[=](Client* p) {return *p == C_WORD; });
	if (it != clientList.end())
	{
		Client* c = *it;
		cout << "Client Prime Key : " << c->getCWord() << endl;
	}
}