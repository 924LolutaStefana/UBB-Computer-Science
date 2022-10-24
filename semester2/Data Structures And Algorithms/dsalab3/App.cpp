#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <iostream>
#include "ShortTest.h"
#include "ExtendedTest.h"

using namespace std;

int main() {
	cout << "TESTS\n";
	testAll();
	testIntersection();
	testAllExtended();
	
	cout << "Test over" << endl;
	system("pause");
}
