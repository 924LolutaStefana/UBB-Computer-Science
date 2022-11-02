#6.Generate all sequences
# of n parentheses that close correctly.
# Example: for n=4 there are two solutions: (()) and ()() USING BACKTRACKING and also the iterative solution
def generate_parenthesis_backtracking( n) :
    combinations=[]
    open_brackets = 0
    close_brackets = 0
    backtrack(combinations, "", open_brackets, close_brackets, n)
    return combinations


def iterative(n):
    combination = []

    stack = [("", 0, 0)]
    k = ""
    while stack:
        k,open_brackets, close_brackets=stack.pop()

        if open_brackets < n:
            stack.append((k + "(",open_brackets + 1,close_brackets))


        if close_brackets < open_brackets:
            stack.append((k + ")",open_brackets,close_brackets+1))
        if len(k) == n * 2:
            combination.append(k)


    return combination


def backtrack (combination, k, open_brackets, close_brackets, n):
    if len(k) == n *2:
        combination.append(k)
        return

    if open_brackets < n :
        backtrack(combination, k + "(", open_brackets + 1, close_brackets, n)

    if close_brackets < open_brackets :
        backtrack(combination, k + ")", open_brackets, close_brackets + 1, n)


def main():

    n=int(input("Give a number> "))
    opt=(input("Choose an option: Iterative or Backtracking?> "))
    if opt=="Iterative":
        if n % 2 == 1:
            print("There are no solutions.")
        else:
            print(iterative(n//2))
    else:
        if opt=="Backtracking":
            if  n%2==1:
                print("There are no solutions.")
            else:
                print(generate_parenthesis_backtracking(n//2))
        else:
            print("Not a valid choice.")

main()

