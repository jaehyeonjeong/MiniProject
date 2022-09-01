#ifndef _SHOPPING_H_
#define _SHOPPING_H_

#include <iostream>
using namespace std;

class Shopping
{
public:
	Shopping(int = 0, string = "", string = "", int = 0, int = 0);
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
private:
	int S_Number;
	string PK_Client;
	string PK_Product;
	int S_Date;
	int S_Quan;
};

#endif