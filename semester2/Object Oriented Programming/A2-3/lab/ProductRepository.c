#include "ProductRepository.h"
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdio.h>

ProductRepo* createRepo()
{
	ProductRepo* repo = (ProductRepo*)malloc(sizeof(ProductRepo));
	if (repo == NULL)
		return NULL;
	repo->products = createDynamicArray(CAPACITY, &destroyProduct);
	return repo;
}

void destroyRepo(ProductRepo* repo)
{
	if (repo == NULL)
		return;
	if (repo->products != NULL)
		destroyDynamicArray(repo->products);
	free(repo);
}

int find(ProductRepo* repo, char name[],char category[])	// finds a product based on their name and category
{
	for (int i = 0; i < getDALength(repo->products); i++)
	{
		Product* current_product = getElem(repo->products, i);
		if ((strcmp(current_product->name, name) == 0) && (strcmp(current_product->category, category) == 0))
			return i;
	}
	return -1;
}

Product* getProductOnPosition(ProductRepo* repo, int pos)	// return the product from a specific position
{
	if (repo == NULL)
		return NULL;
	if (pos < 0 || pos >= getDALength(repo->products))
		return NULL;
	return getElem(repo->products, pos);
}



int addProductRepo(ProductRepo* repo, Product* p)
{
	if (find(repo, p->name, p->category) != -1)	// if the product already exists, it updates the quantity
	{
		Product* current_product = getElem(repo->products, find(repo, p->name, p->category));
		current_product->quantity = p->quantity;
		destroyProduct(p);
		return 1;
	}
	else
	{
		addElem(repo->products, p);
		return 1;
	}
	destroyProduct(p);
	return 0;
}

int deleteProductRepo(ProductRepo* repo, char name[], char category[])
{
	int position_product = find(repo, name, category);
	if (position_product != -1)
	{
		deleteElem(repo->products, position_product);
		return 1;
	}
	return 0;
}

int updateProductRepo(ProductRepo* repo, char name[], char category[], int quantity, char expiration_date[])
{
	int position_product = find(repo, name, category);
	if (position_product != -1)
	{
		Product* current_product = getElem(repo->products, find(repo, name, category));
		current_product->quantity = quantity;
		strcpy(current_product->expiration_date, expiration_date);
		return 1;
	}
	return 0;
}

int getLength(ProductRepo* repo)
{
	return getDALength(repo->products);
}

//TESTS


void testAddRepo()
{
	ProductRepo* repo = createRepo();
	assert(getLength(repo) == 0);

	Product* p = createProduct("milk", "dairy", 8, "12/12/2012");
	addProductRepo(repo, p);
	assert(getLength(repo) == 1);

	Product* p1 = createProduct("milk", "dairy", 2, "3/3/2003");
	assert(addProductRepo(repo, p1) == 1);
	assert(getLength(repo) == 1);

	Product* p2 = createProduct("chicken", "meat", 3, "4/4/2004");
	assert(addProductRepo(repo, p2) == 1);
	assert(getLength(repo) == 2);

	Product* p3 = createProduct("chicken", "meat", 6, "5/5/2005");
	assert(addProductRepo(repo, p3) == 1);
	assert(getLength(repo) == 2);

	destroyRepo(repo);
}
void testDeleteRepo()
{
	ProductRepo* repo = createRepo();
	Product* p = createProduct("milk", "dairy", 8, "12/12/2012");
	addProductRepo(repo, p);
	assert(deleteProductRepo(repo, "milk", "dairy") == 1);
	assert(getLength(repo) == 0);
	assert(deleteProductRepo(repo, "milk", "dairy") == 0);
	destroyRepo(repo);
}

void testUpdateRepo()
{
	ProductRepo* repo = createRepo();
	Product* p = createProduct("milk", "dairy", 8, "12/12/2012");
	addProductRepo(repo, p);
	assert(updateProductRepo(repo, "milk", "dairy", 2, "3/3/2003") == 1);
	assert(getLength(repo) == 1);
	destroyRepo(repo);
}
void testsProductRepo()
{
	testAddRepo();
	testDeleteRepo();
	testUpdateRepo();
}