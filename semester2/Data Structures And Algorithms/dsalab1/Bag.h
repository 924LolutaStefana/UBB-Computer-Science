#pragma once
//DO NOT INCLUDE BAGITERATOR


//DO NOT CHANGE THIS PART
#define NULL_TELEM -111111;
typedef int TElem;
class BagIterator; 
class Bag {

private:
	TElem* U;
	TElem* P;
	int capacity;/// The maximum number of elements that can be stored
	int nr_elements_P;/// The current number of elements stored in the container
	int nr_elements_U;
	void resize();


	//DO NOT CHANGE THIS PART
	friend class BagIterator;
public:
	//constructor
	Bag();

	//adds an element to the bag
	void add(TElem e);

	//removes one occurence of an element from a bag
	//returns true if an element was removed, false otherwise (if e was not part of the bag)
	bool remove(TElem e);

	//checks if an element appearch is the bag
	bool search(TElem e) const;

	//returns the number of occurrences for an element in the bag
	int nrOccurrences(TElem e) const;

	//returns the number of elements from the bag
	int size() const;
	 
	//does the intersection of the bag and a given bag
	//if an allements appears multiple times in both bags. it will be kept the minimum times
	//void intersection(const Bag& b);

	//returns an iterator for this bag
	BagIterator iterator() const;

	//checks if the bag is empty
	bool isEmpty() const;


	//destructor
	~Bag();
};