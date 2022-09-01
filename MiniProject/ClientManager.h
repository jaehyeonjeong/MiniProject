#ifndef _CLIENT_MANAGER_H_
#define _CLIENT_MANAGER_H_

#include "Client.h"
#include <vector>
#include <algorithm>
#include <cstring>

class ClientManager
{
public:
	void Client_Input(string _word, 
		string _name, string _phone, string _email);
	void Client_Display();
	void Client_Remove(string _word, string _name);
	void Client_Remove_All();
	void Client_Change(string _word, string _name);
	void Client_PK(string _word);
private:
	int C_Count = 0;
	vector<Client*> clientList;
	string CM_Word;
	string CM_Name;
	string CM_Phone;
	string CM_Email;
};

#endif