#pragma once
#include "ProductRepository.h"
#include "StackOperation.h"

typedef struct
{
	ProductRepo* repo;
	StackOperation* stack;
}Service;

Service* createService(ProductRepo* repo, StackOperation* stack_operation);
void destroyService(Service* s);

ProductRepo* getRepo(Service* s);
void initializeServiceAtStartup(Service* s);
int addProduct(Service* s, char* name, char* category, int quantity, char* expiration_date);
int deleteProduct(Service* s, char name[], char category[]);
int updateProduct(Service* s, char name[], char category[], int quantity, char expiration_date[]);

void sortAscendingByQuantity(ProductRepo* repo);
ProductRepo* filterByExpirationDay(ProductRepo* repo, int number_days);

ProductRepo* filterByName(Service* s, char name[]);
ProductRepo* filterByCategory(Service* s, char category[], int number_days);
int check_if_product_expires_in_the_following_days(Product* product, int number_days);

int undo(Service* s);
int redo(Service* s);

