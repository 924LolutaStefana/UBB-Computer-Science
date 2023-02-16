import random

from board.board import *
from strategy.strategy import *

class UI:
    def __init__(self, board_service):
        self.board_service = board_service



    @staticmethod
    def print_menu():
        print("Choose between playing:")
        print("1. 1 player(in case you wanna play alone)")
        print("2. 2 players(in case you wanna play with a friend)")
        print("0. Exit")

    @staticmethod
    def check_column_integer(message):
        if message.isdigit():
            column = int(message)
            if 0 < column < 8:
                return True
        return False

    def human_vs_computer(self):
        circle_1 = Circle('●')
        circle_2 = Circle('○')
        name = input("Enter your name: ")
        player1 = Player(name, circle_1)
        player2 = Player('Computer', circle_2)
        board = Board()
        print(player1)
        print(player2)
        print(board)
        while self.board_service.is_draw(board) is False:
            column1 = input(player1.get_name + ", choose the column for your move: ")
            while self.check_column_integer(column1) is False:
                column1 = input("Invalid column number, please choose a number between 1 and 7: ")
            column1 = int(column1)
            column1 -= 1
            while self.board_service.move(board, player1.get_circle, column1) is False:
                column1 = input("Please choose a column which is not full: ")
                while self.check_column_integer(column1) is False:
                    column1 = input("Invalid column number, please choose a number between 1 and 7: ")
                column1 = int(column1)
                column1 -= 1
            print(board)
            if self.board_service.is_game_won(board) is False:
                print("The computer is choosing his next move")
                column =random.randint(1,7)

                column = int(column)
                column -= 1
                while self.board_service.move(board, player2.get_circle, column) is False:
                    column =random.randint(1,7)

                    column = int(column)
                    column1 -= 1
                print(board)
                if self.board_service.is_game_won(board) is True:
                    print("The computer won!")
                    break
                continue
            print("{} won!".format(player1.get_name))
            break
        if self.board_service.is_draw(board) is True:
            print("Draw!")

    def human_vs_human(self):
        circle1 = Circle("●")
        circle2 = Circle("○")
        name1 = input("Enter the name for the first player: ")
        name2 = input("Enter the name for the second player: ")
        player1 = Player(name1, circle1)
        player2 = Player(name2, circle2)
        board = Board()
        print(player1)
        print(player2)
        print(board)
        while self.board_service.is_draw(board) is False:
            column1 = input("{}, choose the column for your move: ".format(player1.get_name))
            while self.check_column_integer(column1) is False:
                column1 = input("Invalid column number, please choose a number between 1 and 7: ")
            column1 = int(column1)
            column1 -= 1
            while self.board_service.move(board, player1.get_circle, column1) is False:
                column1 = input("Please choose a column which is not full: ")
                while self.check_column_integer(column1) is False:
                    column1 = input("Invalid column number, please choose a number between 1 and 7: ")
                column1 = int(column1)
                column1 -= 1
            print(board)
            if self.board_service.is_game_won(board) is False:
                column2 = input("{}, choose the column for your move: ".format(player2.get_name))
                while self.check_column_integer(column2) is False:
                    column2 = input("Invalid column number, please choose a number between 1 and 7: ")
                column2 = int(column2)
                column2 -= 1
                while self.board_service.move(board, player2.get_circle, column2) is False:
                    column2 = input("Please choose a column which is not full: ")
                    while self.check_column_integer(column2) is False:
                        column2 = input("Invalid column number, please choose a number between 1 and 7: ")
                    column2 = int(column2)
                    column2 -= 1
                print(board)
                if self.board_service.is_game_won(board) is True:
                    print("{} won!".format(player2.get_name))
                    break
                continue
            print("{} won!".format(player1.get_name))
            break
        if self.board_service.is_draw(board) is True:
            print("Draw!")

    def menu(self):

        while True:
            UI.print_menu()
            option = input("Choose an option: ")
            if option== '1':
                self.human_vs_computer()
            elif option == '2':

                self.human_vs_human()
            elif option == '0':
                break

