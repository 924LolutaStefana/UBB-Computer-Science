#include "SortedBag.h"

SortedBag::SortedBag(Relation r) : root{ -1 }, numberOfElements{ 0 }, capacity{ 16 }, firstFree{ 0 }, r{ r }
{
    tree = new BSTNode[16];
}

void SortedBag::add(TComp e) {
    if (mustBeResized()) {
        resize();
    }
    tree[firstFree].setValue(e);
    tree[firstFree].setLeftSon(-1);
    tree[firstFree].setRightSon(-1);
    int current = root, parent = -1;
    while (current != -1) {
        parent = current;
        if (r(e, tree[current].getValue()))
            current = tree[current].getLeftSon();
        else
            current = tree[current].getRightSon();
    }
    if (root == -1)
        root = firstFree;
    else if (r(e, tree[parent].getValue()))
        tree[parent].setLeftSon(firstFree);
    else
        tree[parent].setRightSon(firstFree);
    changeFirstFree();
    ++numberOfElements;
}

void SortedBag::changeFirstFree() {
    ++firstFree;
    while (firstFree < capacity && !tree[firstFree].isNull())
        ++firstFree;
}

bool SortedBag::remove(TComp e) {
    bool removed = false;
    root = removeRecursively(root, e, removed);
    if (removed)
        --numberOfElements;
    return removed;
}

bool SortedBag::search(TComp e) const {
    int currentNode = root;
    while (currentNode != -1) {
        if (tree[currentNode].getValue() == e)
            return true;
        if (r(e, tree[currentNode].getValue())) {
            currentNode = tree[currentNode].getLeftSon();
        }
        else {
            currentNode = tree[currentNode].getRightSon();
        }
    }
    return false;
}

int SortedBag::nrOccurrences(TComp e) const {
    int currentNode = root;
    int counter = 0;
    while (currentNode != -1) {
        if (tree[currentNode].getValue() == e)
            ++counter;
        if (r(e, tree[currentNode].getValue())) {
            currentNode = tree[currentNode].getLeftSon();
        }
        else {
            currentNode = tree[currentNode].getRightSon();
        }
    }
    return counter;
}

int SortedBag::size() const {
    return numberOfElements;
}

SortedBagIterator SortedBag::iterator() const {
    return SortedBagIterator(*this);
}

bool SortedBag::isEmpty() const {
    return numberOfElements == 0;
}

SortedBag::~SortedBag() {
    delete[] tree;
}

bool SortedBag::mustBeResized() {
    return firstFree >= capacity;
}

void SortedBag::resize() {
    auto* newTree = new BSTNode[capacity * 2];
    for (int i = 0; i < capacity; ++i) {
        newTree[i].setValue(tree[i].getValue());
        newTree[i].setLeftSon(tree[i].getLeftSon());
        newTree[i].setRightSon(tree[i].getRightSon());
    }
    delete[] tree;
    tree = newTree;
    firstFree = capacity;
    capacity *= 2;
}


TComp SortedBag::getMaximum(int startingRoot) {
    int currentNode = startingRoot, maxNode = startingRoot;
    TComp maximum;
    while (currentNode != -1) {
        maximum = tree[currentNode].getValue();
        maxNode = currentNode;
        currentNode = tree[currentNode].getRightSon();
    }
    return maxNode;
}

int SortedBag::removeRecursively(int node, TComp e, bool& removed) {
    if (node == -1)
        return node;
    if (e == tree[node].getValue()) {
        removed = true;
        if (tree[node].getLeftSon() == -1) {
            int rightSon = tree[node].getRightSon();
            tree[node] = BSTNode{ NULL_TCOMP, -1, -1 };
            return rightSon;
        }
        else if (tree[node].getRightSon() == -1) {

            int leftSon = tree[node].getLeftSon();
            tree[node] = BSTNode{ NULL_TCOMP, -1, -1 };
            return leftSon;
        }
        int maxNode = getMaximum(tree[node].getLeftSon());
        tree[node].setValue(tree[maxNode].getValue());
        tree[node].setLeftSon(removeRecursively(tree[node].getLeftSon(), tree[maxNode].getValue(), removed));
    }
    else if (r(e, tree[node].getValue())) {
        tree[node].setLeftSon(removeRecursively(tree[node].getLeftSon(), e, removed));
    }
    else {
        tree[node].setRightSon(removeRecursively(tree[node].getRightSon(), e, removed));
    }
    return node;
}

void SortedBag::filter(Condition cond) {
    int currentNode = root;
    while (currentNode != -1) {
          
        if (cond(tree[currentNode].getValue())) {
            remove(tree[currentNode].getValue());
            currentNode = tree[currentNode].getLeftSon();
           
        }
        else {
            currentNode = tree[currentNode].getRightSon();
        }
    }
}