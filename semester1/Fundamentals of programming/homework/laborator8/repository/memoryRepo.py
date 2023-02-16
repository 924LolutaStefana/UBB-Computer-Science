from domain.Book import Book
from exceptions.repoError import *


class MemoryRepo:
    def __init__(self):
        self._books = self._generate_books()
        self._list_history = {}
        self._count = 0
        self._list_history[self._count] = self._generate_books()
        self._count = self._count + 1

    def add_book(self, book):
        """
        Function that adds a book to the list of books
        :param book: list
        :return:
        """
        isbn = book.get_isbn()

        if isbn in self._books:
            raise RepoError("Isbn should be unique!")
        self._books.append(book)
        self._list_history[self._count] = []
        for current in self._books:
            self._list_history[self._count].append(current)
        self._count = self._count + 1

    def delete_book(self, given_word):
        if len(self._books) == 0:
            raise RepoError("There is no book to remove!")
        for book in self._books:
            if given_word == book.get_title():
                self._books.remove(book)
        self._list_history[self._count] = []
        for current in self._books:
            self._list_history[self._count].append(current)
        self._count = self._count + 1

    def get_all(self):

        return self._books

    def size(self):
        return len(self._books)

    def _generate_books(self):

        return [Book("A34B", "Stephen Edwin King", "Carrie"),Book("B7G9", "Adam Silvera", "They both die at the end"),
                Book("A35B","Bimal Jalal","The India Story"),Book("G5TR","	Shashi Tharoor","Pride, Prejudice and Punditry"),
                Book("FD56", "Amrita Pritam", "Death of a City"),Book("HBN7", " Erica Jong", "Fear of Flying"),Book("MG79", "Paul Kalanithi", "When Breath Becomes Air")
                , Book("XFH4", " Mark Twain", "The Adventures of Tom Sawyer"),Book("QWE2", "Stephen Edwin King", "Carrie"),Book("MJ98", "Nathaniel Philbrick", "In the Heart of the Sea")]

    def clean_memory(self):
        self._books = []
        return self._books

    def undo_operation(self):
        if self._count > 1:
            self._list_history.pop(self._count - 1)
            self._books = []
            self._books = self._list_history[self._count - 2]
            self._count -= 1
            return True
        return False

