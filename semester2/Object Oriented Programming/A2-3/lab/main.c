#include "UI.h"
#include <crtdbg.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>

int main()
{
	testProduct();
	testsProductRepo();
	testDynamicArray();
	ProductRepo* repo = createRepo();
	StackOperation* stack = createStackOperation();
	Service* serv = createService(repo, stack);
	initializeServiceAtStartup(serv);
	UI* ui = createUI(serv);
	startUI(ui);
	destroyUI(ui);
	_CrtDumpMemoryLeaks();
	return 0;
	
}