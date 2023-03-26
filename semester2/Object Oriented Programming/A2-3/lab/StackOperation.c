#include "StackOperation.h"
#include <stdio.h>
#include <stdlib.h>

StackOperation* createStackOperation()
{
	StackOperation* stack_operations = malloc(sizeof(StackOperation));
	stack_operations->undoOperations = createDynamicArray(CAPACITY, &destroyOperation);
	stack_operations->redoOperations = createDynamicArray(CAPACITY, &destroyOperation);
	return stack_operations;
}

void destroyStackOperation(StackOperation* stack_operation)
{
	if (stack_operation == NULL)
		return;
	destroyDynamicArray(stack_operation->undoOperations);
	destroyDynamicArray(stack_operation->redoOperations);
	free(stack_operation);
}

void pushUndo(StackOperation* stack_operation, Operation* operation)	// adds an operation to the undo list of operations
{
	addElem(stack_operation->undoOperations, operation);
}

Operation* popUndo(StackOperation* stack_operation)	// gets the last operation of the undo list and deletes it from the list
{
	Operation* operation = getElem(stack_operation->undoOperations, stack_operation->undoOperations->length - 1);	
	Operation* copy = copyOperation(operation);
	deleteElem(stack_operation->undoOperations, stack_operation->undoOperations->length - 1);
	return copy;
}

void pushRedo(StackOperation* stack_operation, Operation* operation)	// adds an operation to the redo list of operations
{
	addElem(stack_operation->redoOperations, operation);
}

Operation* popRedo(StackOperation* stack_operation)		// gets the last operation of the redo list and deletes it from the list
{
	DynamicArray* arr = stack_operation->redoOperations;
	if (arr->length == 0)
		return NULL;
	Operation* operation = getElem(arr, arr->length - 1);
	Operation* copy = copyOperation(operation);
	deleteElem(arr, arr->length - 1);

	return copy;
}
