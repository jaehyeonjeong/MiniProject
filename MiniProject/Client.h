#ifndef _CLIENT_H_
#define _CLIENT_H_

#include <iostream>
using namespace std;

class Client
{
public:
	Client(string _word = "", string _name = "",
		string _phone = "", string _email = "")
		:C_Custom_Word(_word), C_Name(_name),
		C_Phone(_phone), C_Email(_email)
	{

	}
	string getCWord();
	void setCWord(string& _custom);
	string getCName();
	void setCName(string& _name);
	string getCPhone();
	void setCPhone(string& _phone);
	string getCEmail();
	void setCEmail(string& _email);
	bool operator==(string str) const;
private:
	string C_Custom_Word;
	string C_Name;
	string C_Phone;
	string C_Email;
};

#endif