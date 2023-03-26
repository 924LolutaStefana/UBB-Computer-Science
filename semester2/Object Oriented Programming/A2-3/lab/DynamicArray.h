#pragma once
#define CAPACITY 100

typedef void* TElement;
typedef void (*DestroyElementFunctionType)(void*);

typedef struct {
	TElement* elements;
	int length;
	int capacity;
	DestroyElementFunctionType destroyElemFct;
} DynamicArray;

DynamicArray* createDynamicArray(int capacity, DestroyElementFunctionType destroyElemFct);
void destroyDynamicArray(DynamicArray* arr);
void addElem(DynamicArray* arr, TElement elem);
void deleteElem(DynamicArray* arr, int position);
int getDALength(DynamicArray* arr);
TElement getElem(DynamicArray* arr, int position);
void testDynamicArray();
void setElem(DynamicArray* arr, int position, TElement new_element);

void testDynamicArray();
