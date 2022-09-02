#ifndef _PRODUCT_MANAGER_H_
#define _PRODUCT_MANAGER_H_

#include "Product.h"
#include <vector>
#include <algorithm>
#include <cstring>
#include <fstream>
#include <sstream>
#include <iomanip>

class ProductManager
{
public:
	void Product_Input(string _id,
		string _name, int _price);
	void Product_Display();
	void Product_Remove(string _id);
	void Product_Remove_All();
	void Product_Change(string _id);
	void Product_PK(string _id);
	int getPCount() { return P_Count; };
	void Product_Save();
	void Product_Load();
	vector<string> parseCSV(istream& file, char delimiter);
	vector<Product*> productList;
private:
	int P_Count = 0;
	  
	string PM_ID;
	string PM_Name;
	int PM_Price;
};


#endif