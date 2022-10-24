import random
import time
def gnomeSort(list, n):
    """
    This function sorts the list using the Gnome Sort algorithm
    :param list: list of numbers
    :param n: length of the list, integer

    :return: the sorted list
    """
    index = 0

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

            list[index], list[index - 1] = list[index - 1], list[index]

            index = index - 1



def cocktailSort(list):
    """
    This function sorts the list using the Cocktail Sort algorithm
    :param list: the list

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

    swapped = True
    start = 0
    end = n - 1
    while (swapped == True):
        swapped = False
        for i in range(start, end):
            if (list[i] > list[i + 1]):

                list[i], list[i + 1] = list[i + 1], list[i]

                swapped = True

        if (swapped == False):
            break
        swapped = False
        end = end - 1
        for i in range(end - 1, start - 1, -1):
            if (list[i] > list[i + 1]):
                list[i], list[i + 1] = list[i + 1], list[i]


                swapped = True

        start = start + 1




def generate_list():
    """
    This function generates a list of random numbers
    :return: the new list
    """
    count = int(input("How many nr to generate? "))

    list_of_numbers = [2,32,45,5,34,12,12,32,4,5,55,43,2,4,5,6,8,8,87,67,35,89,76,23]

    result = []

    for i in range(count):
        number = random.randint(0, len(list_of_numbers) - 1)
        result.append(number)
    print(result)
    return result
def generate_best(list1,list2,list3,list4,list5):

    list1.append(random.randint(0,5))
    list2.append(random.randint(0, 5))
    list3.append(random.randint(0, 5))
    list4.append(random.randint(0, 5))
    list5.append(random.randint(0, 5))
    n = random.randint(5, 10)

    for i in range(n - 1):
        list1.append(list1[i] + random.randint(1, 5))
    for i in range(n*2 - 1):
        list2.append(list2[i] + random.randint(1, 5))
    for i in range(n*4 - 1):
        list3.append(list3[i] + random.randint(1, 5))
    for i in range(n*8 - 1):
        list4.append(list4[i] + random.randint(1, 5))
    for i in range(n*16 - 1):
        list5.append(list5[i] + random.randint(1, 5))


def generate_worst(list1, list2, list3, list4, list5):
    list1.append(random.randint(10000,20000))
    list2.append(random.randint(10000,20000))
    list3.append(random.randint(10000,20000))
    list4.append(random.randint(10000,20000))
    list5.append(random.randint(10000,20000))
    n = random.randint(100,200)

    for i in range(n - 1):
        list1.append(list1[i] -random.randint(1, 100))
    for i in range(n * 2 - 1):
        list2.append(list2[i] - random.randint(1, 100))
    for i in range(n * 4 - 1):
        list3.append(list3[i] - random.randint(1, 100))
    for i in range(n * 8 - 1):
        list4.append(list4[i] - random.randint(1, 100))
    for i in range(n * 16 - 1):
        list5.append(list5[i] - random.randint(1, 100))

def generate_average(list1, list2, list3, list4, list5):
    list1.append(random.randint(1,300))
    list2.append(random.randint(1,300))
    list3.append(random.randint(1,300))
    list4.append(random.randint(1,300))
    list5.append(random.randint(1,300))
    n = random.randint(5, 10)

    for i in range(n - 1):
        list1.append(random.randint(1,300))
    for i in range(n * 2 - 1):
        list2.append(random.randint(1,300))
    for i in range(n * 4 - 1):
        list3.append( random.randint(1,300))
    for i in range(n * 8 - 1):
        list4.append( random.randint(1,300))
    for i in range(n * 16 - 1):
        list5.append(random.randint(11,300))
def best_case_gnome():

    list1 = []
    list2 = []
    list3 = []
    list4 = []
    list5 = []

    generate_best(list1, list2, list3, list4, list5)
    #print("LIST1: ", *list1)
    #print("LIST2: ", *list2)
    #print("LIST3: ", *list3)
    #print("LIST4: ", *list4)
    #print("LIST5: ", *list5)
    print("Sorting first list...waiting")
    start = time.time()
    gnomeSort(list1,len(list1))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting second list...waiting")
    start = time.time()
    gnomeSort(list2, len(list2))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting third list...waiting")
    start = time.time()
    gnomeSort(list3, len(list3))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fourth list...waiting")
    start = time.time()
    gnomeSort(list4, len(list4))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fifth list...waiting")
    start = time.time()
    gnomeSort(list5, len(list5))
    print("--- %s seconds ---" % (time.time() - start))
def best_case_cocktail():

    list1 = []
    list2 = []
    list3 = []
    list4 = []
    list5 = []

    generate_best(list1, list2, list3, list4, list5)
    #print("LIST1: ", *list1)
    #print("LIST2: ", *list2)
    #print("LIST3: ", *list3)

    #print("LIST4: ", *list4)
    #print("LIST5: ", *list5)
    print("Sorting first list...waiting")
    start = time.time()
    cocktailSort(list1)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting second list...waiting")
    start = time.time()
    cocktailSort(list2)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting third list...waiting")
    start = time.time()
    cocktailSort(list3)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fourth list...waiting")
    start = time.time()
    cocktailSort(list4)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fifth list...waiting")
    start = time.time()
    cocktailSort(list5)

    print("--- %s seconds ---" % (time.time() - start))

def worst_case_cocktail():

    list1 = []
    list2 = []
    list3 = []
    list4 = []
    list5 = []

    generate_worst(list1, list2, list3, list4, list5)
    #print("LIST1: ", *list1)
    #print("LIST2: ", *list2)
    #print("LIST3: ", *list3)
    #print("LIST4: ", *list4)
    #print("LIST5: ", *list5)
    print("Sorting first list...waiting")
    start = time.time()
    cocktailSort(list1)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting second list...waiting")
    start = time.time()
    cocktailSort(list2)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting third list...waiting")
    start = time.time()
    cocktailSort(list3)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fourth list...waiting")
    start = time.time()
    cocktailSort(list4)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fifth list...waiting")
    start = time.time()
    cocktailSort(list5)
    print("--- %s seconds ---" % (time.time() - start))

def worst_case_gnome():

    list1 = []
    list2 = []
    list3 = []
    list4 = []
    list5 = []

    generate_worst(list1, list2, list3, list4, list5)
    #print("LIST1: ", *list1)
    #print("LIST2: ", *list2)
    #print("LIST3: ", *list3)
    #print("LIST4: ", *list4)
    #print("LIST5: ", *list5)
    #print("Sorting first list...waiting")
    start = time.time()
    gnomeSort(list1, len(list1))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting second list...waiting")
    start = time.time()
    gnomeSort(list2, len(list2))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting third list...waiting")
    start = time.time()
    gnomeSort(list3, len(list3))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fourth list...waiting")
    start = time.time()
    gnomeSort(list4, len(list4))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fifth list...waiting")
    start = time.time()
    gnomeSort(list5, len(list5))
    print("--- %s seconds ---" % (time.time() - start))
def average_case_cocktail():

    list1 = []
    list2 = []
    list3 = []
    list4 = []
    list5 = []

    generate_average(list1, list2, list3, list4, list5)
    #print("LIST1: ", *list1)
    #print("LIST2: ", *list2)
    #print("LIST3: ", *list3)
    #print("LIST4: ", *list4)
    #print("LIST5: ", *list5)
    print("Sorting first list...waiting")
    start = time.time()
    cocktailSort(list1)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting second list...waiting")
    start = time.time()
    cocktailSort(list2)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting third list...waiting")
    start = time.time()
    cocktailSort(list3)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fourth list...waiting")
    start = time.time()
    cocktailSort(list4)
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fifth list...waiting")
    start = time.time()
    cocktailSort(list5)
    print("--- %s seconds ---" % (time.time() - start))
def average_case_gnome():

    list1 = []
    list2 = []
    list3 = []
    list4 = []
    list5 = []

    generate_average(list1, list2, list3, list4, list5)
    #print("LIST1: ", *list1)
    #print("LIST2: ", *list2)
    #print("LIST3: ", *list3)
    #print("LIST4: ", *list4)
    #print("LIST5: ", *list5)
    #print("Sorting first list...waiting")
    start = time.time()
    gnomeSort(list1, len(list1))

    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting second list...waiting")
    start = time.time()
    gnomeSort(list2, len(list2))

    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting third list...waiting")
    start = time.time()
    gnomeSort(list3, len(list3))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fourth list...waiting")
    start = time.time()
    gnomeSort(list4, len(list4))
    print("--- %s seconds ---" % (time.time() - start))
    print("Sorting fifth list...waiting")
    start = time.time()
    gnomeSort(list5, len(list5))

    print("--- %s seconds ---" % (time.time() - start))
def program():
    list = []
    while True:
        step=0
        print("________________________________________________")
        print("|1. Generate a list of n random natural numbers |")
        print("|2. Sort the list using cocktail sort           |"  )
        print("|3. Sort the list using gnome sort              |" )
        print("|4. Best case                                   |")
        print("|5. Average case                                |")
        print("|6. Worst case                                  |")
        print("|0. Exit                                        |")
        print("|_______________________________________________|")

        opt = input("Give an option>")


        if opt == "1":
            list = generate_list()
        elif opt == "2":
            cocktailSort(list)
            print("The final list sorted with cocktail sort is: ", list)
        elif opt == "3":

            gnomeSort(list,len(list))
            print("The final list sorted with gnome sort is: ", *list)

        elif opt == "4":
            option = input("Cocktail or Gnome?")
            if option=="Cocktail":
                best_case_cocktail()
            elif option=="Gnome":
                best_case_gnome()
            else:
                print("Invalid choice")


        elif opt == "5":
            option = input("Cocktail or Gnome?")
            if option == "Cocktail":
                average_case_cocktail()
            elif option == "Gnome":
                average_case_gnome()
            else:
                print("Invalid choice")
        elif opt == "6":
            option = input("Cocktail or Gnome?")
            if option == "Cocktail":
                worst_case_cocktail()
            elif option == "Gnome":
                worst_case_gnome()
            else:
                print("Invalid choice")

        elif opt == "0":
            return
        else:
            print("Bad command or file name")

program()