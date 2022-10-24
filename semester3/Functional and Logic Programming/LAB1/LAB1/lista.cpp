#include "lista.h"
#include <iostream>

using namespace std;


PNod creare_rec() {
    TElem x;
    cout << "x=";
    cin >> x;
    if (x == 0)
        return NULL;
    else {
        PNod p = new Nod();
        p->e = x;
        p->urm = creare_rec();
        return p;
    }
}

Lista creare() {
    Lista l;
    l._prim = creare_rec();
    return l;
    ;
}

void tipar_rec(PNod p) {
    if (p != NULL) {
        cout << p->e << " ";
        tipar_rec(p->urm);
    }
}

void tipar(Lista l) {
    tipar_rec(l._prim);
}

void distrug_rec(PNod p) {
    if (p != NULL) {
        distrug_rec(p->urm);
        delete p;
    }
}

void distrug(Lista l) {
    //se elibereaza memoria alocata nodurilor listei
    distrug_rec(l._prim);
}
bool search_rec(PNod p, TElem elem) {
    //we create an aditional function that checks if a given element is present
    //in the list
    if (p == NULL) 
        return false;
    
    if (p->e == elem)
        return true;
    search_rec(p->urm,elem);
   
}

bool test_inclusion_rec(PNod p1, PNod p2) {
    if (p1 == NULL) 
        return true;
    if (search_rec(p2, p1->e) == false)
        return false;
    test_inclusion_rec(p1->urm, p2->urm);
}
bool test_inclusion(Lista l1, Lista l2) {
    return test_inclusion_rec(l1._prim,l2._prim );
}
PNod elimin_rec(PNod p, TElem e) {
    if (p == NULL) {
        return NULL;
        
    }
    else if (p->e == e) {
        return elimin_rec(p->urm, e);
    }
    else
    {
        p->urm= elimin_rec(p->urm, e);
        return p;
    }

    
}

Lista elimin(Lista l, TElem e) {
    
     elimin_rec(l._prim,e);
     return l;
}
