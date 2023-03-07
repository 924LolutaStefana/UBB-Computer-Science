#include <stdio.h>
//3.a. Print the Pascal triangle of dimension n of all combinations C(m,k) of m objects taken by k, k = 0, 1, ..., m, for line m, where m = 1, 2, ..., n.
//b.Given a vector of numbers, find the longest contiguous subsequence of prime numbers.
typedef struct {
	//struct for the vector to be abble to access the elements and its length
	int ArrayOfNumbers[101], lenght;
}Vector;
Vector read_vector()
{
	//This function reads the elements of a vector 
	int number_of_elements;
	printf("Give the number of elements from the vector: ");
	scanf("%d", &number_of_elements);//we read the nr of elements of the vector
	Vector vector;
	vector.lenght = number_of_elements;
	printf("Give the elements of the vector: ");
	for (int i = 0; i < vector.lenght; i++)
		scanf("%d", &vector.ArrayOfNumbers[i]);//we read the elements of the vector
	return vector;
}
void menu() {
	//this function prints the menu
	printf("1.Print the Pascal triangle of dimension n of all combinations C(m,k)\n");
	printf("2.Longest contiguous subsequence of prime numbers \n");
	printf("3.Exit the program.\n");
}
int binomial_coefficient(int n, int k) {
    /// <summary>
    ///  this function return the binomial coefficient of a number
    /// </summary>
    /// <param name="n"></param> integer
    /// <param name="k"></param> integer
    /// <returns></returns>
    if (k == 0 || k == n) {
        return 1;
    }
    else {
        return binomial_coefficient(n - 1, k - 1) + binomial_coefficient(n - 1, k);
    }
}

void print_pascal_triangle(int n) {
    /// <summary>
    /// this function prints the pascal triangle of dimensions n
    /// </summary>
    /// <param name="n"></param> integer
    for (int m = 0; m <= n; m++) {
        for (int k = 0; k <= m; k++) {
            printf("%d ", binomial_coefficient(m, k));
        }
        printf("\n");
    }
}
int is_prime(int n) {
    /// <summary>
    /// This function chencks if a number n is prime or note
    /// </summary>
    /// <param name="n"></param> integer
    /// <returns></returns>
    if (n <= 1) {
        return 0;
    }
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            return 0;
        }
    }
    return 1;
}

int longest_prime_subsequence(int arr[], int n, int result[]) {
    /// <summary>
    /// This funcion computes an array with the longest prime subsequence and returns the maximum length
    /// </summary>
    /// <param name="arr"></param>array of integers
    /// <param name="n"></param>integer
    /// <param name="result"></param>array of integers
    /// <returns></returns>
    int index, current_length = 0, max_length = 0;
    for (index = 0; index < n; index++) {
        if(is_prime(arr[index]))
            current_length++;
        else if (current_length > max_length) { // check if the current subsequence has the maximum length
            max_length = current_length;
            int difference = index - current_length; // find the index of the first element of the subsequence
            for (int j = 0; j < max_length; j++)
                result[j] = arr[j + difference];
            current_length = 1;
        }
        else
            current_length = 1;
    }
    // this case is for when the subsequence is at the end of the vector
    if (current_length > max_length) {
        max_length = current_length;
        int difference = index - current_length;
        for (int j = 0; j < max_length; j++)
            result[j] = arr[j + difference];
    }
    return max_length;

}
int main()
{
	int option;
	int is_finished = 1;
	while (is_finished) {
		menu();
		printf("Give your option:\n");
		scanf("%d", &option);
        if (option == 1)
        {
            int n;
            printf("Enter the dimension of Pascal's triangle: ");
            scanf("%d", &n);
            print_pascal_triangle(n);
        }
        else if (option == 2)
        {
            
            Vector vector;
            vector = read_vector();
            int result[100];
           
            
            int length=longest_prime_subsequence(vector.ArrayOfNumbers, vector.lenght,result);
            for (int index = 1; index < length; index++)
                printf("%d ", result[index]);
            printf("\n");
        }
        else if (option == 3)
            is_finished = 0;
        else
            printf("Invalid choice! try again");


	}
}

