#include "Bag.h"
#include "BagIterator.h"
#include <exception>
#include <iostream>
using namespace std;


Bag::Bag() {
	
	this->nr_elements_P = 0;
	this->nr_elements_U = 0;
	this->capacity= 10;
	this->U = new TElem[capacity];
	this->P = new TElem[capacity];
}
//Theta(1)

void Bag::resize()
{
	this->capacity *= 2;
	TElem* aux1, * aux2;
	aux1 = new TElem[capacity];
	aux2 = new TElem[capacity];
	for (int i = 0; i < this->nr_elements_P; i++)
	{
		aux1[i] = this->P[i];
		aux2[i] = this->U[i];
	}
	delete[] this->P;
	delete[] this->U;
	this->P = aux1;
	this->U = aux2;

}
///Theta(nrElements)
void Bag::add(TElem elem) {
	int index = 0;
	int found = 0;
	while (index < this->nr_elements_U && found == 0)
	{
		if (this->U[index] == elem)
		{
			found = 1;
		}
		else
		{
			index++;
		}
	}
	if (found == 0)
	{
		this->U[this->nr_elements_U] = elem;
		this->nr_elements_U++;
		this->P[this->nr_elements_P] = this->nr_elements_U - 1;
		this->nr_elements_P++;
	}
	else
	{
		this->P[this->nr_elements_P] = index;
		this->nr_elements_P++;

	}
	if (this->capacity - 1 == this->nr_elements_P)
		resize();
}
//Best case: Theta(1), Worst case:Theta(nr_elements_U) => Total complexity: O(nr_elements_U)


bool Bag::remove(TElem elem) {
	int index1 = -1;
	int index2 = -1;
	for (int i = 0; i < this->nr_elements_P; i++) {
		if (this->U[this->P[i]] == elem) {
			if (index1 == -1) {
				index1 = i;
			}
			index2 = i;
		}
	}
	if (index2 == -1) {
		return false;
	}
	for (int i = index2; i + 1 < this->nr_elements_P; i++) {
		this->P[i] = this->P[i + 1];
	}
	this->nr_elements_P--;

	if (index2 == index1) { 
		int u_index = -1;
		for (int i = 0; i < this->nr_elements_U; i++) {
			if (this->U[i] == elem) {
				u_index = i;
				break;
			}
		}
		for (int i = u_index; i + 1 < this->nr_elements_U; i++) {
			this->U[i] = this->U[i + 1];
		}
		this->nr_elements_U--;
		for (int i = 0; i < this->nr_elements_P; i++) {
			if (this->P[i] > u_index) {
				this->P[i]--;
			}
		}
	}
	return true;

}
//Best case: Theta(nr_elements_P), Worst case:Theta(nr_elements_P + nr_elements_U) => Total complexity: O(nr_elements_P)


bool Bag::search(TElem elem) const {
	bool found = false;
	int index = 0;
	while (index < this->nr_elements_U && found == false)
	{
		if (this->U[index] == elem)
		{
			found = true;
		}
		else
		{
			index++;
		}
	}
	if (found == false)
		return false;
	else
		return true;
}
//Best case: Theta(1), Worst case:Theta(nr_Elements_U) => Total complexity: O(nr_Elements_U)

int Bag::nrOccurrences(TElem elem) const {
	bool found = false;
	int index = 0;
	int j = 0;
	int nr = 0;
	while (index < this->nr_elements_U && found == false)
	{
		if (this->U[index] == elem)
		{
			found = true;
		}
		else
		{
			index++;
		}
	}
	if (found == true) {
		while (j < this->nr_elements_P)
		{
			if (this->P[j] == index)
				nr++;
			j++;
		}
	}

	return nr;
}
//Best case: Theta(nr_elements_P), Worst case:Theta(nr_elements_P + nr_elements_U) => Total complexity: Theta(nr_elements_P)

int Bag::size() const {
	return this->nr_elements_P;
	
}
//Theta(1)


bool Bag::isEmpty() const {
	if (this->nr_elements_U == 0)
		return true;
	else
		return false;
}
//Theta(1)

BagIterator Bag::iterator() const {
	return BagIterator(*this);
}
//Theta(1)
/*void Bag::intersection(const Bag& b) {
	int index = 0;
	int index2 = 0;
	while (index < nr_elements_U)
	{
		while (index2 < nr_elements_U)
		{
			if (b.U[index2] != this->U[index])
			{
				remove(this->U[index]);

				
			}
			index2++;
		}
		index++;
	}
}*/
//Best case: Theta(nr_elements_U*nr_elements_U), Worst case:Theta(nr_elements_U*nr_elements_U) => Total complexity: Theta(nr_elements_U*nr_elements_U)

Bag::~Bag() {
	delete[] this->P;
	delete[] this->U;
}
//Theta(1)

