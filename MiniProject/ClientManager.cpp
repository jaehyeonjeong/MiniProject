#include "ClientManager.h"

//main문에서 고객관리 정보 데이터가 들어가는 함수
void ClientManager::Client_Input(string _word,
	string _name, string _phone, string _email)
{
	clientList.push_back(new Client(_word, _name, _phone, _email));
	ClientManager::C_Count += 1;
	Client_PK(_word);
	cout << "\n고객 정보 입력 완료" << endl;
}

//고객 정보 리스트 공개 함수
void ClientManager::Client_Display()
{
	cout << "ClientCount : " << C_Count << endl;
	cout << "+++++++++++++++++++++++고객 정보 리스트+++++++++++++++++++++++++++" << endl;
	cout << "------------------------------------------------------------------" << endl;
	cout << setw(11) << "고객ID" << " | " << setw(10) << "고객 성함" << " | " << setw(15) << "고객 전화번호" << " | " << setw(20) << "고객 이메일" << endl;
	cout << "------------------------------------------------------------------" << endl;
	for_each(clientList.begin(), clientList.end(), [](Client* c)
		{
			cout << setw(11) << c->getCWord() << " | " << setw(10) << c->getCName() << " | "
				<< setw(15) << c->getCPhone() << " | " << setw(20) << c->getCEmail() << endl;
			cout << "------------------------------------------------------------------" << endl;
		});
	cout << "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" << endl << endl;
}

//고객 정보 제거 함수
void ClientManager::Client_Remove(string _word, string _name)
{
	for (int i = 0; i < C_Count; i++)
	{
		if ((clientList.at(i)->getCWord().compare(_word) == 0) &&
			(clientList.at(i)->getCName().compare(_name) == 0))
		{
			clientList.erase(clientList.begin() + i);
			ClientManager::C_Count -= 1;
			cout << "\n고객 정보 삭제 완료" << endl;
		}
	}
}

//고객 정보 전체 제거 함수
void ClientManager::Client_Remove_All()
{
	while (!clientList.empty())
	{
		clientList.erase(clientList.begin(), clientList.end());
	}
	ClientManager::C_Count = 0;
	cout << "\n고객 정보 전체 삭제 완료" << endl;
}

//고객 정보 변경 함수
void ClientManager::Client_Change(string _word, string _name)
{
	for (int i = 0; i < ClientManager::C_Count; i++)
	{
		if ((clientList.at(i)->getCWord().compare(_word) == 0) &&
			(clientList.at(i)->getCName().compare(_name) == 0))
		{
			cout << "\n변경할 고객 ID : "; cin >> ClientManager::CM_Word;
			clientList.at(i)->setCWord(ClientManager::CM_Word);
			cout << "변경할 고객 성함 : "; cin >> ClientManager::CM_Name;
			clientList.at(i)->setCName(ClientManager::CM_Name);
			cout << "변경할 고객 전화번호 : "; cin >> ClientManager::CM_Phone;
			clientList.at(i)->setCPhone(ClientManager::CM_Phone);
			cout << "변경할 고객 이메일 : "; cin >> ClientManager::CM_Email;
			clientList.at(i)->setCEmail(ClientManager::CM_Email);
			cout << "고객 정보 변경 완료!!" << endl;
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

//고객 정보 파일로 저장 함수
void ClientManager::Client_Save()
{
	ofstream file;
	file.open("clientlist.txt");
	if (!file.fail()) {
		for (const auto& c : clientList) {
			file << c->getCWord() << ',';
			file << c->getCName() << ',';
			file << c->getCPhone() << ',';
			file << c->getCEmail() << ',';
			file << C_Count << endl;
		}
		file << endl;
	}
	file.close();
	cout << "Client 파일 저장 완료" << endl;
}

//고객 정보 파일로 불러오기 함수
void ClientManager::Client_Load()
{
	//vector<Client*> vecList;
	ifstream file; //input
	file.open("clientlist.txt");
	if (!file.fail()) {
		while (!file.eof()) {
			vector<string> row = parseCSV(file, ',');
			if (row.size()) {
				int count = atoi(row[4].c_str());
				Client* c = new Client(row[0], row[1],
					row[2], row[3]);
				clientList.push_back(c);
				C_Count = count;
			}
		}
	}
	file.close();
	cout << "Client 파일 불러오기 완료" << endl;
}

vector<string> ClientManager::parseCSV(istream& file,
	char delimiter)
{
	stringstream ss;
	vector<string> row;
	string t = " \n\r\t";

	while (!file.eof()) {
		char c = file.get();
		if (c == delimiter || c == '\r' || c == '\n') {
			if (file.peek() == '\n') file.get();
			string s = ss.str();
			s.erase(0, s.find_first_not_of(t));
			s.erase(s.find_last_not_of(t) + 1);
			row.push_back(s);
			ss.str("");
			if (c != delimiter) break;
		}
		else {
			ss << c;
		}
	}
	return row;
}