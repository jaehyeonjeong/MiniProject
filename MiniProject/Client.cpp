#include "Client.h"

string Client::getCWord()
{
	return C_Custom_Word;
}

void Client::setCWord(string& _custom)
{
	C_Custom_Word = _custom;
}

string Client::getCName()
{
	return C_Name;
}

void Client::setCName(string& _name)
{
	C_Name = _name;
}

string Client::getCPhone()
{
	return C_Phone;
}

void Client::setCPhone(string& _phone)
{
	C_Phone = _phone;
}

string Client::getCEmail()
{
	return C_Email;
}

void Client::setCEmail(string& _email)
{
	C_Email = _email;
}

bool Client::operator==(string str)const
{
	return (this->C_Custom_Word == str);
}