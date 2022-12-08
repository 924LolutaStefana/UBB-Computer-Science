def create_product(name,price,quantity):
    """
    Function that creates a product
    :param name: string
    :param price: int
    :param quantity: int
    :return: the product
    """
    return [name,price,quantity]
def get_name(product):
    return product[0]
def get_price(product):
    return product[1]
def get_quantity(product):
    return product[2]
def set_name(product,name):
    product[0]=name
def set_price(product,price):
    product[1]=price
def set_quantity(product,quantity):
    product[2]=quantity
def toString(product):
    """
    to string function
    :param product: list
    :return: string version
    """
    return "name: "+str(product[0])+",price: "+str(product[1])+",quantity: "+str(product[2])
def add_product(products,name,price,quantity):
    """
    Function that adds a product to the list of products
    :param products: list
    :param name: string
    :param price: int
    :param quantity: int
    :return:
    """
    product=create_product(name,price,quantity)
    if price<0 or quantity<0:
        raise ValueError("The numbers have to be positive..")
    products.append(product)
def sort_descending(products):
    products1=products
    products1.sort(key=get_name, reverse=True)
    return products1
def remove_product(products,name):
    ok=0
    for product in products:
        if get_name(product)==name:
            ok=1
            products.remove(product)
    if ok==0:
        raise ValueError("Product does not exist!")
def calculate_value(products):
    value=0
    for product in products:
        value=value+get_quantity(product)*get_price(product)
    return value
