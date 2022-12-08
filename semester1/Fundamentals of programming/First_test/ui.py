from functions import *


def add_ui(products,parameters):
    params = parameters.split()
    if len(params) != 3:
        raise ValueError("Wrong number of parameters..")
    name = params[0].strip()

    price = int(params[1].strip())
    quantity = int(params[2].strip())

    add_product(products,name,price,quantity)
    print("Product successfully added!")
def remove_ui(products,parameters):
    params = parameters.split()
    if len(params) != 1:
        raise ValueError("Wrong number of parameters..")
    name = params[0].strip()
    remove_product(products,name)
    print("Product successfully removed")

def split_command(command):
    params=command.strip().split(' ',1)
    params[0] = params[0].strip().lower()
    return params[0], ' ' if len(params) == 1 else params[1].strip()
def print_menu():
    print("exit")
    print("add <product_name> <price> <quantity>")
    print("remove <product_name>")
    print("list all")
    print("list total")
def list_ui(products,parameters):
    params = parameters.split()
    products1 = sort_descending(products)
    if len(params) == 1 and params[0]=='all':
        for product in products1:
            print(toString(product))
    elif len(params) == 1 and params[0]=='total':
        value=calculate_value(products)
        print("The total value of the products is ",value)

    else:
        raise ValueError("Incorrect syntax!")


def generate_products(products):
    products.append(create_product("Napkins_Pack_100",50,2))
    products.append(create_product("Stefana:)", 45, 3))
    products.append(create_product("Residence", 60, 4))
    products.append(create_product("Camin", 9, 3))
    products.append(create_product("Pack", 78, 1))

def start():
    products=[]
    generate_products(products)
    print_menu()

    command_dict = {'add': add_ui, 'list' : list_ui, 'remove' : remove_ui}

    while True:
        choice = input("give the command> ")
        command_word, command_params = split_command(choice)
        if command_word in command_dict:
            try:
                command_dict[command_word](products, command_params)
            except ValueError as ve:
                print(str(ve))

        elif 'exit' == command_word:
            break
        else:
            print("Invalid command")
