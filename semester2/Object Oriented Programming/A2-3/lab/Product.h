#pragma once

typedef struct {
	char* name;
	char* category;
	int quantity;
	char* expiration_date;
}Product;

Product* createProduct(char* name, char* category, int quantity, char* expiration_date);

void destroyProduct(Product* p);

char* getName(Product* p);
char* getCategory(Product* p);
int getQuantity(Product* p);
char* getExpirationDate(Product* p);

Product* copyProduct(Product* p);
void toString(Product* p, char str[]);

void testProduct();