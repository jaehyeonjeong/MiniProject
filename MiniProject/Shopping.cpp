#include "Shopping.h"

int Shopping::getSNumber()
{
	return S_Number;
}

void Shopping::setSNumber(int _number)
{
	S_Number = _number;
}

string Shopping::getSPKClient()
{
	return PK_Client;
}

void Shopping::setSPKClient(string& _client)
{
	PK_Client = _client;
}

string Shopping::getSPKProduct()
{
	return PK_Product;
}

void Shopping::setSPKProcut(string& _product)
{
	PK_Product = _product;
}

int Shopping::getSDate()
{
	return S_Date;
}

void Shopping::setSDate(int _date)
{
	S_Date = _date;
}

int Shopping::getSQuan()
{
	return S_Quan;
}

void Shopping::setSQuan(int _quan)
{
	S_Quan = _quan;
}

int Shopping::getSAllprice()
{
	return S_Allprice;
}