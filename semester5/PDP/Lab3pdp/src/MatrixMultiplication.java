import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {

    public static int computeElement(int[][] matrixA, int[][] matrixB, int row, int col) {
        int result = 0;
        int n = matrixA[0].length;

        for (int i = 0; i < n; i++) {
            result += matrixA[row][i] * matrixB[i][col];
        }

        return result;
    }

    //Consecutive elements, going row after row
    public static Runnable rowTask(int[][] matrixA, int[][] matrixB, int[][] result, int startRow, int endRow) {
        return () -> {
            for (int i = startRow; i <= endRow; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = computeElement(matrixA, matrixB, i, j);
                }
            }
        };
    }

    //Consecutive elements, going column after column
    public static Runnable columnTask(int[][] matrixA, int[][] matrixB, int[][] result, int startCol, int endCol) {
        return () -> {
            for (int j = startCol; j <= endCol; j++) {
                for (int i = 0; i < result.length; i++) {
                    result[i][j] = computeElement(matrixA, matrixB, i, j);
                }
            }
        };
    }

    //Every k-th element, going row by row
    public static Runnable kthElementTask(int[][] matrixA, int[][] matrixB, int[][] result, int k) {
        return () -> {
            int numRows = matrixA.length;
            int numCols = matrixB[0].length;

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    int index = i * numRows + j;
                    if (index % k == 0) {
                        result[i][j] = computeElement(matrixA, matrixB, i, j);
                    }
                }
            }
        };
    }

    // Function to run parallel tasks using individual threads
    public static void runTasksInThreads(int numTasks, Runnable[] tasks) throws InterruptedException {
        Thread[] threads = new Thread[numTasks];

        for (int i = 0; i < numTasks; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();//waits for all the threads to finish their execution
        }
    }

    // Function to run parallel tasks using a thread pool
    public static void runTasksInThreadPool(int numTasks, Runnable[] tasks, int numThreads) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);//initalization of the thread pool

        for (int i = 0; i < numTasks; i++) {
            executorService.execute(tasks[i]);
        }

        executorService.shutdown();// no new tasks will be accepted!!
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);// in case an old task is still executing, wait for it
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] initializeMatrix( int size) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 2);
            }
        }

        return matrix;
    }


    public static void main(String[] args) throws InterruptedException {

        int size=1000;
        int[][] matrixA = initializeMatrix(size);
        int[][] matrixB = initializeMatrix(size);



        //System.out.println("Matrix A:");
       // printMatrix(matrixA);

//        System.out.println("Matrix B:");
//        printMatrix(matrixB);

        int numTasks = 10;

        // Scenario 1: Consecutive elements, going row after row
        System.out.println("SCENARIO 1: Consecutive elements, going row after row");

        // Approach 1: Create an actual thread for each task
        long startTime = System.currentTimeMillis();

        int[][] result1Thread = new int[size][size];
        Runnable[] rowTasksThread = new Runnable[numTasks];
        for (int i = 0; i < numTasks; i++) {
            int startRow = i * size / numTasks;
            int endRow = (i + 1) * size / numTasks - 1;
            rowTasksThread[i] = rowTask(matrixA, matrixB, result1Thread, startRow, endRow);
        }

        runTasksInThreads(numTasks, rowTasksThread);

        long endTime = System.currentTimeMillis();
        System.out.println("Result with individual threads: ");
        //printMatrix(result1Thread);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
        System.out.println("----------------------------------------------------");
        // Approach 2: Use a thread pool
        startTime = System.currentTimeMillis();

        int[][] result1ThreadPool = new int[size][size];
        Runnable[] rowTasksThreadPool = new Runnable[numTasks];
        for (int i = 0; i < numTasks; i++) {
            int startRow = i * size / numTasks;
            int endRow = (i + 1) * size / numTasks - 1;
            rowTasksThreadPool[i] = rowTask(matrixA, matrixB, result1ThreadPool, startRow, endRow);
        }


        runTasksInThreadPool(numTasks, rowTasksThreadPool, 80);

        endTime = System.currentTimeMillis();
        System.out.println("Result with a thread pool: ");
       // printMatrix(result1ThreadPool);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds\n");
        System.out.println("----------------------------------------------------");
        // Scenario 2: Consecutive elements, going column after column
        System.out.println("SCENARIO 2: Consecutive elements, going column after column");

        // Creating a thread for each task
        startTime = System.currentTimeMillis();

        int[][] result2Thread = new int[size][size];
        Runnable[] colTasksThread = new Runnable[numTasks];
        for (int i = 0; i < numTasks; i++) {
            int startCol = i * size / numTasks;
            int endCol = (i + 1) * size / numTasks - 1;
            colTasksThread[i] = columnTask(matrixA, matrixB, result2Thread, startCol, endCol);
        }

        runTasksInThreads(numTasks, colTasksThread);

        endTime = System.currentTimeMillis();
        System.out.println("Result with individual threads: ");
       // printMatrix(result2Thread);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
        System.out.println("----------------------------------------------------");
        // Approach 2: Use a thread pool
        startTime = System.currentTimeMillis();

        int[][] result2ThreadPool = new int[size][size];
        Runnable[] colTasksThreadPool = new Runnable[numTasks];
        for (int i = 0; i < numTasks; i++) {
            int startCol = i * size / numTasks;
            int endCol = (i + 1) * size / numTasks - 1;
            colTasksThreadPool[i] = columnTask(matrixA, matrixB, result2ThreadPool, startCol, endCol);
        }

        runTasksInThreadPool(numTasks, colTasksThreadPool, 80);

        endTime = System.currentTimeMillis();
        System.out.println("Result with a thread pool: ");
       // printMatrix(result2ThreadPool);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds\n");
        System.out.println("----------------------------------------------------");
        // Scenario 3: Every k-th element, going row by row
        System.out.println("SCENARIO 3: Every k-th element, going row by row");

        startTime = System.currentTimeMillis();

        int[][] result3Thread = new int[size][size];
        Runnable[] kthElementTasksThread = new Runnable[numTasks];
        for (int i = 0; i < numTasks; i++) {
            kthElementTasksThread[i] = kthElementTask(matrixA, matrixB, result3Thread, numTasks);
        }

        runTasksInThreads(numTasks, kthElementTasksThread);

        endTime = System.currentTimeMillis();
        System.out.println("Result with individual threads: ");
       // printMatrix(result3Thread);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
        System.out.println("----------------------------------------------------");
        // a thread pool
        startTime = System.currentTimeMillis();

        int[][] result3ThreadPool = new int[size][size];
        Runnable[] kthElementTasksThreadPool = new Runnable[numTasks];
        for (int i = 0; i < numTasks; i++) {
            kthElementTasksThreadPool[i] = kthElementTask(matrixA, matrixB, result3ThreadPool, numTasks);
        }

        runTasksInThreadPool(numTasks, kthElementTasksThreadPool, 80);
        endTime = System.currentTimeMillis();
        System.out.println("Result with a thread pool: ");
        //printMatrix(result3ThreadPool);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
    }
}
