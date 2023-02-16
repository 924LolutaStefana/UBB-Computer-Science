import unittest

from board.board import Circle, Board, Player
from strategy.strategy import BoardService


class Tests(unittest.TestCase):
    def test_circle(self):
        circle1 = Circle("●")
        circle2 = Circle("○")
        self.assertEqual(circle1.symbol, "●")
        self.assertEqual(circle2.symbol, "○")
        self.assertEqual(str(circle1), '●')
        self.assertEqual(str(circle2), '○')

    def test_is_game_won(self):
        circle1 = Circle("●")
        circle2 = Circle("○")
        board = Board()
        board_service = BoardService()
        self.assertEqual(board.matrix[0][1], ' ')
        board_service.move(board, circle1, 1)
        board_service.move(board, circle2, 2)
        self.assertEqual(board_service.is_game_won(board), False)
        board_service.move(board, circle1, 1)
        board_service.move(board, circle2, 3)
        self.assertEqual(board_service.is_game_won(board), False)
        board_service.move(board, circle1, 1)
        board_service.move(board, circle2, 4)
        self.assertEqual(board_service.is_game_won(board), False)
        board_service.move(board, circle1, 1)
        self.assertEqual(board_service.is_game_won(board), True)
    def test_is_draw(self):
        circle1 = Circle("●")
        circle2 = Circle("○")
        board = Board()
        board_service = BoardService()

        board_service.move(board, circle1, 0)
        self.assertEqual(board_service.is_draw(board), False)
        board_service.move(board, circle1, 0)
        board_service.move(board, circle2, 0)
        board_service.move(board, circle1, 0)
        board_service.move(board, circle1, 0)
        board_service.move(board, circle2, 0)
        board_service.move(board, circle2, 0)

        board_service.move(board, circle1, 1)
        board_service.move(board, circle2, 1)
        board_service.move(board, circle1, 1)
        board_service.move(board, circle1, 1)
        board_service.move(board, circle2, 1)
        board_service.move(board, circle2, 1)

        board_service.move(board, circle1, 2)
        board_service.move(board, circle2, 2)
        board_service.move(board, circle1, 2)
        board_service.move(board, circle1, 2)
        board_service.move(board, circle2, 2)
        board_service.move(board, circle2, 2)

        board_service.move(board, circle1, 3)
        board_service.move(board, circle2, 3)
        board_service.move(board, circle1, 3)
        board_service.move(board, circle1, 3)
        board_service.move(board, circle2, 3)
        board_service.move(board, circle2, 3)

        board_service.move(board, circle1, 4)
        board_service.move(board, circle2, 4)
        board_service.move(board, circle1, 4)
        board_service.move(board, circle1, 4)
        board_service.move(board, circle2, 4)
        board_service.move(board, circle2, 4)

        board_service.move(board, circle1, 5)
        board_service.move(board, circle2, 5)
        board_service.move(board, circle1, 5)
        board_service.move(board, circle1, 5)
        board_service.move(board, circle2, 5)
        board_service.move(board, circle2, 5 )

        board_service.move(board, circle1, 6)
        board_service.move(board, circle2, 6)
        board_service.move(board, circle1, 6)
        board_service.move(board, circle1, 6)
        board_service.move(board, circle2, 6)
        board_service.move(board, circle2, 6)

        self.assertEqual(board_service.is_draw(board), True)

        self.assertEqual(board_service.move(board, circle1, 6), False)

    def test_player(self):
        player1 = Player("Mara", '●')
        player2 = Player("Ana", '○')
        self.assertEqual(player1.get_name, 'Mara')
        self.assertEqual(player2.get_circle, '○')
