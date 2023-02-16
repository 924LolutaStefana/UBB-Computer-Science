class Book:
    def __init__(self, isbn, author, title):
        self.__isbn = isbn
        self.__author = author
        self.__title = title

    def get_isbn(self):
        return self.__isbn

    def get_author(self):
        return self.__author

    def get_title(self):
        return self.__title

    def __str__(self):
        return f" {self.__isbn},{self.__author},{self.__title} "


def test_book():
    book = Book("A34B", "Stephen Edwin King", "Carrie")
    assert book.get_isbn() == "A34B"
    assert book.get_author()=="Stephen Edwin King"
    assert book.get_title()=="Carrie"

test_book()


