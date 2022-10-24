#pragma once

//tip de data generic (pentru moment este intreg)
typedef int TElem;

//referire a structurii Nod;
struct Nod;

//se defineste tipul PNod ca fiind adresa unui Nod
typedef Nod* PNod;

typedef struct Nod {
	TElem e;
	PNod urm;
};

typedef struct {
	//prim este adresa primului Nod din lista
	PNod _prim;
} Lista;

//operatii pe lista - INTERFATA

//crearea unei liste din valori citite pana la 0
Lista creare();
PNod creare_rec();
//tiparirea elementelor unei liste
void tipar(Lista l);
// destructorul listei
void distrug(Lista l);
bool search_rec(PNod p, TElem elem);
bool test_inclusion_rec(PNod p1, PNod p2);
bool test_inclusion(Lista l1, Lista l2);
PNod elimin_rec(PNod p, TElem e);
Lista elimin(Lista l, TElem e);