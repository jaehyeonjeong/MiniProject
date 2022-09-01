#ifndef _PRODUCT_H_
#define _PRODUCT_H_

#include <iostream>
using namespace std;

class Product
{
public:
	Product(string = "", string = "", int = 0);
	string getPId();
	void setPId(string& _id);
	string getPName();
	void setPName(string& _name);
	int getPPrice();
	void setPPrice(int _price);

private:
	string P_Id;
	string P_Name;
	int P_Price;
};

#endif