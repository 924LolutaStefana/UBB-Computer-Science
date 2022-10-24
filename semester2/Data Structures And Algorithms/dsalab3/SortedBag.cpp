#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <exception>


SortedBag::SortedBag(Relation r) {
	//Complexity: O(n) - where n is the initial capacity
	this->rel = r;
	this->cap = 8;
	this->elements = new TPair[cap];
	this->next = new int[cap];
	this->head = -1;
	for (int i = 0; i < cap - 1; i++)
		this->next[i] = i + 1;
	next[cap - 1] = -1;
	this->firstEmpty = 0;
}

void SortedBag::add(TComp e) {
	//Complexity: O(n)
	int pos = this->head, prev = -1;
	while (pos != -1 && !rel(e, this->elements[pos].first)) // find position to insert
	{
		prev = pos;
		pos = next[pos];
	}
	if (pos != -1 && this->elements[pos].first == e) // element already exists
	{
		this->elements[pos].second++;
		return;
	}
	if (this->firstEmpty == -1)
		this->resizeSLLA();
	pair<TElem, int> newElement(e, 1); // create new element
	int newPos = this->firstEmpty;
	this->elements[newPos] = newElement;
	this->firstEmpty = this->next[firstEmpty];
	this->next[newPos] = pos;
	if (pos == this->head)
		this->head = newPos;
	else
		this->next[prev] = newPos;
}


bool SortedBag::remove(TComp e) {
	//Complexity: O(n)
	int current = this->head, prev = -1;
	while (current != -1 && this->elements[current].first != e)
	{
		if (!rel(this->elements[current].first, e)) // it was not found in the sorted bag, stop earlier
			return false;
		prev = current;
		current = next[current];
	}
	if (current != -1)
	{
		this->elements[current].second--;
		if (this->elements[current].second == 0)
		{
			if (current == this->head)
				this->head = this->next[head];
			else
				this->next[prev] = this->next[current];
			this->next[current] = this->firstEmpty;
			this->firstEmpty = current;
		}
		return true;
	}
	return false;
}


bool SortedBag::search(TComp elem) const {
	//Complexity: O(n)
	int current = this->head;
	while (current != -1 && this->elements[current].first != elem)
	{
		if (!rel(this->elements[current].first, elem)) // it was not found in the sorted bag, stop earlier
			return false;
		current = next[current];
	}
	if (current != -1)
		return true;
	return false;
}


int SortedBag::nrOccurrences(TComp elem) const {
	//Complexity: O(n)
	int current = this->head;
	while (current != -1)
	{
		if (!rel(this->elements[current].first, elem))
			break;
		if (this->elements[current].first == elem)
			return this->elements[current].second;
		current = next[current];
	}
	return 0;
}


int SortedBag::size() const {
	//Complexity: O(n)
	int size = 0, current = this->head;
	while (current != -1)
	{
		size += this->elements[current].second;
		current = next[current];
	}
	return size;
}


bool SortedBag::isEmpty() const {
	//Complexity: O(1)
	return this->head == -1;
}


SortedBagIterator SortedBag::iterator() const {
	// Complexity: O(1)
	return SortedBagIterator(*this);
}



SortedBag::~SortedBag() {
	//Complexity: O(1)
	delete elements;
	delete next;
}
void SortedBag::intersection(const SortedBag& b) {
	int current=this->head;
	int current2 = b.head;
	bool found = false;
	int pos = 0;
	while (current !=-1)
	{
		while (current2 != -1 && found == false)
		{
			if (this->elements[current].first== b.elements[current2].first)
			{
				found == true;
				
				pos = current;

			}
			current2=next[current2];
		}
		if(found==false)
		{
			remove(this->elements[pos].first);
			pos = 0;
			
		}
		found = false;
			
		current=next[current];
	}
}


void SortedBag::resizeSLLA()
{
	//Complexity: O(n)
	TPair* newElems = new TPair[cap * 2];

	int* newNext = new int[cap * 2];
	for (int i = 0; i < cap; i++)
	{
		newElems[i] = this->elements[i];
		newNext[i] = this->next[i];
	}
	for (int i = cap; i < cap * 2 - 1; i++)
		newNext[i] = i + 1;
	newNext[cap * 2 - 1] = -1;
	delete this->elements;
	delete this->next;
	this->elements = newElems;
	this->next = newNext;
	this->firstEmpty = cap;
	this->cap *= 2;
}