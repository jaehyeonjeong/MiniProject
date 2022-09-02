#ifndef _SHOPPING_MANAGER_H_
#define _SHOPPING_MANAGER_H_

#include "Shopping.h"
#include "ClientManager.h"
#include "ProductManager.h"
#include <vector>
#include <algorithm>

class ShoppingManager
{
public:
	void Shopping_Input(ClientManager& C_ref, ProductManager& P_ref,
		int num, string _clpk, string _prpk, int _date, int _quantatiy);
	void Shopping_Display();
	int getSNumber() { return Snumber; };
	void Shopping_Remove(int _num);
	void Shopping_Remove_All();
	void Shopping_Change(int _num);
	vector<Shopping*> shoppingList;
private:
	int S_Count = 0;
	int Snumber = 0;
	string CL_PK;
	string PR_PK;
	int date;
	int quan;
};

#endif