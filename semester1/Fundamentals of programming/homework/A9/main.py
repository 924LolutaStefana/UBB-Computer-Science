from console.ui import UI
from strategy.strategy import BoardService

board_service = BoardService()

Game = UI(board_service)
Game.menu()