#include "UI.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

UI* createUI(Service* s)
{
	UI* ui = malloc(sizeof(ui));
	if (ui == NULL)
		return NULL;
	ui->serv = s;
	return ui;
}

void destroyUI(UI* ui)
{
	if (ui == NULL)
		return;
	destroyService(ui->serv);
	free(ui);
}


void printMenu()
{
	printf("\n****************************************************************************\n");
	printf("1 - Add product \n");
	printf("2 - Delete product \n");
	printf("3 - Update product\n");
	printf("4 - Display all products whose name contains a given string\n");
	printf("5 - Display all products of a given category whose expiration dates are close\n");
	printf("6 - Undo\n");
	printf("7 - Redo\n");
	printf("0 - Exit\n");
	printf("\n****************************************************************************\n");

}

int validCommand(int command)
{
	if (command >= 0 && command <= 7)
		return 1;
	return 0;
}

int readInteger()
{
	char input[16] = { 0 };
	int option = 0, result = 0, flag = 0;
	while (flag == 0)
	{
		printf("Choose option: ");
		int scanf_result = scanf("%15s", input);

		result = sscanf(input, "%d", &option);
		flag = (result == 1);
		if (flag == 0)
			printf("Error reading number!\n");
	}
	return option;

}

int addProductUI(UI* ui)
{
	char name[50], category[50], expiration_date[50];
	int quantity = 0;
	char categories[4][10] = { "dairy", "meat", "sweets", "fruit" };

	printf("Please input the name: ");
	int scanf_result = scanf("%49s", name);
	printf("Please input the category: ");
	scanf_result = scanf("%49s", category);
	int correct = 0;
	for (int i = 0; i < 4; i++)
		if (strcmp(category, categories[i]) == 0)
			correct = 1;
	if (correct == 0)
		return 0;
	printf("Please input the quantity: ");
	scanf_result = scanf("%d", &quantity);
	printf("Please input the expiration date (format DD/MM/YYYY): ");
	scanf_result = scanf("%49s", expiration_date);

	return addProduct(ui->serv, name, category, quantity, expiration_date);
}


int deleteProductUI(UI* ui)
{
	char name[50], category[50];
	printf("Please input the name: ");
	int scanf_result = scanf("%49s", name);
	printf("Please input the category: ");
	scanf_result = scanf("%49s", category);
	return deleteProduct(ui->serv, name, category);
}

int updateProductUI(UI* ui)
{
	char name[50], category[50], expiration_date[50];
	int quantity = 0;

	printf("Please input the name: ");
	int scanf_result = scanf("%49s", name);
	printf("Please input the category: ");
	scanf_result = scanf("%49s", category);
	printf("Please input the quantity: ");
	scanf_result = scanf("%d", &quantity);
	printf("Please input the expiration date (format DD/MM/YYYY): ");
	scanf_result = scanf("%49s", expiration_date);

	return updateProduct(ui->serv, name, category, quantity, expiration_date);
}

void printProducts(UI* ui)
{
	if (ui == NULL)
		return;
	ProductRepo* repo = getRepo(ui->serv);
	if (repo == NULL)
		return;

	for (int i = 0; i < getLength(repo); i++)
	{
		Product* product = getProductOnPosition(repo, i);
		char productString[200];
		toString(product, productString);
		printf("% s\n", productString);
	}
}

void listProductsByName(UI* ui)
{
	char name[50];
	printf("Please input the name; input 'null' for all names: ");
	int scanf_result = scanf("%49s", name);

	ProductRepo* result = filterByName(ui->serv, name);
	
	for (int i = 0; i < getLength(result); i++)
	{
		Product* product = getProductOnPosition(result, i);
		char productString[200];
		toString(product, productString);
		printf("% s\n", productString);
	}

	destroyRepo(result);
}

void listProductsByCategory(UI* ui)
{
	char category[50];
	int number_days;
	printf("Please input the category; input 'null' for all types of food: ");
	int scanf_result = scanf("%49s", category);
	printf("Please input the number of days: ");
	scanf("%d", &number_days);

	ProductRepo* result = filterByCategory(ui->serv, category, number_days);

	for (int i = 0; i < getLength(result); i++)
	{
		Product* product = getProductOnPosition(result, i);
		char productString[200];
		toString(product, productString);
		printf("% s\n", productString);
	}

	destroyRepo(result);
}

int undoUI(UI* ui) {
	return undo(ui->serv);
}

int redoUI(UI* ui) {
	return redo(ui->serv);
}

void startUI(UI* ui)
{
	while (1)
	{
		printMenu();
		int command = readInteger();
		while (validCommand(command) == 0)
		{
			printf("Please input a valid command!\n");
			command = readInteger();
		}
		if (command == 0)
			break;
		switch (command)
		{
		case 1:
		{
			int res = addProductUI(ui);
			
			if (res == 1)
				printf("Product successfully added.\n");
			else
				printf("Error! Product could not be added as there is no such category\n");
			break;
		}
		case 2:
		{
			int res = deleteProductUI(ui);
			
			if (res == 1)
				printf("Product successfully deleted.\n");
			else
				printf("Error! Product could not be deleted, as there is no product with this name and category!\n");
			break;
		}
		case 3:
		{
			int res = updateProductUI(ui);
			if (res == 1)
				printf("Product successfully updated.\n");
			else
				printf("Error! Product could not be updated, as there is no product with this name and category!\n");

			break;
		}
		case 4:
		{
			listProductsByName(ui);
			break;
		}
		case 5:
		{
			listProductsByCategory(ui);
			break;
		}
		case 6:
		{
			int res = undoUI(ui);
			if (res == 1)
				printf("Succes undo");
			else printf("Failed undo");
			break;
		}
		case 7:
		{
			int res = redoUI(ui);
			if (res == 1)
				printf("Succes redo");
			else printf("Failed redo");
			break;
		}
		}
	}
}