def create_expense(nr_apartament,amount,type):
    """
    creates an expense for a apartament, giving its number and type
    :param nr_apartament:int
    :param amount:int
    :param type:string
    :return: the expense or none if the type is invalid
    """
    valid_type=["water","heating","electricity","gas","other"]
    if type  not in valid_type:
        raise ValueError("Not a valid type..")
    return [nr_apartament,amount,type]

def get_nr_apartament(expense):
    """
    getter for the apartament nr
    :param expense: expenses list
    :return:
    """
    return expense[0]
def get_amount(expense):
    """
    getter for the amount
    :param expense:  expenses list
    :return:
    """
    return expense[1]

def get_type(expense):
    """
    getter fot the type
    :param expense:
    :return:
    """
    return expense[2]
def set_nr_apartament(expense,apartament):
    """
    setter for the nr apartament
    :param expense:
    :param apartament:
    :return:
    """
    expense[0]=apartament
def set_amount(expense,amount):
    """
    setter for the amount
    :param expense:
    :param amount:
    :return:
    """
    expense[1]=amount
def set_type(expense,type):
    """
    setter for the type
    :param expense:
    :param type:
    :return:
    """
    expense[2]=type
def toString(expense):
    """
    to string function
    :param expense:list
    :return:
    """
    return "apartament " + str(get_nr_apartament(expense))+", amount "+str(get_amount(expense))+ ", type "+str(get_type(expense))

def add_expense(expenses,nr_apartament,amount,type,history):
    """
    function to add an expense to the expenses list
    :param expenses: list
    :param nr_apartament:integer
    :param amount: integer
    :param type: string
    :return: raises a ValueError "This expense already exists.." if we add an already existing expense
    """
    for expense in expenses:
        if nr_apartament==get_nr_apartament(expense):
            if type==get_type(expense):
                raise ValueError("This expense already exists..")
    history.append(expenses[:])
    expense=create_expense(nr_apartament,amount,type)
    expenses.append(expense)
def remove_apartament_or_type(expenses,to_remove,history):
    """
    This function removes an expense that has an apartament number equal to a given number

    :param expenses: list
    :param apartament: integer
    :param history: a list where we store the list before any modification
    :return: a boolean "found" that indicates if the expense was found in the list or not
    """
    history.append(expenses[:])
    to_be_removed = str(to_remove)
    found = False
    for index in expenses:
        if str(to_be_removed) == str(get_nr_apartament(index)) or str(to_be_removed) == get_type(index):
            found = True
            expenses.remove(index)
    return found

def remove_from_positions(expenses,start,end,history):
    """
    Function that removes expenses from a start position to an end position
    :param expenses: list
    :param start: integer
    :param end: integer
    :param history: a list where we store the list before any modification
    :return: a boolean "found" that indicates if the expense was found in the list or not
    """
    history.append(expenses[:])
    list_to_remove = list(range(start, end + 1))
    expense_found = False
    for current_apartment in list_to_remove:
        for index in expenses:
            if str(current_apartment) == str(get_nr_apartament(index)):
                expenses.remove(index)
                current_apartment += 1
                expense_found = True
    return expense_found
def replace_expense(expenses,nr_apartament,type,amount,history):
    """
    function to replace  an expense
    :param expenses: list
    :param nr_apartament:integer
    :param amount: integer
    :param type: string
    :return: raises a ValueError "This expense doesn't exist.." if we can't find that expense
    """
    history.append(expenses[:])
    expense_found = False
    for expense in expenses:
        if nr_apartament==get_nr_apartament(expense):
            if type==get_type(expense):
                 set_amount(expense, amount)
                 expense_found = True

    return expense_found
def get_sum_expenses(expense_list, apartment_number):
    """
    Computes the total sum of the expenses for an apartment number
    :param expense_list: the list of all expenses
    :param apartment_number: the number of the apartment
    :return: the sum of the expenses for the given apartment
    """
    expense_sum = 0
    for index in range(len(expense_list)):
        if apartment_number == get_nr_apartament(expense_list[index]):
            expense_sum = expense_sum + int(get_amount(expense_list[index]))
    return expense_sum
def filter_expense(expenses,parameter,history):
    """
    Function that keeps only the expenses that have a given type or a given amount
    :param expenses: list
    :param parameter: a string or an integer
    :param history: a list
    :return:
    """
    filter_value = parameter
    if filter_value in ['gas', 'water', 'electricity', 'other', 'heating']:
        history.append(expenses[:])
        for expense in expenses[::-1]:
            if get_type(expense) != str(filter_value):
                expenses.remove(expense)
    else:
        history.append(expenses[:])
        for expense in expenses[::-1]:
            if get_amount(expense) >= int(filter_value):
                expenses.remove(expense)
def undo(expenses,parameters,history):
    """
    Function that makes an undo on the last performed operation
    :param expenses: list
    :param parameters: list of strings
    :param history: list
    :return: raises a value error "Cannot undo anymore" if there are no more operations left to undo
    raises a value error "Invalid command" if there are parameters given
    """
    params = parameters.split()
    if history == []:
        raise ValueError("Cannot undo anymore..")
    else:
        if len(params) == 0:
            expenses.clear()
            last_expense_list = history.pop()
            for expense in last_expense_list:
                expenses.append(expense)
        else:
            raise ValueError("Invalid command..")
def test_create_expense():

    expense1=create_expense(2, 20, 'water')
    assert expense1==[2,20,'water']
    try:
        expense2 = create_expense(2, 20, 'gdrsrd')
        assert False
    except ValueError as ve:
        assert str(ve)=="Not a valid type.."



def test_add_expense():
    expenses=[]
    history=[]
    add_expense(expenses,11,20,'water',history)
    assert get_nr_apartament(expenses[0])==11
    assert get_type(expenses[0])=='water'
    assert get_amount(expenses[0])==20
    try:
        add_expense(expenses, 11, 20, 'water', history)
        assert False
    except ValueError as ve:
        assert str(ve)=="This expense already exists.."
def test_remove_apartament():
    expenses = []
    history = []
    add_expense(expenses, 11, 20, 'water', history)
    remove_apartament_or_type(expenses,11,history)
    assert expenses == []
    add_expense(expenses, 11, 20, 'water', history)
    remove_apartament_or_type(expenses, 'water', history)

    assert expenses==[]
def test_remove_from_positions():
    expenses = []
    history = []
    add_expense(expenses, 1, 20, 'water', history)
    add_expense(expenses, 2, 20, 'water', history)
    add_expense(expenses, 3, 20, 'water', history)
    remove_from_positions(expenses,1,2,history)
    assert expenses[0]==[3,20,'water']
def test_replace_expense():
    expenses = []
    history = []
    add_expense(expenses, 1, 20, 'water', history)
    add_expense(expenses, 2, 20, 'water', history)
    add_expense(expenses, 3, 20, 'water', history)
    replace_expense(expenses,1,'water',200,history)
    assert get_amount(expenses[0])==200
def test_filter_expense():
    expenses = []
    history = []

    add_expense(expenses, 1, 20, 'water', history)
    add_expense(expenses, 2, 20, 'gas', history)
    add_expense(expenses, 3, 20, 'other', history)
    filter_expense(expenses,'water',history)
    assert expenses[0]==[1,20,'water']
def test_undo():
    expenses = []
    history = []
    parameter = ""
    add_expense(expenses, 1, 20, 'water', history)
    undo(expenses,parameter,history)
    add_expense(expenses, 1, 20, 'water', history)

    try:
        undo(expenses, "hgvbh", history)
        assert False
    except ValueError as ve:
        assert str(ve)=="Invalid command.."
    undo(expenses, parameter, history)
    assert expenses == []
    try:
        undo(expenses, parameter, history)
        assert False
    except ValueError as ve:
        assert str(ve) == "Cannot undo anymore.."


def test_all():

    test_create_expense()
    test_add_expense()
    test_remove_apartament()
    test_remove_from_positions()
    test_replace_expense()
    test_filter_expense()
    test_undo()





