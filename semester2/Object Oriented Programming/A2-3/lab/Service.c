#include "Service.h"
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdio.h>

Service* createService(ProductRepo* repo, StackOperation* stack_operation)
{
	Service* s = malloc(sizeof(Service));
	if (s == NULL)
		return NULL;
	s->repo = repo;
	s->stack = stack_operation;
	return s;
}

void destroyService(Service* s)
{
	if (s == NULL)
		return;
	destroyRepo(s->repo);
	destroyStackOperation(s->stack);
	free(s);
}

ProductRepo* getRepo(Service* s)
{
	return s->repo;
}

void initializeServiceAtStartup(Service * s)
{
	Product* p = createProduct("milk", "dairy", 1, "27/04/2022");
	addProductRepo(s->repo, p);
	p = createProduct("beef", "meat", 7, "21/03/2022");
	addProductRepo(s->repo, p);
	p = createProduct("kiwi", "fruit", 3, "07/04/2022");
	addProductRepo(s->repo, p);
	p = createProduct("cake", "sweets", 6, "12/03/2022");
	addProductRepo(s->repo, p);
	p = createProduct("yogurt", "dairy", 2, "20/04/2022");
	addProductRepo(s->repo, p);
	p = createProduct("pork", "meat", 4, "1/04/2022");
	addProductRepo(s->repo, p);
	p = createProduct("banana", "fruit", 2, "23/04/2022");
	addProductRepo(s->repo, p);
	p = createProduct("cookies", "sweets", 8, "15/03/2022");
	addProductRepo(s->repo, p);
	p = createProduct("chicken", "meat", 2, "12/04/2022");
	addProductRepo(s->repo, p);
	p = createProduct("cheese", "dairy", 1, "02/04/2022");
	addProductRepo(s->repo, p);
	
}

int addProduct(Service* s, char* name, char* category, int quantity, char* expiration_date)
{
	Product* p = createProduct(name, category, quantity, expiration_date);
	int result = addProductRepo(s->repo, p);
	if (result == 0)
		destroyProduct(p);
	else
	{
		Operation* op = createOperation(p, ADD);	// creates the operation for the undo list
		pushUndo(s->stack, op);
	}
	return result;
}

int deleteProduct(Service* s, char name[], char category[])
{
	int product_position = find(s->repo, name, category);
	if (product_position != -1)
	{
		Product* product_found = getElem(s->repo->products, product_position);
		Operation* op = createOperation(product_found, DELETE);	// creates the operation for the undo list
		pushUndo(s->stack, op);
	}
	int result = deleteProductRepo(s->repo, name, category);
	return result;
}

int updateProduct(Service* s, char name[], char category[], int quantity, char expiration_date[])
{
	int product_position = find(s->repo, name, category);
	if (product_position != -1)
	{
		Product* product_found = getElem(s->repo->products, product_position);
		Operation* op = createOperation(product_found, UPDATE);	// creates the operation for the undo list
		pushUndo(s->stack, op);
	}
	int result = updateProductRepo(s->repo, name, category, quantity, expiration_date);
	return result;
}

ProductRepo* filterByName(Service* s, char name[])
{
	ProductRepo* result = createRepo();
	if (strcmp(name, "null") == 0)	// if the input of the user is "null", it adds to the result repository all the products
		for (int i = 0; i < getDALength(s->repo->products); i++) {
			addProductRepo(result, copyProduct(getElem(s->repo->products, i)));
		}
	else
	{
		for (int i = 0; i < getDALength(s->repo->products); i++)
		{
			Product* current_product = getProductOnPosition(s->repo, i);
			if (strstr(current_product->name, name) != NULL) {	// it searches for the string name in the product name

				addProductRepo(result, copyProduct(current_product));
			}
			}
	}
	sortAscendingByQuantity(result);
	return result;
}
void sortAscendingByQuantity(ProductRepo* repo)
{
	int ok = 1;
	while (ok == 1)
	{
		ok = 0;
		for (int i = 0; i < getDALength(repo->products) - 1; i++)
		{
			Product* firstProduct = getElem(repo->products, i);
			Product* secondProduct = getElem(repo->products, i + 1);

			if (firstProduct->quantity > secondProduct->quantity)	// swaps the elements
			{
				Product* aux = firstProduct;
				setElem(repo->products, i, secondProduct);
				setElem(repo->products, i+1, aux);
				ok = 1;
			}
		}
	}

}

ProductRepo* filterByCategory(Service* s, char category[], int number_days)	// in the result repo we will have all the products having the given category
{
	ProductRepo* result = createRepo();
	if (strcmp(category, "null") == 0)
		for (int i = 0; i < getDALength(s->repo->products); i++) {
			addProductRepo(result, copyProduct(getElem(s->repo->products, i)));
		}
	else
	{
		for (int i = 0; i < getDALength(s->repo->products); i++)
		{
			Product* current_product = getProductOnPosition(s->repo, i);
			if (strcmp(current_product->category, category) == 0)	
				addProductRepo(result, copyProduct(current_product));
		}
	}

	return filterByExpirationDay(result, number_days);
}


ProductRepo* filterByExpirationDay(ProductRepo* repo, int number_days)
{
	int i = 0;
	while( i < getDALength(repo->products))
	{
		Product* current_product = getProductOnPosition(repo, i);

		if (check_if_product_expires_in_the_following_days(current_product, number_days) == 0)	// if the product is expired or expires after the given number of days, it deletes it from the list
			deleteProductRepo(repo, current_product->name, current_product->category);
		else
			i++;
	}
	return repo;
}

int check_if_product_expires_in_the_following_days(Product* product, int number_days)
{
	
	time_t s1;
	struct tm* current_date;
	s1 = time(NULL);
	current_date = localtime(&s1); // in the current_date we have the calendar date at the moment of running the program
	int current_day, current_month, current_year;
	current_day = current_date->tm_mday;
	current_month = current_date->tm_mon + 1;
	current_year = current_date->tm_year + 1900;
	
	time_t s2;
	struct tm* expiration_date;
	s2 = time(NULL);
	expiration_date = localtime(&s2);	// here we get the local time
	expiration_date->tm_mday += number_days;	//here we add the number of days to the local time in order to get the maximum expiration_date
	mktime(expiration_date);	// in the expiration_date we have the maximum date of the products we are looking for
	int expiration_day, expiration_month, expiration_year;
	expiration_day = current_date->tm_mday;
	expiration_month = current_date->tm_mon + 1;
	expiration_year = current_date->tm_year + 1900;

	
	char date[50];
	int product_day, product_month, product_year;

	strcpy(date, product->expiration_date);	//format expiration date: dd/mm/yyyy
	char* p = strtok(date, "/");	//we store the day, month and year using strtok
	product_day = atoi(p);
	p = strtok(NULL, "/");
	product_month = atoi(p);
	p = strtok(NULL, "");
	product_year = atoi(p);


	int biger_than_current_day = 0, smaller_than_exp_day = 0;

	// We check if product is not expired
	if (product_year > current_year)	
		biger_than_current_day = 1;
	else if (product_year == current_year && product_month > current_month)
		biger_than_current_day = 1;
	else if (product_year == current_year && product_month == current_month && product_day >= current_day)
		biger_than_current_day = 1;

	//We check if the product expires at a date that is less than the maximum expiration date
	if (product_year < expiration_year)
		smaller_than_exp_day = 1;
	else if (product_year == expiration_year && product_month < expiration_month)
		smaller_than_exp_day = 1;
	else if (product_year == expiration_year && product_month == expiration_month && product_day <= expiration_day)
		smaller_than_exp_day = 1;

	if (biger_than_current_day == 1 && smaller_than_exp_day == 1)
	{	
		return 1;
	}
	return 0;
}

int undo(Service* s)
{
	if (s->stack->undoOperations->length == 0)
		return 0;
	int undo_success = 0;
	Operation* op_undo = popUndo(s->stack);	// here we have the last operation from the undo list
	if (op_undo != NULL)
	{
		if (getOperationType(op_undo) == ADD)
			//if we added an element and want to undo the action, we delete it
		{	Product* product = getOperationProduct(op_undo);
			Operation* op_redo = createOperation(product, DELETE);	//creates operation for the redo function
			if (op_redo == NULL)
				undo_success = 0;
			else {
				pushRedo(s->stack, op_redo);	// we store the operation for the redo function and we delete de element
				deleteProductRepo(s->repo, product->name, product->category);
				undo_success = 1;
			}
		}
		else if (getOperationType(op_undo) == DELETE)
		{
			Product* product = getOperationProduct(op_undo);
			Operation* op_redo = createOperation(product, ADD);
			if (op_redo == NULL)
				undo_success = 0;
			else {
				pushRedo(s->stack, op_redo);
				addProductRepo(s->repo, copyProduct(product));	// adds the product that was previously deleted
				undo_success = 1;
			}
		}
		else if (getOperationType(op_undo) == UPDATE)
		{
			
			Product* product = getOperationProduct(op_undo);
			Product* old_product = getElem(s->repo->products, find(s->repo, product->name, product->category));
			Operation* op_redo = createOperation(old_product, UPDATE);
			if (op_redo == NULL)
				undo_success = 0;
			else {
				pushRedo(s->stack, op_redo);
				updateProductRepo(s->repo, product->name, product->category, product->quantity, product->expiration_date); // updates the product that was previously updated
				undo_success = 1;
			}
		}
		else undo_success = 0;
	}
	destroyOperation(op_undo);
	return undo_success;
}

int redo(Service* s)
{
	Operation* op_redo = popRedo(s->stack);
	int undo_success = 0;
	if (op_redo != NULL)
	{
		if (getOperationType(op_redo) == ADD)
		{
			Product* product = getOperationProduct(op_redo);
			deleteProductRepo(s->repo, product->name, product->category);
			undo_success = 1;
		}
		else if (getOperationType(op_redo) == DELETE)
		{
			Product* product = getOperationProduct(op_redo);
			addProductRepo(s->repo, copyProduct(product));
			undo_success = 1;
		}
		else if (getOperationType(op_redo) == UPDATE)
		{
			Product* product = getOperationProduct(op_redo);
			updateProductRepo(s->repo, product->name, product->category, product->quantity, product->expiration_date);
			undo_success = 1;
			
		}
		else undo_success = 0;
	}
	destroyOperation(op_redo);
	return undo_success;
}

