#include "Product.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

Product* createProduct(char* name, char* category, int quantity, char* expiration_date)
{
    Product* p = malloc(sizeof(Product));
    if (p == NULL)
        return NULL;
    p->name = malloc(sizeof(char) * (strlen(name) + 1));
    if (p->name != NULL)
        strcpy(p->name, name);
    p->category = malloc(sizeof(char) * (strlen(category) + 1));
    if (p->category != NULL)
        strcpy(p->category, category);
    p->quantity = quantity;
    p->expiration_date = malloc(sizeof(char) * (strlen(expiration_date) + 1));
    if (p->expiration_date != NULL)
        strcpy(p->expiration_date, expiration_date);

    return p;
}

void destroyProduct(Product* p)
{
    if (p == NULL)
        return;
    if (p->expiration_date != NULL) {
        free(p->expiration_date);
    }
    if (p->category != NULL) {
        free(p->category);
    }
    if (p->name != NULL) {
        free(p->name);
    }
    free(p);
}

char* getName(Product* p)
{
    if (p == NULL)
        return NULL;
    return p->name;

}

char* getCategory(Product* p)
{
    if (p == NULL)
        return NULL;
    return p->category;
}

int getQuantity(Product* p)
{
    if (p == NULL)
        return -1;
    return p->quantity;
}

char* getExpirationDate(Product* p)
{
    if (p == NULL)
        return NULL;
    return p->expiration_date;

}

Product* copyProduct(Product* p) {
    if (p == NULL)
        return NULL;
    return createProduct(p->name, p->category, p->quantity, p->expiration_date);
}

void toString(Product* p, char str[])
{
    if (p == NULL)
        return;
    sprintf(str, "PRODUCT: %s,   CATEGORY: %s,   QUANTITY: %d,   EXPIRATION DATE: %s", p->name, p->category, p->quantity, p->expiration_date);

}
//TESTS

void testProduct()
{
    Product* p = createProduct("milk", "dairy", 56, "22/22/2222");
    assert(strcmp(getName(p), "milk") == 0);
    assert(strcmp(getCategory(p), "dairy") == 0);
    assert(getQuantity(p) == 56);
    assert(strcmp(getExpirationDate(p), "22/22/2222") == 0);
    destroyProduct(p);

}
