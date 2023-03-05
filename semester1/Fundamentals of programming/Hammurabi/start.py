from src.tests import Test
from src.ui import *
from src.service import *
from src.domain import *

test = Test()
test.runAllTests()
print("All tests passed! :)\n")
start_year = Year()
print(start_year)
service = Service(start_year)
ui = UI(service)
ui.start()




