from functions import *
def tests():
    products=[]
    add_product(products,"LALA",34,56)
    assert get_name(products[0])=="LALA"
    assert get_price(products[0])==34
    assert get_quantity(products[0])==56
    try:
        add_product(products, "LALA", -45, 7)
        assert False
    except ValueError as ve:
        assert str(ve)=="The numbers have to be positive.."
