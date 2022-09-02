#include "ProductManager.h"

void ProductManager::Product_Input(string _id, string _name, int _price)
{
	productList.push_back(new Product(_id, _name, _price));
	P_Count += 1;
	Product_PK(_id);
	cout << "\n��ǰ ���� ��� �Ϸ�!" << endl << endl;
}
void ProductManager::Product_Display()
{
	cout << "+++++++++++++++++++++��ǰ ���� ����Ʈ+++++++++++++++++++++" << endl;
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
			cout << "\n��ǰ ���� ���� �Ϸ�!" << endl;
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
	cout << "\n��ǰ ���� ��ü ���� �Ϸ�!" << endl;
}
void ProductManager::Product_Change(string _id)
{
	for (int i = 0; i < P_Count; i++)
	{
		if (productList.at(i)->getPId().compare(_id) == 0)
		{
			cout << "\n������ ��ǰ ID : "; cin >> ProductManager::PM_ID;
			productList.at(i)->setPId(ProductManager::PM_ID);
			cout << "������ ��ǰ �̸� : "; cin >> ProductManager::PM_Name;
			productList.at(i)->setPName(ProductManager::PM_Name);
			cout << "������ ��ǰ ���� : "; cin >> ProductManager::PM_Price;
			productList.at(i)->setPPrice(ProductManager::PM_Price);
			cout << "��ǰ ���� ���� �Ϸ�!" << endl;
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