class MyExcepion(Exception):
    pass
class NumericalValueCheck(Exception):
    pass
def check_numerical(nr):
    try:
        nr = int(nr)
    except ValueError:
        raise ValueError("Please insert numerical values only")

