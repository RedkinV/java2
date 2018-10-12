package ru.geekbrains.java2.lesson2;

public class Main {

    public static final int M=4;

    public static int  sumArr(String[][] strArr) throws MyArraySizeException, MyArrayDataException {
        int sum=0;

        if(strArr.length==M) {
            for (int i = 0; i <strArr.length ; i++) {
                if (strArr[i].length!=M) throw new MyArraySizeException("Размер не равен 4х4. Работа программы прекращена!");
            }
        } else throw new MyArraySizeException("Размер не равен 4х4");


        for (int i = 0; i <strArr.length ; i++) {
            for (int j = 0; j <strArr[i].length ; j++) {

                try {
                    sum+= Integer.parseInt(strArr[i][j]);
                }
                catch (NumberFormatException e){
                    throw new MyArrayDataException("В ячейке ("+i+","+j+") не число.");
                }

            }
        }

        return sum;
    }



    public static void main(String[] args) {
        String[][] strArr = new String[][]{{"1", "2", "3", "4"}, {"4", "5", "1", "0"}, {"6", "8", "4", "2"}, {"dd", "2","6", "2"}};
//        String[][] strArr=new String[][] {{"1","2","3","4"},{"4","5","3","7"},{"6","8","4","9"}};

        try {
            System.out.println(sumArr(strArr));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println("Работа программы остановлена!");
        }

    }
}
