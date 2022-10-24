#1.Generate the first prime number larger than a given natural number n.
def is_prime(n):
    if n<2 :
        return False
    for a in range (2,int(n/2+1)):
        if n%a==0:
            return False
    return True
def first_prime_larger_than_n(n):
    number=n+1
    while is_prime(number) == False:
        number = number+ 1
    return number
def start():
    number=int(input("Give a natural number >"))
    print("The first prime number larger than the given number is ",first_prime_larger_than_n(number))
start()