#include "ShoppingManager.h"

void ShoppingManager::Shopping_Input(ClientManager& C_ref, ProductManager& P_ref,
	int _num, string _clpk, string _prpk, int _date, int _quantatiy)
{
	for (int i = 0; i < C_ref.getCCount(); i++)
	{
		if (C_ref.clientList.at(i)->getCWord().compare(_clpk) == 0)
		{
			string C_Word = C_ref.clientList.at(i)->getCWord();
			auto it1 = find_if(C_ref.clientList.begin(), C_ref.clientList.end(),
				[=](Client* c) {return *c == C_Word; });
			if (it1 != C_ref.clientList.end())
			{
				Client* c = *it1;
				cout << "ADD Client Prime Key : " << c->getCWord() << endl;
				for (int j = 0; j < P_ref.getPCount(); j++)
				{
					if (P_ref.productList.at(j)->getPId().compare(_prpk) == 0)
					{
						string P_Id = P_ref.productList.at(j)->getPId();
						auto it2 = find_if(P_ref.productList.begin(),
							P_ref.productList.end(),
							[=](Product* p) {return *p == P_Id; });
						if (it2 != P_ref.productList.end())
						{
							Product* p = *it2;
							cout << "ADD Prodcut Prime Key : " << p->getPId() << endl;
							int price = P_ref.productList.at(j)->getPPrice() * _quantatiy;
							shoppingList.push_back(new Shopping(_num,
								_clpk, _prpk, _date, _quantatiy, price));
							Snumber += 1;
							S_Count += 1;
							cout << "\n구매 정보 추가 완료" << endl;
						}
					}
				}
			}
		}
	}
}

void ShoppingManager::sort()
{
	for (int i = 0; i < S_Count; i++)
	{
		shoppingList.at(i)->setSNumber(i);
	}
}

void ShoppingManager::Shopping_Display()
{
	cout << "ShoppingCount : " << S_Count << endl;
	cout << "+++++++++++++++++++++구매 정보 리스트+++++++++++++++++++++" << endl;
	for_each(shoppingList.begin(), shoppingList.end(), [](Shopping* s)
		{
			cout << s->getSNumber() << " : " << s->getSPKClient() << ", "
				<< s->getSPKProduct() << ", " << s->getSDate() <<
				", " << s->getSQuan() << ", " << s->getSAllprice() << endl;
		});
	cout << "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" << endl << endl;
}

void ShoppingManager::Shopping_Remove(int _num)
{
	for (int i = 0; i < S_Count; i++)
	{
		if (shoppingList.at(i)->getSNumber() == _num)
		{
			shoppingList.erase(shoppingList.begin() + i);
			S_Count--;
			cout << "\n구매 정보 삭제 완료!" << endl;
		}
	}
}

void ShoppingManager::Shopping_Remove_All()
{
	while (!shoppingList.empty())
	{
		shoppingList.erase(shoppingList.begin(), shoppingList.end());
	}
	S_Count = 0;
	Snumber = 0;
	cout << "\n구매 정보 전체 삭제 완료!" << endl;
}


void ShoppingManager::Shopping_Change(int _num)
{
	for (int i = 0; i < S_Count; i++)
	{
		if (shoppingList.at(i)->getSNumber() == _num)
		{
			cout << "\n변경할 날짜 : "; cin >> date;
			shoppingList.at(i)->setSDate(date);
			cout << "변경할 수량 : "; cin >> quan;
			shoppingList.at(i)->setSQuan(quan);
			cout << "고객 정보 변경 완료!!" << endl;
		}
	}
}

void ShoppingManager::Shopping_Save()
{
	ofstream file;
	file.open("shoppinglist.txt");
	if (!file.fail())
	{
		for (const auto& s : shoppingList)
		{
			file << s->getSNumber() << ',';
			file << s->getSPKClient() << ',';
			file << s->getSPKProduct() << ',';
			file << s->getSDate() << ',';
			file << s->getSQuan() << ',';
			file << s->getSAllprice() << ',';
			file << S_Count << endl;
		}
		file << endl;
	}
	file.close();
	cout << "Shopping 파일 저장 완료" << endl;
}

void ShoppingManager::Shopping_Load()
{
	ifstream file;
	file.open("shoppinglist.txt");
	if (!file.fail())
	{
		while (!file.eof())
		{
			vector<string> row = parseCSV(file, ',');
			if (row.size())
			{
				int snum = atoi(row[0].c_str());
				int date = atoi(row[3].c_str());
				int quan = atoi(row[4].c_str());
				int price = atoi(row[5].c_str());
				int count = atoi(row[6].c_str());
				Shopping* s = new Shopping(snum, row[1],
					row[2], date, quan, price);
				shoppingList.push_back(s);
				S_Count = count;
				Snumber = count;
			}
		}
	}
	file.close();
	cout << "Shopping 파일 불러오기 완료" << endl << endl;
}

vector<string> ShoppingManager::parseCSV(istream& file, char delimiter)
{
	stringstream ss;
	vector<string> row;
	string t = " \n\r\t";

	while (!file.eof())
	{
		char c = file.get();
		if (c == delimiter || c == '\r' || c == '\n')
		{
			if (file.peek() == '\n') file.get();
			string s = ss.str();
			s.erase(0, s.find_first_not_of(t));
			s.erase(s.find_last_not_of(t) + 1);
			row.push_back(s);
			ss.str("");
			if (c != delimiter) break;
		}
		else
		{
			ss << c;
		}
	}
	return row;
}