#pragma once
#include "Product.h"

typedef enum {
	ADD,
	DELETE,
	UPDATE
} operationType;

typedef struct {
	operationType type;
	Product* product;
} Operation;

Operation* createOperation(Product* product, operationType type);
void destroyOperation(Operation* operation);

operationType getOperationType(Operation* operation);
Product* getOperationProduct(Operation* operation);

Operation* copyOperation(Operation* operation);