#include <stdexcept>
#include "SortedBagIterator.h"

SortedBagIterator::SortedBagIterator(const SortedBag& sb) : sb{ sb }, stack{ std::stack<int>{} } {
    first();
}

void SortedBagIterator::first() {
    currentNode = sb.root;
    while (currentNode != -1) {
        stack.push(currentNode);
        currentNode = sb.tree[currentNode].getLeftSon();
    }
    if (!stack.empty()) {
        currentNode = stack.top();
    }
    else {
        currentNode = -1;
    }
}

void SortedBagIterator::next() {
    if (!valid()) {
        throw std::runtime_error{ "" };
    }
    int node = stack.top();
    stack.pop();
    if (sb.tree[node].getRightSon() != -1) {
        node = sb.tree[node].getRightSon();
        while (node != -1) {
            stack.push(node);
            node = sb.tree[node].getLeftSon();
        }
    }

    if (!stack.empty()) {
        currentNode = stack.top();
    }
    else {
        currentNode = -1;
    }
}

bool SortedBagIterator::valid() const {
    return currentNode != -1;
}

TComp SortedBagIterator::getCurrent() const {
    if (!valid()) {
        throw std::runtime_error{ "" };
    }
    return sb.tree[currentNode].getValue();
}