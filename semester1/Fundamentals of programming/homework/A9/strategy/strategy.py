
from board.board import *


class BoardService:
    def is_game_won(self, board):
        """
        function that checks if the game is won by a player
        :param board: Board
        :return:True if the game is won, false otherwise
        """

        for row in range(6):
            for column in range(4):
                if board.matrix[row][column] != ' ':
                    if board.matrix[row][column] == board.matrix[row][column + 1]:
                        if board.matrix[row][column] == board.matrix[row][column + 2]:
                            if board.matrix[row][column] == board.matrix[row][column + 3]:
                                return True
        for column in range(7):
            for row in range(3):
                if board.matrix[row][column] != ' ':
                    if board.matrix[row][column] == board.matrix[row + 1][column]:
                        if board.matrix[row][column] == board.matrix[row + 2][column]:
                            if board.matrix[row][column] == board.matrix[row + 3][column]:
                                return True
        for row in range(3):
            for column in range(4):
                if board.matrix[row][column] != ' ':
                    if board.matrix[row][column] == board.matrix[row + 1][column + 1]:
                        if board.matrix[row][column] == board.matrix[row + 2][column + 2]:
                            if board.matrix[row][column] == board.matrix[row + 3][column + 3]:
                                return True
        for row in range(3):
            column = 6
            while column >= 3:
                if board.matrix[row][column] != ' ':
                    if board.matrix[row][column] == board.matrix[row + 1][column - 1]:
                        if board.matrix[row][column] == board.matrix[row + 2][column - 2]:
                            if board.matrix[row][column] == board.matrix[row + 3][column - 3]:
                                return True
                column -= 1
        return False

    def is_draw(self, board):
        """
        Checks if it is a draw between players
        :param board: Board
        :return: True if it is draw,false otherwise
        """

        for row in range(6):
            for column in range(7):
                if board.matrix[row][column] == ' ':
                    return False
        return True

    def move(self, board, circle, column):
        """
        Functon that makes a move on the board
        :param board: Board
        :param circle: string
        :param column: int
        :return: True if the move was made, false otherwise
        """

        i = 5
        while i > -1:
            if board.matrix[i][column] == ' ':
                board.matrix[i][column] = circle
                return True
            i -= 1
        return False


