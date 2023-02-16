


class Circle:
    def __init__(self, symbol):
        """
        Constructor for the circle
        :param symbol: char
        """

        self.symbol = symbol

    @property
    def get_symbol(self):
        return self.symbol

    def __str__(self):

        return  str(self.symbol)


class Board:
    def __init__(self):
        """
        constructor for the board
        """
        self.matrix = [[' ' for x in range(7)] for y in range(6)]

    def __str__(self):

        string = ' | 1   2   3   4   5   6   7 |\n'
        string += ' - - - - - - - - - - - - - - -\n'
        for index1 in range(6):
            for index2 in range(7):
                string += ' | '
                string += str(self.matrix[index1][index2])
            string += ' | \n'
            string += ' - - - - - - - - - - - - - - -\n'
        return string


class Player:
    def __init__(self, name, circle):
        """
        Constructor for the player
        :param name: string
        :param circle: string
        """

        self.name = name
        self.circle = circle

    @property
    def get_circle(self):

        return self.circle

    @property
    def get_name(self):

        return self.name

    def __str__(self):
        return f"{self.name} is playing with:{self.circle}"


