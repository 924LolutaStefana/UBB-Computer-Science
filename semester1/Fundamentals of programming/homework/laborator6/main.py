import math
import random
from collections import defaultdict


def generate_complex_number(real, imaginary):
    """
    generates a complex number as a list
    :param real:integer
    :param imaginary:integer
    :return:the complex nr
    """
    return [real, imaginary]


def generate_complex_number_as_dictionary(real,imag):
    """
     generates a complex number as a dictionary
    :param real:integer
    :param imag: integer
    :return: the complex nr
    """
    return {"real":real, "imaginary":imag}


def generate_list_of_complex_numbers():
    """
    generates a list of complex numbers represented as lists
    :return: the final list
    """
    list1=[]
    for i in range(0, 10):
        number = generate_complex_number(i+random.randint(-10, 10), i + random.randint(-10, 10))
        list1.append(number)
    return list1


def generate_dictionary_of_complex_numbers():
    """
    generates a list of complex numbers represented as dictionaries
    :return: the final list
    """
    dict = []
    for i in range(0, 10):
        number = generate_complex_number_as_dictionary(i+random.randint(-10, 10), i + random.randint(-10, 10))
        dict.append(number)
    return dict

def get_real_part_of_complex_number(complex_number):
    """
    getter for the real part
    :param complex_number:a complex nr
    :return: real part
    """
    return complex_number["real"]


def get_imaginary_part_of_complex_number(complex_number):
    """
    getter for the imag part
    :param complex_number:a complex nr
    :return: the imag part
    """
    return complex_number["imaginary"]

def get_real_part_of_complex_number(complex_number):
    """
    getter for the real part
    :param complex_number: complex nr
    :return: real part
    """
    return complex_number[0]


def get_imaginary_part_of_complex_number(complex_number):
    """
    getter for the imag part
    :param complex_number: complex nr
    :return: imag part
    """
    return complex_number[1]

def toStr(complex_number):
    """
    to str function
    :param complex_number: a complex nr
    :return: the list as a string

    """

    return str(get_real_part_of_complex_number(complex_number)) + "+" + str(
        get_imaginary_part_of_complex_number(complex_number)) + "i"




def print_list(complex_numbers):
    """
    prints the list of complex numbers
    :param complex_numbers: list of complex nrs
    :return:
    """
    for complex in complex_numbers:
        print(toStr(complex))



def largest_subarray(a):
    """
    A function that computes the length of the  longest subarray of numbers having increasing modulus
    :param a:
    :return:
    """
    start_position = 0
    max_contour = 1
    current_contour = 1
    for position in range(1, len(a)):
        if a[position] ==a[position-1]:
            current_contour += 1
        else:
            if current_contour > max_contour:
                max_contour = current_contour
                start_position = position - max_contour
            current_contour = 1
    if current_contour > max_contour:
        max_contour = current_contour
        start_position = len(a) - max_contour

    return start_position,max_contour
def compute_modulus(real,imag):
    """
    computes the modulus of a complex number
    :param real:
    :param imag:
    :return:
    """

    return math.sqrt(real ** 2 + imag ** 2)
def compute_list_of_moduluses(complex_numbers):
    """
    computes a list of moduluses of our complex number list
    :param complex_numbers:
    :return:
    """
    complex_numbers_modulus=[]
    for i in range (len(complex_numbers)):
        modulus=compute_modulus(get_real_part_of_complex_number(complex_numbers[i]),get_imaginary_part_of_complex_number(complex_numbers[i]))
        complex_numbers_modulus.append(modulus)
    return complex_numbers_modulus


def lis(complex_numbers):
    """
    computes the length of the longest increasing subsequence when considering each number's real part
    :param complex_numbers:
    :return:
    """
    n = len(complex_numbers)

    lis = [1] * n

    for i in range(1, n):
        for j in range(0, i):
            if get_real_part_of_complex_number(complex_numbers[i]) > get_real_part_of_complex_number(complex_numbers[j]) and lis[i] < lis[j] + 1:
                lis[i] = lis[j] + 1

    maximum = 0

    for i in range(n):
        maximum = max(maximum, lis[i])

    return maximum
def start():
    complex_numbers = generate_list_of_complex_numbers()
    #complex_numbers=generate_dictionary_of_complex_numbers()
    while True:

        print("1. Generate list of complex numbers")
        print("2. Print complex number")
        print("3. Length and elements of a longest subarray of numbers having the same modulus modulus.//naive implementation")
        print("4. The length and elements of a longest increasing subsequence, when considering each number's real part//dynammic")
        print("0. Exit")
        choice = int(input("Enter your choice> "))
        if choice == 1:
            real1 = int(input("Enter real part:> "))
            imaginary1 = int(input("Enter imaginary part:> "))
            number=generate_complex_number(real1, imaginary1)
            #number=generate_complex_number_as_dictionary(real1,imaginary1)
            complex_numbers.append(number)

        elif choice == 2:

            print_list(complex_numbers)
        elif choice == 3:
            modulus=compute_list_of_moduluses(complex_numbers)
            startpos=largest_subarray(modulus)[0]
            maxcont=largest_subarray(modulus)[1]
            for number in range(startpos, (startpos + maxcont)):
                print(toStr(complex_numbers[number]), end=" ")
            print("\n longest subarray with increasing modulo having the length " + str(maxcont) + "\n")

        elif choice == 4:

            print("Length of the longest increasing subsequence is ",lis(complex_numbers))
        elif choice == 0:
            break
        else:
            print("Invalid choice")


start()
