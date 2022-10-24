#include "lista.h"
#include <iostream>
using namespace std;
typedef int TElem;

int main()
{
	//problem 13. a)Test the inclusion of 2 sets represented as lists
	//b)Eliminate all occurances of an elem from a list
	Lista l1;
	l1 = creare();
	Lista l2;
	l2 = creare();
	//tipar(l);
	//cout << endl;
	cout << "The result for point a) is: ";
	if (test_inclusion(l1, l2) == false)
		cout << "Not inclusion" << endl;
	else
		cout << "Inclusion" << endl;
	TElem var;
	cout << "Give the value you want to remove" << endl;
	cin >> var;
	l1 = elimin(l1, var);
	cout << "The new list is: ";
	tipar(l1);
	return 0;
}
