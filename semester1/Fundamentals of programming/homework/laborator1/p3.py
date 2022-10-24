#15.Generate the largest perfect number smaller than a given natural number n.
# If such a number does not exist, a message should be displayed.
# A number is perfect if it is equal to the sum of its divisors, except itself.
# (e.g. 6 is a perfect number, as 6=1+2+3).
def sum_of_divisors(n):
     sum=0
     for i in range(1, n):
         if (n % i) == 0:
            sum=sum+i
     return sum

def is_perfect(n):
    if(sum_of_divisors(n)==n):
        return 1
    else:
        return 0
def largest_perfect_smaller_than_n(n):
    n=n-1
    while n:
        if(is_perfect(n)==1):
            return n
        else:
            n=n-1
def start():
    n=int(input("Give a natural number > "))
    print("The largest perfect number that is smaller than the given number is ",largest_perfect_smaller_than_n(n))
start()
