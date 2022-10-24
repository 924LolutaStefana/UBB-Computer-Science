#include <exception>
#include "BagIterator.h"
#include "Bag.h"

using namespace std;


BagIterator::BagIterator(const Bag& c): bag(c)
{
	
	this->current = 0;
}
//Theta(1)

void BagIterator::first() {
	this->current = 0;
}
//Theta(1)


void BagIterator::next() {
	if (this->current >= this->bag.size()) {
		throw exception();
	}
	this->current++;
}
//Theta(1)

bool BagIterator::valid() const {
	return (this->current < this->bag.size());
}
//Theta(1)


TElem BagIterator::getCurrent() const
{
	if (this->valid() == false) {
		throw exception();
	}
	return this->bag.U[this->bag.P[this->current]];
}
//Theta(1)