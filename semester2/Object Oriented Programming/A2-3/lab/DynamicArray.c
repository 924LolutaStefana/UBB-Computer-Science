#include "DynamicArray.h"
#include "Product.h"
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <string.h>

DynamicArray* createDynamicArray(int capacity, DestroyElementFunctionType destroyElemFct) {
	DynamicArray* arr = (DynamicArray*)malloc(sizeof(DynamicArray));
	if (arr == NULL) {
		return NULL;
	}
	arr->capacity = capacity;
	arr->length = 0;

	arr->elements = (TElement*)malloc(capacity * sizeof(TElement));
	if (arr->elements == NULL) {
		return NULL;
	}

	arr->destroyElemFct = destroyElemFct;
	return arr;
}

void destroyDynamicArray(DynamicArray* arr) {
	if (arr == NULL) {
		return;
	}
	for (int i = 0; i < arr->length; ++i) {
		arr->destroyElemFct(arr->elements[i]);
	}
	free(arr->elements);
	arr->elements = NULL;
	free(arr);
	arr = NULL;
}

int resize(DynamicArray* arr) {
	if (arr == NULL) {
		return -1;
	}
	arr->capacity *= 2;

	TElement* aux = (TElement*)realloc(arr->elements, arr->capacity * sizeof(TElement));
	if (aux == NULL) {
		return -1;
	}
	arr->elements = aux;
	return 0;
}

void addElem(DynamicArray* arr, TElement elem) {
	if (arr == NULL) {
		return;
	}
	if (arr->elements == NULL) {
		return;
	}
	if (arr->length == arr->capacity) {
		resize(arr);
	}
	arr->elements[arr->length] = elem;
	arr->length++;
}

void deleteElem(DynamicArray* arr, int position) {
	if (arr == NULL || position < 0 || position >= arr->length) {
		return;
	}
	arr->destroyElemFct(arr->elements[position]);
	if (position != arr->length - 1) {
		arr->elements[position] = arr->elements[arr->length - 1];
	}
	arr->length--;
}

int getDALength(DynamicArray* arr) {
	if (arr == NULL) {
		return -1;
	}
	return arr->length;
}

TElement getElem(DynamicArray* arr, int position) {
	if (arr == NULL || position < 0 || position >= getDALength(arr)) {
		return NULL;
	}
	return arr->elements[position];
}

void setElem(DynamicArray* arr, int position, TElement new_element)
{
	arr->elements[position] = new_element;
}


// TESTS


void testAddDA() {
	DynamicArray* arr = createDynamicArray(10, &destroyProduct);
	//initDynamicArray(arr);
	Product* p1 = createProduct("milk", "dairy", 7, "10/10/2001");
	addElem(arr, p1);
	assert(getDALength(arr) == 1);

	Product* new_product = createProduct("cheese", "dairy", 1, "31/32/3333");
	addElem(arr, new_product);
	assert(getDALength(arr) == 2);

	destroyDynamicArray(arr);
}

void testDeleteDA() {
	DynamicArray* arr = createDynamicArray(10, &destroyProduct);
	Product* p1 = createProduct("milk", "dairy", 7, "10/10/2001");
	addElem(arr, p1);
	assert(getDALength(arr) == 1);

	deleteElem(arr, 0);
	assert(getDALength(arr) == 0);
	destroyDynamicArray(arr);
}

void testGetDA() {
	DynamicArray* arr = createDynamicArray(10, &destroyProduct);
	Product* p1 = createProduct("milk", "dairy", 7, "10/10/2001");
	addElem(arr, p1);

	Product* product = getElem(arr, 0);
	assert(strcmp(product->name, "milk") == 0);

	destroyDynamicArray(arr);
}

void testSetDA() {
	DynamicArray* arr = createDynamicArray(10, &destroyProduct);
	Product* p1 = createProduct("milk", "dairy", 7, "10/10/2001");
	addElem(arr, p1);

	Product* product_to_set = createProduct("cheese", "dairy", 1, "31/32/3333");

	setElem(arr, 0, product_to_set);
	Product* product = getElem(arr, 0);
	assert(strcmp(product->name, "cheese") == 0);
	destroyProduct(p1);
	destroyDynamicArray(arr);
}

void testDynamicArray() {
	testAddDA();
	testDeleteDA();
	testGetDA();
	testSetDA();
}