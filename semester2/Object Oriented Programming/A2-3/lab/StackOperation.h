#pragma once
#include "DynamicArray.h"
#include "Operation.h"

typedef struct {
	DynamicArray* undoOperations;
	DynamicArray* redoOperations;
}StackOperation;

StackOperation* createStackOperation();
void destroyStackOperation(StackOperation* stack_operation);
void pushUndo(StackOperation* stack_operation, Operation* operation);
Operation* popUndo(StackOperation* stack_operation);
void pushRedo(StackOperation* stack_operation, Operation* operation);
Operation* popRedo(StackOperation* stack_operation);