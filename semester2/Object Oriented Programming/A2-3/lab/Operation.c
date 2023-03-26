#include "Operation.h"
#include <stdio.h>
#include <stdlib.h>

Operation* createOperation(Product* product, operationType type)
{
	Operation* operation;

	operation = malloc(sizeof(Operation));
	operation->product = copyProduct(product);
	operation->type = type;
	return operation;
}

void destroyOperation(Operation* operation)
{
	if (operation == NULL)
		return;
	destroyProduct(operation->product);
	free(operation);
}

operationType getOperationType(Operation* operation)
{
	return operation->type;
}

Product* getOperationProduct(Operation* operation)
{
	return operation->product;
}

Operation* copyOperation(Operation* operation)
{
	Operation* copyOperation;
	copyOperation = createOperation(operation->product, operation->type);
	return copyOperation;
}