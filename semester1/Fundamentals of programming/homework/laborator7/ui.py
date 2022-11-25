from functions import *
def test_all_ui():
    test_all()
    print('\033[92m'"All tests passed! :)"'\033[0m' )

def add_ui(expenses,parameters,history):

    params=parameters.split()
    if len(params)!=3:
        raise ValueError("Wrong number of parameters..")
    nr_apartament=int(params[0].strip())

    amount=int(params[1].strip())
    type = params[2].strip()
    add_expense(expenses,nr_apartament,amount,type,history)
def remove_expense_ui(expenses,parameters,history):
    params = parameters.split()
    if len(params) == 1:

         to_remove = str(params[0].strip())

         removed=remove_apartament_or_type(expenses,to_remove,history)
         if removed==False:
            raise ValueError("This expense doesn't exist..")
    elif len(params)==3:
        start=int(params[0].strip())
        word=str(params[1].strip())
        if word!="to":
            raise ValueError("Incorrect syntax!")
        end=int(params[2].strip())
        removed=remove_from_positions(expenses,start,end,history)
        if removed == False:
            raise ValueError("Those expenses don't exist..")
    else:
        raise ValueError("Wrong number of parameters..")
def replace_expense_ui(expenses,parameters,history):
    params = parameters.split()
    if len(params) == 4:
        nr_apartament=int(params[0].strip())

        type = str(params[1].strip())

        word=str(params[2].strip())
        if word != "with":
            raise ValueError("Incorrect syntax!")
        amount= int(params[3].strip())
        replaced = replace_expense(expenses,nr_apartament,type,amount,history)
        if replaced == False:
            raise ValueError("This expense doesn't exist..")


    else:
        raise ValueError("Wrong number of parameters..")
def list_ui(expenses,parameters,history):
    params = parameters.split()
    if len(params) == 0:
        for expense in expenses:
            print(toString(expense))
    elif len(params)==1:
        apartament=int(params[0].strip())
        for expense in expenses:
            if apartament==get_nr_apartament(expense):
                print(toString(expense))
    elif len(params)==2:
        operator=str(params[0].strip())
        value=int(params[1].strip())
        if operator=='<':
            for expense in expenses:
                if get_sum_expenses(expenses,get_nr_apartament(expense)) < value:
                    print(toString(expense))
        elif operator=='>':
            for expense in expenses:
                if get_sum_expenses(expenses, get_nr_apartament(expense)) > value:
                    print(toString(expense))
        elif operator=='=':
            for expense in expenses:
                if get_sum_expenses(expenses, get_nr_apartament(expense)) == value:
                    print(toString(expense))
        else:
            raise ValueError("Incorrect syntax!")
    else:
        raise ValueError("Wrong number of parameters..")

def filter_expense_ui(expenses,parameters,history):
    params = parameters.split()
    if len(params) != 1:
        raise ValueError("Wrong number of parameters..")
    param= str(params[0].strip())

    filter_expense(expenses,param,history)
def undo_ui(expenses,parameters,history):
    undo(expenses,parameters,history)

def split_command(command):
    params=command.strip().split(' ',1)
    params[0] = params[0].strip().lower()
    return params[0], ' ' if len(params) == 1 else params[1].strip()
def print_menu():
    print("add <apartament> <amount> <type>")
    print("remove <apartament>")
    print("remove <type>")
    print("remove <start> to <end>")
    print("replace <apartament> <type> with <amount>")
    print("list")
    print("list <apartment>")
    print("list [ < | = | > ] <amount>")
    print("filter <type>")
    print("filter <value>")
    print("undo")
    print("exit")
def generate_10_expenses_ui(expenses):
    expenses.append(create_expense(1,20,'water'))
    expenses.append(create_expense(2, 100, 'gas'))
    expenses.append(create_expense(3, 35, 'heating'))
    expenses.append(create_expense(4, 40, 'electricity'))
    expenses.append(create_expense(5, 29, 'other'))
    expenses.append(create_expense(6, 90, 'gas'))
    expenses.append(create_expense(7, 150, 'gas'))
    expenses.append(create_expense(8, 230, 'water'))
    expenses.append(create_expense(9, 48, 'heating'))
    expenses.append(create_expense(10, 120, 'other'))


def start():

    print_menu()
    expenses=[]
    history=[]
    generate_10_expenses_ui(expenses)
    command_dict={'add':add_ui,'remove':remove_expense_ui,'replace':replace_expense_ui,'list':list_ui,'filter':filter_expense_ui,'undo':undo_ui}

    while True:
        choice=input("give the command> ")
        command_word,command_params=split_command(choice)
        if command_word in command_dict:
            try:
                command_dict[command_word](expenses,command_params,history)
            except ValueError as ve:
                print(str(ve))

        elif 'exit'==command_word:
            break
        else:
            print("Invalid command")


