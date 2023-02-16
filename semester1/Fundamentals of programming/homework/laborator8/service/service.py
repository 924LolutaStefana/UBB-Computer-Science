from domain.Book import Book
from exceptions.serviceError import *
from repository.binaryRepo import BinaryRepo
from repository.fileRepo import FileRepo
from repository.memoryRepo import *


class Service:
    def __init__(self,repo=None):
        self._repo=repo
        if repo is None:
            self._repo= MemoryRepo()
            #self._repo = FileRepo("books.txt")
            #self._repo = BinaryRepo("books1.bin")

    def get_books(self):
        return self._repo.get_all()

    def add_book(self, isbn,author,title):
        book=Book(isbn,author,title)

        self._repo.add_book(book)
    def remove_book(self,given_word):

        self._repo.delete_book(given_word)

    def undo_operation(self):
        if not self._repo.undo_operation():
            raise ValueError("You cant undo anymore")
        #self._repo.undo_operation()








