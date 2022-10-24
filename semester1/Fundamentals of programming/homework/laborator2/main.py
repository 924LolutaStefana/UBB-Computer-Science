import random

def gnomeSort(list, n, step):
    """
    This function sorts the list using the Gnome Sort algorithm
    :param list: list of numbers
    :param n: length of the list, integer
    :param step: the step provided by the user, integer
    :return: the sorted list
    """
    index = 0
    current_step=0
    #If you are at the start of the array then go to the right element (from arr[0] to arr[1]).
    #If the current array element is larger or equal to the previous array element then go one step right
    # If the current array element is smaller than the previous
    # array element then swap these two elements and go one step backwards
    # repeat until it reaches the end of the array
    while index < n:
        if index == 0:
            index = index + 1
        if list[index] >= list[index - 1]:
            index = index + 1
        else:
            current_step=current_step+1
            list[index], list[index - 1] = list[index - 1], list[index]
            if current_step == step:
                print(*list)
                current_step=0
            index = index - 1



def cocktailSort(list, step):
    """
    This function sorts the list using the Cocktail Sort algorithm
    :param list: the list
    :param step: the step provided by the user, integer
    :return: the sorted list
    """
    # The first stage loops through the array from left to right,
    # just like the Bubble Sort. During the loop,
    # adjacent items are compared and if value on the left is greater
    # than the value on the right, then values are swapped.
    # At the end of first iteration, largest number will reside at the end of the array.
    # The second stage loops through the array in opposite direction-
    # starting from the item just before the most recently sorted item,
    # and moving back to the start of the array. Here also, adjacent items
    # are compared and are swapped if required.
    n = len(list)
    current_step=0
    swapped = True
    start = 0
    end = n - 1
    while (swapped == True):
        swapped = False
        for i in range(start, end):
            if (list[i] > list[i + 1]):
                current_step = current_step + 1
                list[i], list[i + 1] = list[i + 1], list[i]

                swapped = True
            if current_step == step:
                print(*list)
                current_step = 0
        if (swapped == False):
            break
        swapped = False
        end = end - 1
        for i in range(end - 1, start - 1, -1):
            if (list[i] > list[i + 1]):
                list[i], list[i + 1] = list[i + 1], list[i]
                current_step = current_step + 1

                swapped = True
            if current_step == step:
                print(*list)
                current_step = 0
        start = start + 1




def generate_list():
    """
    This function generates a list of random numbers
    :return: the new list
    """
    count = int(input("How many nr to generate? "))  # int() converts to integer

    list_of_numbers = [2,32,45,5,34,12,12,32,4,5,55,43,2,4,5,6,8,8,87,67,35,89,76,23]  # list()

    result = []

    for i in range(count):
        number = random.randint(0, len(list_of_numbers) - 1)
        result.append(number)

    # Let's print out the names
    print(result)
    return result
def test_functions():
    """
    This function tests all the methods int his project
    :return:
    """
    list=[1,3,1,6,3,7,2]
    cocktailSort(list,0)
    assert list==[1,1,2,3,3,6,7]
    print("Cocktail sort works!")
    list = [1, 3, 1, 6, 3, 7, 2]
    gnomeSort(list, len(list),0)
    assert list == [1, 1, 2, 3, 3, 6, 7]
    print("Gnome sort works!")
def start():
    list = []
    while True:
        step=0
        print("________________________________________________")
        print("|1. Generate a list of n random natural numbers |")
        print("|2. Sort the list using cocktail sort           |"  )
        print("|3. Sort the list using gnome sort              |" )
        print("|4. Test the functions                          |")
        print("|0. Exit                                        |")
        print("|_______________________________________________|")

        opt = input("Give an option>")


        if opt == "1":
            list = generate_list()
        elif opt == "2":
            additional_list=list
            step=int(input("Please provide a step>"))
            cocktailSort(additional_list,step)
            print("The final list sorted with cocktail sort is: ", *additional_list)


        elif opt == "3":
            additional_list_2= list
            step_a = int(input("Please provide a step>"))
            gnomeSort(additional_list_2,len(additional_list_2),step_a)
            print("The final list sorted with gnome sort is: ", *additional_list_2)

        elif opt == "4":
            test_functions()

        elif opt == "0":
            return
        else:
            print("Bad command or file name")

start()