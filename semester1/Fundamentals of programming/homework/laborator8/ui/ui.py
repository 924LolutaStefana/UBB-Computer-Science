from service.service import *


class UI:
    def __init__(self, service=None):
        if service is None:
            service = Service()
        self._service = service

    @staticmethod
    def list_books(books):

        for book in books:
            print(book)

    def ui_add(self):
        try:
            isbn= input("Input the isbn of the book> ")
            author= input("Input the author of the book>  ")
            title = input("Input the author of the book>  ")
            self._service.add_book(isbn,author,title)
            print("Book successfully added!")
        except Exception as e:
            print(e)

    def ui_remove(self):
        try:
            given_word=input("Give the starting word of the title> ")
            self._service.remove_book(given_word)
            print("Books successfully removed!")
        except Exception as e:
            print(e)
    @staticmethod

    def _print_menu():
        print("\n0. Exit")
        print("1. Add a book")
        print("2. List all the books")
        print("3. Filter the list so that book titles starting with a given word are deleted from the list.")
        print("4. Undo")
    def ui_undo(self):
        try:

            self._service.undo_operation()
            print("Did undo!")
        except Exception as e:
            print(e)

    def start(self):

        while True:
            self._print_menu()
            userchoice = input("> ")
            if userchoice == "0":
               break
            elif userchoice == "1":
                self.ui_add()
            elif userchoice == "2":
                self.list_books(self._service.get_books())
            elif userchoice == "3":
                self.ui_remove()
            elif userchoice=="4":
                self.ui_undo()




