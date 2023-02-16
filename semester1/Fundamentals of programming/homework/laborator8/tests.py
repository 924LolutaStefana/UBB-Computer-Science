
from service.service import *
class Tests:
    def tests(self):
        books = Service()
        books.add_book("A34B", "Stephen Edwin King", "Carrie")
        assert books.get_books()[0].get_isbn() == "A34B"
        assert books.get_books()[0].get_author() == "Stephen Edwin King"
        assert books.get_books()[0].get_title() == "Carrie"
        try:
            books.add_book("A34B", "LALA", "LALAL")


        except RepoError as ve:
            assert str(ve) == "Isbn should be unique!"
    def run_all(self):
        self.tests()

