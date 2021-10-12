package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] mm = new int[n][n];
        for(int i = 0; i<matrix.length;i++){
            for(int j =0 ;j<matrix.length;j++){

                mm[j][n-i]=matrix[i][j];
            }
        }

        for(int i =0;i<matrix.length;i++){
            for(int j =0;j<matrix.length;j++){
                matrix[i][j] = matrix[i][j];

            }


        }




    }
}
