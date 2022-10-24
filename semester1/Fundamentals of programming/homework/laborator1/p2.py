#10.The palindrome of a number is the number obtained by reversing the order of its digits
# (e.g. the palindrome of 237 is 732). For a given natural number n, determine its palindrome.



def palindrome(number):
    reversed_number=0
    while number != 0:
        digit = number % 10
        reversed_number = reversed_number * 10 + digit
        number //= 10
    return reversed_number



def start():
    number=int(input("Give the number:"))

    print("The numbers palindrome is: ",palindrome(number))
start()