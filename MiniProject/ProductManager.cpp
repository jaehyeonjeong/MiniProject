#include "ProductManager.h"

void ProductManager::Product_Input(string _id, string _name, int _price)
{
	productList.push_back(new Product(_id, _name, _price));
	P_Count += 1;
	Product_PK(_id);
	cout << "\n상품 정보 등록 완료!" << endl << endl;
}
void ProductManager::Product_Display()
{
	cout << "ProductCount : " << P_Count << endl;
	cout << "+++++++++++++++++++++상품 정보 리스트+++++++++++++++++++++" << endl;
	for_each(productList.begin(), productList.end(),
		[](Product* p) {
			cout << p->getPId() << " : " << p->getPName() << ", " 
				<< p->getPPrice() << endl;
		});
	cout << "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" << endl << endl;
}
void ProductManager::Product_Remove(string _id)
{
	for (int i = 0; i < P_Count; i++)
	{
		if (productList.at(i)->getPId().compare(_id) == 0)
		{
			productList.erase(productList.begin() + i);
			P_Count -= 1;
			cout << "\n상품 정보 삭제 완료!" << endl;
		}
	}
}
void ProductManager::Product_Remove_All()
{
	while (!productList.empty())
	{
		productList.erase(productList.begin(), productList.end());
	}
	P_Count = 0;
	cout << "\n상품 정보 전체 삭제 완료!" << endl;
}
void ProductManager::Product_Change(string _id)
{
	for (int i = 0; i < P_Count; i++)
	{
		if (productList.at(i)->getPId().compare(_id) == 0)
		{
			cout << "\n변경할 상품 ID : "; cin >> ProductManager::PM_ID;
			productList.at(i)->setPId(ProductManager::PM_ID);
			cout << "변경할 상품 이름 : "; cin >> ProductManager::PM_Name;
			productList.at(i)->setPName(ProductManager::PM_Name);
			cout << "변경할 상품 가격 : "; cin >> ProductManager::PM_Price;
			productList.at(i)->setPPrice(ProductManager::PM_Price);
			cout << "상품 정보 변경 완료!" << endl;
		}
	}
}
void ProductManager::Product_PK(string _id)
{
	string P_ID = _id;
	auto it = find_if(productList.begin(), productList.end(),
		[=](Product* p) {return *p == P_ID; });
	if (it != productList.end())
	{
		Product* p = *it;
		cout << "Product Prime Key : " << p->getPId() << endl;
	}
}

void ProductManager::Product_Save()
{
	ofstream file;
	file.open("prductlist.txt");
	if (!file.fail())
	{
		for (const auto& p : productList)
		{
			file << p->getPId() << ',';
			file << p->getPName() << ',';
			file << p->getPPrice() << ',';
			file << P_Count << endl;
		}
		file << endl;
	}
	file.close();
	cout << "Product 파일 저장 완료" << endl;
}

void ProductManager::Product_Load()
{
	ifstream file;
	file.open("prductlist.txt");
	if (!file.fail())
	{
		while (!file.eof())
		{
			vector<string> row = parseCSV(file, ',');
			if (row.size())
			{
				int count = atoi(row[3].c_str());
				int price = atoi(row[2].c_str());
				Product* p = new Product(row[0], row[1], price);
				productList.push_back(p);
				P_Count = count;
			}
		}
	}
	file.close();
	cout << "Product 파일 불러오기 완료" << endl;
}

vector<string> ProductManager::parseCSV(istream& file, char delimiter)
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