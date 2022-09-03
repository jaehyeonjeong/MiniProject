#ifndef _SHOPPING_H_
#define _SHOPPING_H_

#include <iostream>
using namespace std;

class Shopping
{
public:
	Shopping(int _snum = 0, string _cpk = "", string _ppk = "", int _date = 0, int _quan = 0, int _allprice = 0)
		:S_Number(_snum), PK_Client(_cpk), PK_Product(_ppk), S_Date(_date), S_Quan(_quan), S_Allprice(_allprice)
	{

	}
	int getSNumber();
	void setSNumber(int _number);
	string getSPKClient();
	void setSPKClient(string& _client);
	string getSPKProduct();
	void setSPKProcut(string& _product);
	int getSDate();
	void setSDate(int _date);
	int getSQuan();
	void setSQuan(int _quan);
	int getSAllprice();
	void setSAllprice(int _allprice) { S_Allprice = _allprice; }
private:
	int S_Number;
	string PK_Client;
	string PK_Product;
	int S_Date;
	int S_Quan;
	int S_Allprice;
};

#endif