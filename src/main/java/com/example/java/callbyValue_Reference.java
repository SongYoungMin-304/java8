package com.example.java;

public class callbyValue_Reference {

    private int num;


    public static void main(String[] args) {

        int []array = {1,2,3,4,5};
        int k = 10;
        callbyValue_Reference a = new callbyValue_Reference();


        for (int i : array) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println(k);
        System.out.println(a.num);

        test(array.clone(), k, a);

        for (int i : array) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println(k);
        System.out.println(a.num);
    }

    public static void test(int []array, int k, callbyValue_Reference b){
        for(int a = 0; a < array.length; a++){
            array[a]++;
        }
        k++;
        b.num++;
    }
}
