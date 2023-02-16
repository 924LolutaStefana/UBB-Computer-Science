from repository.fileRepo import *
from tests import *
from ui.ui import UI


tests=Tests()
tests.run_all()
print("Tests done! :)")
program = UI()
program.start()
