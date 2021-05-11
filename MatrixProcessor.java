

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;



public class MatrixProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true){
            int key =  showMenu();
            
            switch(key) {
                case 1:
                
                    System.out.print("Enter size of first matrix:");
                    int r1 = scanner.nextInt();
                    int c1 = scanner.nextInt();
                    System.out.println("Enter first matrix:");
                    double [][] grid1 = getMatrixfromUser(r1,c1);
                    
                    System.out.print("Enter size of second matrix:");
                    int r2 = scanner.nextInt();
                    int c2 = scanner.nextInt();
                    System.out.println("Enter second matrix:");
                    double[][] grid2 = getMatrixfromUser(r2,c2);
                    
                
                    
                    double[][] resultAddition = addMatrix(grid1,grid2);
            
                    if (resultAddition.length == 0) {
                        System.out.println("The operation cannot be performed.");
                    }
                    
                    else {
                        showMatrix(resultAddition);
                    }
                    
                    break;
                
                case 2:
                
                    System.out.print("Enter size of matrix:");
                    int r = scanner.nextInt();
                    int c = scanner.nextInt();
                    System.out.println("Enter matrix:");
                    double [][] grid = getMatrixfromUser(r,c);
                    System.out.print("Enter constant:");
                    double constScalar = scanner.nextDouble();
                    
                    double[][] resultScalarMultiplication = scalarMultiMatrix(grid,constScalar);
                    showMatrix(resultScalarMultiplication);
                    break;
                    
                case 3:
                    System.out.println("->>>Multiplication");
                    
                    System.out.print("Enter size of first matrix:");
                    int r3 = scanner.nextInt();
                    int c3 = scanner.nextInt();
                    System.out.println("Enter first matrix:");
                    double [][] grid3 = getMatrixfromUser(r3,c3);
                    
                    System.out.print("Enter size of second matrix:");
                    int r4 = scanner.nextInt();
                    int c4 = scanner.nextInt();
                    System.out.println("Enter second matrix:");
                    double[][] grid4 = getMatrixfromUser(r4,c4);
                    
                    double[][] resultMatrixMultiplication = multiplyMatrices(grid3,grid4);
                    
                    if (resultMatrixMultiplication.length == 0) {
                        System.out.println("The operation cannot be performed.");
                    }
                    
                    else {
                        showMatrix(resultMatrixMultiplication);
                    }
                    
                    break;
                   
                case 4:
                    int keyTranspose =  showMenuTranspose();
                    System.out.print("Enter size of matrix:");
                    int r5 = scanner.nextInt();
                    int c5 = scanner.nextInt();
                    System.out.println("Enter matrix:");
                    double [][] grid5 = getMatrixfromUser(r5,c5);
                    
                    transposeMatrix(grid5, keyTranspose);
                    break;
                    
                case 5:
                    System.out.print("Enter size of matrix:");
                    int r6 = scanner.nextInt();
                    int c6 = scanner.nextInt();
                    System.out.println("Enter matrix:");
                    double [][] grid6 = getMatrixfromUser(r6,c6);
                    double result = determinant(grid6);
                    System.out.println("The result is:");
                    System.out.println(result);
                    break;
                    
                case 6:
                    System.out.print("Enter size of matrix:");
                    int r7 = scanner.nextInt();
                    int c7 = scanner.nextInt();
                    System.out.println("Enter matrix:");
                    double [][] grid7 = getMatrixfromUser(r7,c7);
                    
                    double det = determinant(grid7);
                    if (det == 0) {
                        break;
                    }
                    
                    double [][] ans = inverse(grid7);
                    
                    showMatrix(ans);
                    
                    

                    
                    
                    
                    break;
                
                    
                case 0:
                    return;
            } 
                
        }
        
        
    }
    
    public static double determinant(double[][] arr) {
        double result = 0;
        if (arr.length == 1) {
            result = arr[0][0];
            return result;
        }
        if (arr.length == 2) {
            result = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
            return result;
        }
        
        for (int i = 0; i < arr[0].length ; i++) {
            double temp[][] = new double[arr.length - 1][arr[0].length -1];
            
            for(int j = 1; j < arr.length; j++) {
                for(int k = 0; k < arr[0].length; k++) {
                    if(k < i){
                        temp[j - 1][k] = arr[j][k];
                    } else if (k > i) {
                        temp[j -1][k-1] = arr[j][k];
                    }
                }
            }
            
            result += arr[0][i] * Math.pow(-1, (int) i) * determinant(temp);
        }
        
        return result;
    }
    
    private static double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(minor(matrix, i, j));

        // adjugate and determinant
        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }
    
    private static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }

    
    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        
        int r1 = firstMatrix.length;
        int c2 = secondMatrix[0].length;
        int c1 = firstMatrix[0].length;
        
        if (c1 != secondMatrix.length ){
            return new double[0][0];
        }
        
        
        double[][] product = new double[r1][c2];
        for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return product;
    }
    
    public static int showMenu() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice:");
        int choice = scanner.nextInt();
        return choice;
        
    }
    
    public static int showMenuTranspose() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice:");
        int choice = scanner.nextInt();
        return choice;
        
    }
    
    public static double[][] getMatrixfromUser(int r, int c) {
        Scanner scanner = new Scanner(System.in);
        
        double[][] grid = new double [r][c];
        
        for (int i = 0; i < grid.length; i++) {
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            ArrayList<Double> inumbers = new ArrayList<Double>();
            for (String s : numbers)
                inumbers.add(Double.parseDouble(s));
                
            double[] arr = inumbers.stream().mapToDouble(Double::doubleValue).toArray();
            grid[i] = arr;
        }
        
        return grid;
    }
    
    public static double[][] addMatrix(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length){
            return new double[0][0];
        }
        
        double[][] ans = new double [A.length][A[0].length];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = A[i][j] + B[i][j];
            }
        }
        
        return ans;
        
    }
    
    public static double[][] scalarMultiMatrix(double[][]A, double scalar) {
        double[][] ans = new double [A.length][A[0].length];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = A[i][j] * scalar;
            }
        }
        
        return ans;
        
    }
    
    public static void showMatrix(double[][] matrix) {
        System.out.println("The result is:");
       for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        } 
    }
    
    
    public static void transposeMatrix(double[][] A, int method) {
        double[][] ans = new double [A.length][A[0].length];
        
        switch(method) {
            case 1:
                System.out.println("Main Diagonal");
                for (int i = 0; i < ans.length; i++) {
                    for (int j = 0; j < ans[0].length; j++) {
                        ans[i][j] = A[j][i];
                    }
                }
                
                showMatrix(ans);
                
                break;
            case 2:
                System.out.println("Side diagonal");
                int n = ans[0].length;
                for (int i = 0; i < (n - 1); i++) {
                    for (int j = 0; j < (n - 1) - i; j++){
                        double tmp = A[i][j];
                        A[i][j] = A[(n - 1) - j][(n - 1) - i];
                        A[(n - 1) - j][(n - 1) - i] = tmp;
                    }       
                }
                
                showMatrix(A);
                break;
            case 3:
                System.out.println("Vertical Line");
                
                for(int i = 0; i < A.length; i++ ){
                    int start = 0;
                    int end = A[0].length - 1;
                    
                    while (start < end) {
                        ans[i][start] = A[i][end];
                        ans[i][end] = A[i][start];
                        
                        start += 1;
                        end -= 1;
                    }
                }
                
                showMatrix(ans);
                break;
            case 4:
                System.out.println("Horizontal Line");
                int start = 0;
                int end = A[0].length - 1;    
                while (start < end) {
                    ans[start] = A[end];
                    ans[end] = A[start];
                        
                    start += 1;
                    end -= 1;
                }
                
                showMatrix(ans);
                break;
        }
        
    }
    
    
}
