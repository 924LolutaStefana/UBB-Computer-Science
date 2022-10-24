#include <exception>
#include "ListIterator.h"
#include "IteratedList.h"


IteratedList::IteratedList() {
    this->linkedList.head = nullptr;
    this->linkedList.tail = nullptr;
    this->sizeList = 0;
}
//Theta(1)

int IteratedList::size() const {
    return this->sizeList;
} // Theta(1)

bool IteratedList::isEmpty() const {
    if (this->sizeList == 0)
        return true;
    return false;
} // Theta(1)

ListIterator IteratedList::first() const {
    return ListIterator(*this);
} // Theta(1)

TElem IteratedList::getElement(ListIterator pos) const {
    return pos.getCurrent();
} // Theta(1)

TElem IteratedList::remove(ListIterator& pos) {
    // check if valid
    if (!pos.valid())
        throw std::exception();

    TElem elem = pos.currentElement->data;

    // search for prev node
    DLLNode* currentNode = this->linkedList.head;
    DLLNode* prevNode = nullptr;

    while (currentNode != nullptr && currentNode != pos.currentElement) {
        prevNode = currentNode;
        currentNode = currentNode->next;
    }

    // remove the current node
    if (currentNode != nullptr && prevNode == nullptr) // delete the head
        this->linkedList.head = this->linkedList.head->next;
    else if (currentNode != nullptr) {
        prevNode->next = currentNode->next;
        currentNode->next = nullptr;
    }

    this->sizeList--;
    return elem;
} // complexity: O(n)


ListIterator IteratedList::search(TElem e) const {
    ListIterator it = this->first();
    while (it.currentElement != nullptr) {
        if (it.currentElement->data != e)
            it.next();
        else
            break;
    }
    return it;
} // Complexity: O(n)

void IteratedList::removeBetween(ListIterator& start, ListIterator& end)
{
    if (!start.valid() && !end.valid())
        throw std::exception();
    while (start.getCurrent() != end.getCurrent())
    {
        remove(start);
        start.next();
    }
    if (start.getCurrent() == end.getCurrent())
    {
        remove(start);
    }

}
//O(n)



TElem IteratedList::setElement(ListIterator pos, TElem e) {
    if (!pos.valid())
        throw std::exception();
    TElem old = pos.getCurrent();
    pos.currentElement->data = e;
    return old;
} // Theta(1)

void IteratedList::addToBeginning(TElem e) {
    this->sizeList++;
    DLLNode* new_node = new DLLNode();
    new_node->data = e;

    /* Make next of new node as head
    and previous as NULL */
    new_node->next = linkedList.head;
    new_node->prev = NULL;

    /*  change prev of head node to new node */
    if (linkedList.head != NULL)
        linkedList.head->prev = new_node;
    else
        linkedList.tail = new_node;// if the list was empty, the tail is going
    //to be the same as the head
    /*  move the head to point to the new node */
    linkedList.head = new_node;
}
// Theta(1)

void IteratedList::addToPosition(ListIterator& pos, TElem e) {
    if (!pos.valid())
        throw std::exception();

    this->sizeList++;
    DLLNode* newNode = new DLLNode();
    newNode->data = e;
    newNode->next = NULL;
    newNode->prev = NULL;
    newNode->next = pos.currentElement->next;
    pos.currentElement->next = newNode;
    pos.next();
} // Theta(1)

void IteratedList::addToEnd(TElem e) {
    this->sizeList++;
    DLLNode* new_node = new DLLNode();
    DLLNode* last = linkedList.head;
    new_node->data = e;

    /*  This new node is going to be the last node, so
        make next of it as NULL*/
    new_node->next = NULL;
    /*  If the Linked List is empty, then make the new
        node as head and tail */
    if (linkedList.head == NULL)
    {
        new_node->prev = NULL;
        linkedList.head = new_node;
        linkedList.tail = new_node;
        return;
    }

    /* Else traverse till the last node */
    while (last->next != NULL)
        last = last->next;
    /*  Change the next of last node */
    last->next = new_node;
    /* Make last node as previous of new node */
    new_node->prev = last;
    /*  put the new node in the tail*/
    linkedList.tail = last->next;


}
//=>Complexity: O(n)

IteratedList::~IteratedList() {
    DLLNode* current = this->linkedList.head;
    while (current != nullptr) {
        DLLNode* next = current->next;
        delete current;
        current = next;
    }
} //Complexity: O(n)


