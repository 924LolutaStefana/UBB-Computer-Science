from domain.Book import *
from repository.memoryRepo import *


class FileRepo(MemoryRepo):
    def __init__(self,file_path):
        MemoryRepo.__init__(self)


        self.__file_path=file_path
    def __read_all_from_file(self):
        with open(self.__file_path,"r")as f:
            lines=f.readlines()
            self._books.clear()
            for line in lines:
                line=line.strip()
                if line!="":
                    parts = line.split(",")
                    isbn = parts[0]
                    author = parts[1]
                    title = parts[2]
                    book = Book(isbn, author, title)
                    self._books.append(book)


    def __write_all_to_file(self):
        with open(self.__file_path, "w") as f:
            for book in self._books:
                f.write(str(book)+"\n")


    def add_book(self,book):
        #self.__read_all_from_file()
        MemoryRepo.add_book(self,book)
        self.__write_all_to_file()
    def delete_book(self,given_word):
        #self.__read_all_from_file()
        MemoryRepo.delete_book(self,given_word)
        self.__write_all_to_file()
    def undo_operation(self):

        ok=MemoryRepo.undo_operation(self)
        self.__write_all_to_file()
        return ok

    def get_all(self):
        #self.__read_all_from_file()
        return MemoryRepo.get_all(self)
    def size(self):
        self.__read_all_from_file()
        return MemoryRepo.get_all(self)
