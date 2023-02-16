import pickle

from repository.memoryRepo import *


class BinaryRepo(MemoryRepo):
    def __init__(self,file_path):
        MemoryRepo.__init__(self)

        self.__file_path = file_path

    def __read_all_from_file(self):
        with open(self.__file_path, 'rb') as f:
            self._books = pickle.load(f)
            f.close()

    def __write_all_to_file(self):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(super().get_all(), f)
            f.close()

    def size(self):
        self.__read_all_from_file()
        return MemoryRepo.get_all(self)

    def add_book(self, book):

        MemoryRepo.add_book(self, book)
        self.__write_all_to_file()

    def delete_book(self, given_word):

        MemoryRepo.delete_book(self, given_word)
        self.__write_all_to_file()

    def  undo_operation(self):

        ok=MemoryRepo.undo_operation(self)
        self.__write_all_to_file()
        return ok


    def get_all(self):

        return MemoryRepo.get_all(self)