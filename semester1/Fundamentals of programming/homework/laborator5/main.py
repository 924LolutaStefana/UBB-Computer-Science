"""
Determine the longest common subsequence of two given sequences.
 Subsequence elements are not required to occupy consecutive positions.
 For example, if X = "MNPNQMN" and Y = "NQPMNM", the longest common subsequence has length 4,
  and can be one of "NQMN", "NPMN" or "NPNM".Determine and display both the length of
  the longest common subsequence as well as at least one such subsequence.
"""

def naive(X, Y, m, n):
    if m == 0 or n == 0:
        return 0
    elif X[m - 1] == Y[n - 1]:
        return 1 + naive(X, Y, m - 1, n - 1)
    else:
        return max (naive(X, Y, m, n - 1), naive(X, Y, m - 1, n))


def dynamic_programming(X, Y,m,n):
    L = [[0 for i in range(n + 1)] for j in range(m + 1)]
    for i in range(m + 1):
        for j in range(n + 1):
            if i == 0 or j == 0:
                L[i][j] = 0
            elif X[i - 1] == Y[j - 1]:
                L[i][j] = L[i - 1][j - 1] + 1
            else:
                L[i][j] = max(L[i - 1][j], L[i][j - 1])
    longest_common_subsequence = ""
    i = m
    j = n
    while i > 0 and j > 0:
        if X[i - 1] == Y[j - 1]:
            longest_common_subsequence += X[i - 1]
            i -= 1
            j -= 1
        elif L[i - 1][j] > L[i][j - 1]:
            i -= 1

        else:
            j -= 1
    longest_common_subsequence = longest_common_subsequence[::-1]
    print("Longest common subsequence of " + X + " and " + Y + " is " + longest_common_subsequence)


    return L[m][n]


def print_menu():
    print("1.Naive implementation for the problem ")
    print("2.Dynamic Programming implementation ")
    print("0.Exit")
if __name__ == '__main__':


    while True:
        print_menu()
        option = int(input("Choose a number>"))
        if option == 1:
            X = "MNPNQMN"
            Y = "NQPMNM"
            print("Length of longest common subsequence is ", naive(X, Y, len(X), len(Y)))
        elif option == 2:
            X = "MNPNQMN"
            Y = "NQPMNM"
            m = len(X)
            n = len(Y)
            print("Length of longest common subsequence is ", dynamic_programming(X, Y,m,n))
        else: break


