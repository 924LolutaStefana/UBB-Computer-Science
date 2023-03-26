#pragma once
#include "Product.h"
#include "DynamicArray.h"
#include <time.h>

typedef struct
{
	DynamicArray* products;
}ProductRepo;

ProductRepo* createRepo();
void destroyRepo(ProductRepo* repo);

int find(ProductRepo* repo, char name[], char category[]);
Product* getProductOnPosition(ProductRepo* repo, int pos);
int addProductRepo(ProductRepo* repo, Product* p);
int deleteProductRepo(ProductRepo* repo, char name[], char category[]);
int updateProductRepo(ProductRepo* repo, char name[], char category[], int quantity, char expiration_date[]);

int getLength(ProductRepo* repo);

void testsProductRepo();
